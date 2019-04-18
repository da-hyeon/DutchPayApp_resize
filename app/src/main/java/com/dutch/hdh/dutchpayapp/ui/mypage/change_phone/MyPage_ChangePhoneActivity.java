package com.dutch.hdh.dutchpayapp.ui.mypage.change_phone;

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
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangePhoneBinding;

public class MyPage_ChangePhoneActivity extends BaseActivity implements MyPage_ChangePhoneContract.View {

    private MyPage_ChangePhoneContract.Presenter mPresenter;
    private ActivityMyPageChangePhoneBinding mBinding;
    private EditText[] mEditTextArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_my_page_change_phone);
        mBinding.setPhoneActivity(this);
        mPresenter = new MyPage_ChangePhonePresenter(this , this );
        initData();

        //취소
        mBinding.vCancel.setOnClickListener(v->
            mPresenter.clickCancel()
        );

        //변경 버튼 클릭
        mBinding.btChange.setOnClickListener(v->
            mPresenter.clickChange(mEditTextArray)
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
        mEditTextArray = new EditText[] {
                mBinding.etMyPhoneNumber,
                mBinding.etAuthNumber
        };
    }
}
