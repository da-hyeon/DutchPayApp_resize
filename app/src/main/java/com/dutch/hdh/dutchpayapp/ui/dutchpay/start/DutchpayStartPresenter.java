package com.dutch.hdh.dutchpayapp.ui.dutchpay.start;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayStartListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.Dutchpayhistory;
import com.dutch.hdh.dutchpayapp.data.db.DutchpaytotalList;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail.DutchpayDetailFragment;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.DutchpayNewFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DutchpayStartPresenter implements DutchpayStartContract.Presenter{

    private DutchpayStartContract.View mView;

    private MyApplication mMyApplication;

    private ObservableArrayList<TempStartListModel> mStartList;
    private DutchpayStartListAdapter mAdapter;

    public DutchpayStartPresenter(DutchpayStartContract.View mView) {
        this.mView = mView;
        this.mStartList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayStartListAdapter(mStartList,this);
        this.mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void listInit() {
        //리스트 불러오기
        Call<Dutchpayhistory> getDutchpayHistoryList = MyApplication
                .getRestAdapter()
                .getDutchapyHistoryList(mMyApplication.getUserInfo().getUserCode());

        getDutchpayHistoryList.enqueue(new Callback<Dutchpayhistory>() {
            @Override
            public void onResponse(Call<Dutchpayhistory> call, Response<Dutchpayhistory> response) {
                if(response.body() != null){
                    ArrayList<DutchpaytotalList> list = response.body().getDutchpayHistoryList();
                    Gson gson = new Gson();
                    String a = gson.toJson(response.body());
                    Log.e("List? ->",a);

                    for(int i=0; i<list.size(); i++){
                        DutchpaytotalList item = list.get(i);
                        int status = dutchpayStatusCheck(item);

                        mStartList.add(new TempStartListModel(item.getShop(), String.valueOf(item.getCost()), item.getDate(),status));
                    }
                }
            }

            @Override
            public void onFailure(Call<Dutchpayhistory> call, Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }

    //내역 클릭
    @Override
    public void onItemClick(TempStartListModel item) {
            //임시 이동
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayDetailFragment dutchpayDetailFragment = new DutchpayDetailFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayDetailFragment , DutchpayDetailFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayDetailFragment.class.getName());
        fragmentTransaction.commit();
    }

    //더치페이 새로 시작하기
    public void onNewClick(){
        //기존 더치페이가 존재한다면 초기화
        mMyApplication.setDutchpayGroup(false);
        mMyApplication.setDutchpayNewFragment(null);

        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayNewFragment dutchpayNewFragment = mMyApplication.getDutchpayNewFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayNewFragment, DutchpayNewFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayNewFragment.class.getName());
        fragmentTransaction.commit();
    }

    public DutchpayStartListAdapter getmAdapter() {
        return mAdapter;
    }

    public ObservableArrayList<TempStartListModel> getmStartList() {
        return mStartList;
    }

    @BindingAdapter("item")
    public static void bindItem(RecyclerView view, ObservableArrayList<TempStartListModel> list){
        DutchpayStartListAdapter adapter = (DutchpayStartListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

    public void onIngClick(){
        listFiltering(true);
    }

    public void onCompleteClick(){
        listFiltering(false);
    }

    public void onAllClick(){
        mAdapter.setItem(mStartList);
        mAdapter.notifyDataSetChanged();
    }

    //더치페이 내역 필터링
    public void listFiltering(boolean flag){
        ObservableArrayList<TempStartListModel> newList = new ObservableArrayList<>();

        for(int i = 0; i < mStartList.size(); i++){
            int state = mStartList.get(i).getState();

            if(flag){ //진행중
                if(state == Constants.DUTCHPAY_STATE_WAIT || state == Constants.DUTCHPAY_STATE_REQUEST){
                    newList.add(mStartList.get(i));
                }
            } else { //완료_취소
                if(state == Constants.DUTCHPAY_STATE_COMPLETE || state == Constants.DUTCHPAY_STATE_CANCEL){
                    newList.add(mStartList.get(i));
                }
            }
        }
        mAdapter.setItem(newList);
        mAdapter.notifyDataSetChanged();
    }

    private int dutchpayStatusCheck(DutchpaytotalList item){

        if(item.isCostcomplete()){ //거래완료
            return Constants.DUTCHPAY_STATE_COMPLETE;
        } else { //입금대기
            return Constants.DUTCHPAY_STATE_WAIT;
        }
    }

}
