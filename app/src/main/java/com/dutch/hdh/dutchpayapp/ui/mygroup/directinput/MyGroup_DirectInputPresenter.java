package com.dutch.hdh.dutchpayapp.ui.mygroup.directinput;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.Listview_DirectInputParticipantsAdapter;

public class MyGroup_DirectInputPresenter implements MyGroup_DirectInputContract.Presenter {

    private MyGroup_DirectInputContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private MyApplication mMyApplication;
    private Listview_DirectInputParticipantsAdapter mListview_DirectInputParticipantsAdapter;

    /**
     * 생성자
     */
    MyGroup_DirectInputPresenter(MyGroup_DirectInputContract.View mView, Context mContext , FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * ListView 초기화 및 Adapter 세팅
     */
    @Override
    public void initListViewData(ListView listView) {

        mListview_DirectInputParticipantsAdapter = new Listview_DirectInputParticipantsAdapter(mView, mContext);
        mListview_DirectInputParticipantsAdapter.addMember();

        mView.changeAddMemberCount(mListview_DirectInputParticipantsAdapter.getCount());
        listView.setAdapter(mListview_DirectInputParticipantsAdapter);
    }

    /**
     * 멤버추가 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAddMember() {
        mListview_DirectInputParticipantsAdapter.addMember();
        mView.changeAddMemberCount(mListview_DirectInputParticipantsAdapter.getCount());
        mListview_DirectInputParticipantsAdapter.notifyDataSetInvalidated();
    }

    /**
     * 멤버추가완료 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickMemberAdditionConfirmed(Bundle bundle) {
        if (mListview_DirectInputParticipantsAdapter.isEmptyText()) {
            mView.showFailDialog("실패", "빈칸을 채워주세요.");
        } else {
            Bundle bundle1 = new Bundle();
            //전달받은 기존 구성원 리스트 되돌려주기
            bundle1.putParcelableArrayList("itemList" , bundle.getParcelableArrayList("itemList"));

            //추가한 구성원 리스트 전달하기.
            bundle1.putParcelableArrayList("InputMember", mListview_DirectInputParticipantsAdapter.getList());
            bundle1.putParcelableArrayList("telephoneInputMember", null);
            mMyApplication.getMyGroup_EditFragment().setArguments(bundle1);

            //현재 프래그먼트 제거
            mFragmentManager.popBackStack();
        }
    }

    /**
     * 뒤로가기 이벤트 처리
     */
    @Override
    public void clickBackPressed() {
        mView.showWarningDialog("경고", "정말로 추가를 그만하시겠습니까?\n입력된 정보는 저장되지 않습니다.");
    }

    /**
     * 경고다이얼로그 확인 버튼 이벤트 처리
     */
    @Override
    public void clickWarningDialogOK(Bundle bundle) {

        Bundle bundle1 = new Bundle();

        //전달받은 기존 구성원 리스트 되돌려주기
        bundle1.putParcelableArrayList("itemList" , bundle.getParcelableArrayList("itemList"));

        //아무것도 전달하지 않는다.
        bundle1.putParcelableArrayList("InputMember", null);
        bundle1.putParcelableArrayList("telephoneInputMember", null);
        mMyApplication.getMyGroup_EditFragment().setArguments(bundle1);

        //현재 프래그먼트 제거
        mFragmentManager.popBackStack();
    }
}
