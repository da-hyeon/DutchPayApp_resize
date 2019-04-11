package com.dutch.hdh.dutchpayapp.ui.dutchpay.start;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayStartListAdapter;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail.DutchpayDetailFragment;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.DutchpayNewFragment;

public class DutchpayStartPresenter implements DutchpayStartContract.Presenter{

    private DutchpayStartContract.View mView;

    private ObservableArrayList<TempStartListModel> mStartList;
    private DutchpayStartListAdapter mAdapter;

    public DutchpayStartPresenter(DutchpayStartContract.View mView) {
        this.mView = mView;
        this.mStartList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayStartListAdapter(mStartList,this);
    }

    @Override
    public void listInit() {
        //더미 데이터 셋
        mStartList.add(new TempStartListModel("[BAR 홍대점]","국민카드","50,000원","2019/04/26    18:36:28",1));
        mStartList.add(new TempStartListModel("[CU 편의점]","신한카드","30,000원","2019/04/20    10:30:06",2));
        mStartList.add(new TempStartListModel("[우도 음식점]","하나카드","120,000원","2019/04/15    05:12:00",3));
        mStartList.add(new TempStartListModel("[신사 호프]","하나카드","360,000원","2019/03/31    19:02:07",0));
        mStartList.add(new TempStartListModel("[북경오리]","하나카드","540.000","2019/03/28    12:06:45",0));

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
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayNewFragment dutchpayNewFragment = new DutchpayNewFragment();
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

}
