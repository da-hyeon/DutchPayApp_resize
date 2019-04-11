package com.dutch.hdh.dutchpayapp.ui.mypage.change_email;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangeEmailBinding;


public class MyPage_ChangeEmailActivity extends BaseActivity implements MyPage_ChangeEmailContract.View {

    private MyPage_ChangeEmailContract.Presenter mPresenter;
    private ActivityMyPageChangeEmailBinding mBinding;
    private EditText[] mEditTextArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_page_change_email);
        mBinding.setEmailActivity(this);
        mPresenter = new MyPage_ChangeEmailPresenter(this, this, getSupportFragmentManager());

        initData();

        //취소클릭
        mBinding.vCancel.setOnClickListener(v ->
                mPresenter.clickCancel()
        );

        mBinding.btChange.setOnClickListener(v ->
                mPresenter.clickChange(mEditTextArray)
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
        mPresenter.setVIew();

        mEditTextArray = new EditText[]{
                mBinding.etNewEmail,
                mBinding.etNewEmailCheck,
                mBinding.etPassword
        };
    }

    @Override
    public void changeEmailText(String email) {
        mBinding.tvMyEmail.setText(email);
    }

    @Override
    public void onBackPressed() {
        mPresenter.clickCancel();
    }
}
