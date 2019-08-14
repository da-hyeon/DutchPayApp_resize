package com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayDetailListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.DutchDetailMember;
import com.dutch.hdh.dutchpayapp.data.db.DutchDetailRoom;
import com.dutch.hdh.dutchpayapp.data.db.DutchpayDetail;
import com.dutch.hdh.dutchpayapp.data.db.Dutchpayhistory;
import com.dutch.hdh.dutchpayapp.data.db.DutchpaytotalList;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.photo.DutchpayPhotoFragment;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.TempStartListModel;
import com.dutch.hdh.dutchpayapp.ui.receipt.ReceiptActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DutchpayDetailPresenter implements DutchpayDetailContract.Presenter {

    private DutchpayDetailContract.View mView;
    private ObservableArrayList<TempDetailListModel> mDetailList;
    private DutchpayDetailListAdapter mAdapter;

    private MyApplication mMyApplication;
    private Gson gson;

    private int mLeaderCode;
    private int mRoomCode;
    private int mMemPayCode;
    private int mMyCost;
    private int mAllMemCost = 0;
    private int mLeaderPosition = 0;
    private String mRoomTitle;

    public DutchpayDetailPresenter(DutchpayDetailContract.View mView) {
        this.mView = mView;
        this.mDetailList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayDetailListAdapter(mDetailList,this);
        this.mMyApplication = MyApplication.getInstance();
        this.gson = new Gson();
    }

    @Override
    public void listInit(Bundle bundle) {
        int dutchpayId = bundle.getInt("dutchpayID");

        //리스트 불러오기
        Call<DutchpayDetail> getDutchpayDetail = MyApplication
                .getRestAdapter()
                .getDutchpayDetail(mMyApplication.getUserInfo().getUserCode(),
                        dutchpayId);

        getDutchpayDetail.enqueue(new Callback<DutchpayDetail>() {
            @Override
            public void onResponse(Call<DutchpayDetail> call, Response<DutchpayDetail> response) {
                if(response.body() != null){
                    //방 정보 꺼내기
                    ArrayList<DutchDetailRoom> roomInfo = response.body().getRoominfo();
                    DutchDetailRoom room = roomInfo.get(0);
                    mRoomTitle = room.getShop();

                    //멤버 리스트 꺼내기
                    ArrayList<DutchDetailMember> memberInfo = response.body().getMemberinfo();

                    //방정보 셋팅
                    mView.setDutchpayTitle(mRoomTitle);
                    mView.setDutchpayCost(String.valueOf(room.getCost()));
                    mView.setDutchpayMemCount(String.valueOf(memberInfo.size()));

                    String b = gson.toJson(memberInfo);
                    Log.e("Member? ->",b);

                    for(int i=0; i<memberInfo.size(); i++) {
                        DutchDetailMember member = memberInfo.get(i);

                        if(member.getLeaderFlag() == 0){ //방장
                            mLeaderCode = member.getMemCode();
                            mRoomCode = member.getRoomCode();
                            mLeaderPosition = i;
                        } else {
                            mAllMemCost += member.getMemCost();
                        }

                        boolean imageFlag = false;
                        if(member.getMemCode() == Integer.parseInt(mMyApplication.getUserInfo().getUserCode())){ //본인 체크
                            imageFlag = true;
                            mMemPayCode = member.getMemDutchcode();
                            mMyCost = member.getMemCost();

                            if(member.getPayComplete().equals("N") && member.getPrePayed().equals("N")){
                                mView.setPointButtonView();
                            }
                        }

                        int payed = 0;
                        if(member.getPrePayed().equals("Y") || member.getPayComplete().equals("Y")){
                            payed = 2;
                        }
                        mDetailList.add(new TempDetailListModel(member.getNaem(),imageFlag,String.valueOf(member.getMemCost()),payed));

                    }
                    //방장 금액 반영
                    mDetailList.get(mLeaderPosition).setCoast(String.valueOf(room.getCost() - mAllMemCost));

                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DutchpayDetail> call, Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });

        //binding
        mView.adapterInit();
    }

    public ObservableArrayList<TempDetailListModel> getmDetailList() {
        return mDetailList;
    }

    public DutchpayDetailListAdapter getmAdapter() {
        return mAdapter;
    }

    @BindingAdapter("item")
    public static void bindItem(RecyclerView view, ObservableArrayList<TempDetailListModel> list){
        DutchpayDetailListAdapter adapter = (DutchpayDetailListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

    public void onPhotoClick(){
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayPhotoFragment dutchpayPhotoFragment = new DutchpayPhotoFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayPhotoFragment , DutchpayPhotoFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayPhotoFragment.class.getName());
        fragmentTransaction.commit();
    }

    public void onPayClick(){
        //더치페이 참여자 결제
        Call<Void> setMemberDutchpay = MyApplication
                .getRestAdapter()
                .setMemberDutchpay(
                        mLeaderCode,
                        Integer.parseInt(mMyApplication.getUserInfo().getUserCode()),
                        mRoomCode,
                        mMemPayCode
                );

        setMemberDutchpay.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("response",response.message());

                mView.showSuccessDialog("성공", "결제 완료");
                mMyApplication.getUserInfo().setUserMoney(mMyApplication.getUserInfo().getUserMoney() - mMyCost);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //서버통신 오류
                mView.showFailDialog("실패", "서버와 통신할 수 없습니다.");
            }
        });
    }

    /**
     * 다이얼로그 확인버튼
     */
    public void clickSuccessDialog() {

        Intent intent = new Intent(mView.getContext(), ReceiptActivity.class);
        mView.setDefaultMainStack();
        intent.putExtra(Constants.PAYMENT_DATE ,currentTime());
        intent.putExtra(Constants.PAYMENT_STORE_NAME ,mRoomTitle);
        intent.putExtra(Constants.PAYMENT_AMOUNT , mMyCost);
        intent.putExtra(Constants.PAYMENT_STORE_LOCATION ,"더치페이 참가 결제");

        mView.getContext().startActivity(intent);
        mView.getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 현재날짜 + 시간 구하기
     */
    private String currentTime() {
        Calendar cal = Calendar.getInstance();
        //현재 년도, 월, 일
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int date = cal.get(cal.DATE);

        //현재 (시,분,초)
        int hour = cal.get(cal.HOUR_OF_DAY);
        int min = cal.get(cal.MINUTE);
        int sec = cal.get(cal.SECOND);

        return year + "년 " + month + "월 " + date +"일 " + hour +"시 " + min + "분 " + sec + "초";
    }
}
