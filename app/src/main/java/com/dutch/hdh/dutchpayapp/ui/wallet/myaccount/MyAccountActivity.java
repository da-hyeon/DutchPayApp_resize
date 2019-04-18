package com.dutch.hdh.dutchpayapp.ui.wallet.myaccount;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.AccountRegisterListAdapter;
import com.dutch.hdh.dutchpayapp.adapter.CardImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.data.db.AccountRegisterList;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.databinding.ActivityAccountInfoBinding;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardInfoBinding;
import com.dutch.hdh.dutchpayapp.ui.view.CommonDialogView;
import com.dutch.hdh.dutchpayapp.ui.wallet.mycard.MyCardContract;
import com.dutch.hdh.dutchpayapp.ui.wallet.mycard.MyCardPresenter;

import java.util.ArrayList;

public class MyAccountActivity extends BaseActivity implements MyAccountContract.View {

    private ActivityAccountInfoBinding mBinding;
    private MyAccountContract.Presenter mPresenter;
    private MyApplication mMyApplication;
    private AccountRegisterListAdapter mAccountRegisterListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_account_info);
    }

    @Override
    public void initData() {

        mPresenter = new MyAccountPresenter(this, this);
        mMyApplication = MyApplication.getInstance();


        //취소
        mBinding.tvCancel.setOnClickListener(v ->
                mPresenter.clickCancel()

        );
        //계좌등록
        mBinding.llAccountAdd.setOnClickListener(v ->
                mPresenter.clickAccountAdd()

        );
        //계좌등록
        mBinding.ivAccountAdd.setOnClickListener(v ->
                mPresenter.clickAccountAdd()
        );

        mBinding.lvAccountList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AccountRegisterList.AccountRegisterListResult accountRegisterListResult = (AccountRegisterList.AccountRegisterListResult) mAccountRegisterListAdapter.getItem(position);

                if (mAccountRegisterListAdapter.getRepresentativeAccount().getAccount_No() == accountRegisterListResult.getAccount_No()) {
                    return;
                } else {
                    showRepresentativeAccountDialog(mAccountRegisterListAdapter.getRepresentativeAccount().getAccount_Code(), accountRegisterListResult.getAccount_Code());
                }

            }
        });

        mPresenter.getRegisterAccountList();
    }


    @Override
    public void showRepresentativeAccountDialog(String mainAccountCode, String subAccountCode) {
        Dialog build = new Dialog(this);
        build.requestWindowFeature(Window.FEATURE_NO_TITLE);
        build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        build.setContentView(new CommonDialogView(this, "대표계좌 등록", "선택하신 계좌를 대표결제 계좌로 등록하시겠습니까?", false, v -> {
            if (v.getId() == R.id.ivConfirm) {
                mPresenter.setRepresentativeAccount(mainAccountCode, subAccountCode);
                build.dismiss();
            } else {
                build.dismiss();
            }
        }));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(build.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.TOP;
        build.show();
        Window window = build.getWindow();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void setRegisterAccountList(ArrayList<AccountRegisterList.AccountRegisterListResult> accountRegisterListResultArrayList) {


        if (accountRegisterListResultArrayList.size() != 0) {
            for (int i = 0; i < accountRegisterListResultArrayList.size(); i++) {
                if (mPresenter.isRepresentativeAccountNumber(accountRegisterListResultArrayList.get(i).getAccount_Choice())) {
                    mBinding.tvAccountNumber.setText(accountRegisterListResultArrayList.get(i).getAccount_No());
                    mBinding.ivBankImage.setBackgroundResource(mPresenter.getBankImage(accountRegisterListResultArrayList.get(i).getAccount_TypeCode()));
                    mBinding.llRepresentativeAccount.setBackgroundColor(Color.parseColor(mPresenter.getBankBackgroundColor(accountRegisterListResultArrayList.get(i).getAccount_TypeCode())));
                }
            }
            mBinding.flAccountAdd.setVisibility(View.GONE);
            mBinding.flAccountInfo.setVisibility(View.VISIBLE);
            mAccountRegisterListAdapter = new AccountRegisterListAdapter(this, accountRegisterListResultArrayList, this, mPresenter);
            mBinding.lvAccountList.setAdapter(mAccountRegisterListAdapter);
        } else {
            mBinding.flAccountAdd.setVisibility(View.VISIBLE);
            mBinding.flAccountInfo.setVisibility(View.GONE);
        }
    }


    @Override
    public void showDeleteAccountDialog(String accountCode) {
        Dialog build = new Dialog(this);
        build.requestWindowFeature(Window.FEATURE_NO_TITLE);
        build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        build.setContentView(new CommonDialogView(this, "계좌 삭제", "선택하신 계좌를 삭제하시겠습니까?", false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.ivConfirm) {
                    mPresenter.setAccountDelete(accountCode);
                    build.dismiss();
                } else {
                    build.dismiss();
                }
            }
        }));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(build.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.TOP;
        build.show();
        Window window = build.getWindow();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void showCommonDialog(String title, String content, boolean isBack) {
        super.showCommonDialog(title, content, isBack);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
