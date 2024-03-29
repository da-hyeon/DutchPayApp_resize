package com.dutch.hdh.dutchpayapp.ui.register.term;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentRegisterTermsConditionsAgreementBinding;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

public class Register_TermsConditionsAgreementFragment extends BaseFragment implements Register_TermsConditionsAgreementContract.View{

    private FragmentRegisterTermsConditionsAgreementBinding mBinding;
    private Register_TermsConditionsAgreementPresenter mPresenter;

    private CheckBox mTermsConditions[];
    private ImageView mAllViewImage[];
    private View mTermView[];
    private View mAllView[];

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_register_terms_conditions_agreement, container, false);

        initData();

        //전체 동의 클릭
        mBinding.cbCompleteAgreement.setOnClickListener(v ->
                mPresenter.clickAllTOS(mBinding.cbCompleteAgreement.isChecked())
        );

        //전체동의 클릭
        mBinding.vAgreeAllTerm.setOnClickListener(v->{
            //클릭처리
            mBinding.cbCompleteAgreement.setChecked(!mBinding.cbCompleteAgreement.isChecked());
            mPresenter.clickAllTOS(mBinding.cbCompleteAgreement.isChecked());
        });

        //약관 동의 클릭
        for(int i  = 0 ; i < mTermsConditions.length; i++){
            int finalI = i;
            mTermsConditions[i].setOnClickListener(v->
                mPresenter.clickTOS(finalI, mTermsConditions[finalI].isChecked())
            );
        }

        //약관 동의 클릭
        for(int i  = 0 ; i < mTermView.length; i++){
            int finalI = i;
            mTermView[i].setOnClickListener(v->{
                    //클릭처리
                mTermsConditions[finalI].setChecked(!mTermsConditions[finalI].isChecked());
                mPresenter.clickTOS(finalI, mTermsConditions[finalI].isChecked());
            });
        }

        //전체보기 클릭
        for(int i = 0; i < mAllView.length; i++){
            int finalI = i;
            mAllView[i].setOnClickListener(v ->
                mPresenter.clickAllView(finalI)
            );
        }
        
        //회원가입 클릭
        mBinding.imageViewRegister.setOnClickListener(v->
                mPresenter.clickRegister()
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    @Override
    public void initData() {
        mPresenter = new Register_TermsConditionsAgreementPresenter(this, getContext(), getFragmentManager());

        mTermsConditions = new CheckBox[]{
                mBinding.cbTermsConditions1,
                mBinding.cbTermsConditions2,
                mBinding.cbTermsConditions3,
                mBinding.cbTermsConditions4,
                mBinding.cbTermsConditions5,
                mBinding.cbTermsConditions6
        };


        mAllViewImage = new ImageView[]{
                mBinding.viewAll1,
                mBinding.viewAll2,
                mBinding.viewAll3,
                mBinding.viewAll4,
                mBinding.viewAll5,
                mBinding.viewAll6,
        };

        mAllView = new View[]{
                mBinding.vAllView1,
                mBinding.vAllView2,
                mBinding.vAllView3,
                mBinding.vAllView4,
                mBinding.vAllView5,
                mBinding.vAllView6
        };

        mTermView = new View[]{
                mBinding.vAgreeTerm1,
                mBinding.vAgreeTerm2,
                mBinding.vAgreeTerm3,
                mBinding.vAgreeTerm4,
                mBinding.vAgreeTerm5,
                mBinding.vAgreeTerm6,
        };

        mPresenter.refreshData(getArguments());
    }

    @Override
    public void showDialog(String content) {
        new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.WARNING_TYPE)
                .setTitleText("실패")
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(KAlertDialog::dismissWithAnimation)
                .show();
    }

    @Override
    public void changeAllTOS(boolean state) {
        mBinding.cbCompleteAgreement.setChecked(state);
    }

    @Override
    public void changeTOS(int index, boolean state) {
        mTermsConditions[index].setChecked(state);
    }

    @Override
    public void changeAllView(int index, int id) {
        mAllViewImage[index].setImageResource(id);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }
}
