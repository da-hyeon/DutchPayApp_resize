package com.dutch.hdh.dutchpayapp.ui.find.password;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityFindPasswordBinding;
import com.dutch.hdh.dutchpayapp.ui.find.email.FindEmailPresenter;

public class FindPasswordActivity extends BaseActivity implements FindPasswordContract.View {

    private ActivityFindPasswordBinding mBinding;
    private FindPasswordContract.Presenter mPresenter;
    private EditText[] mEditTextArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_find_password);
        mBinding.setFindPasswordActivity(this);
        mPresenter = new FindPasswordPresenter(this, this);

        initData();

        //취소클릭
        mBinding.vCancel.setOnClickListener(v ->
                mPresenter.clickCancel()
        );

        //확인클릭
        mBinding.btOK.setOnClickListener(v->
                mPresenter.clickOKButton(mEditTextArray)
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
        mEditTextArray = new EditText[]{
                mBinding.etMyEmail,
                mBinding.etName,
                mBinding.etMyPhoneNumber,
                mBinding.etAuthNumber
        };
    }

    @Override
    public void showPasswordLayout() {
        mBinding.llPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePasswordLayout() {
        mBinding.llPassword.setVisibility(View.GONE);
    }
}
