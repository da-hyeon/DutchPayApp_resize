package com.dutch.hdh.dutchpayapp.ui.mygroup.edit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupEditBinding;
import com.kinda.alert.KAlertDialog;

public class MyGroup_EditFragment extends BaseFragment implements MyGroup_EditContract.View {

    private FragmentMyGroupEditBinding mBinding;
    private MyGroup_EditContract.Presenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_group_edit, container, false);
        mPresenter = new MyGroup_EditPresenter(this, getContext(), getFragmentManager() , getArguments());

        initData();

        //전화부호부 클릭
        mBinding.btTelephoneDirectory.setOnClickListener(v -> {
            mPresenter.clickTelephoneDirectory();
        });

        //직접입력 클릭
        mBinding.btDirectoryInput.setOnClickListener(v -> {
            mPresenter.clickDirectInput();
        });

        //완료 버튼 클릭
        mBinding.btComplete.setOnClickListener(v->
            mPresenter.clickComplete(mBinding.etGroupName.getText().toString())
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter.initListViewData(mBinding.lvParticipantsList);
    }

    /**
     * 구성원 수 변경하기
     */
    @Override
    public void changePersonCount(int num) {
        mBinding.tvPersonCount.setText("( "+ num +"명 )");
    }

    /**
     * 그룹명 변경하기
     */
    @Override
    public void changeGroupName(String groupName) {
        mBinding.etGroupName.setText(groupName);
    }

    /**
     * 뒤로가기 버튼 클릭
     */
    public void onBackPressed() {
        mPresenter.clickBackPressed();
    }

    /**
     * Fragment 진입
     */
    @Override
    public void onResume() {
        super.onResume();

        //전달받은 bundle 갱신
        mPresenter.onResume(getArguments());

        //입장경로가 편집일때 해당 그룹의 구성원 불러오기
        mPresenter.receiveGroupMemberWhenEditing();

        //전달한 구성원 목록 되돌려받기
        mPresenter.returningMembers();

        //직접입력한 구성원 목록 전달받기
        mPresenter.receiveDirectlyEnteredMembers();

        //전화부호부에서 가져온 구성원 전달받기
        mPresenter.receiveTelephoneDirectoryMembers();

        //리스트 갱신 요청
        mPresenter.refreshData();
    }

}
