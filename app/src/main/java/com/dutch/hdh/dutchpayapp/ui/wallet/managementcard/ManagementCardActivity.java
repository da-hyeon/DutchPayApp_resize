package com.dutch.hdh.dutchpayapp.ui.wallet.managementcard;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardRegisterListAdapter;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardManagementBinding;
import com.dutch.hdh.dutchpayapp.ui.view.CommonDialogView;

import java.util.ArrayList;

public class ManagementCardActivity extends BaseActivity implements ManagementCardContract.View {

    private ActivityCardManagementBinding mBinding;
    private ManagementCardContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_card_management);
        mPresenter = new ManagementCardPresenter(this, this, mBinding);

    }

    @Override
    public void initData() {
        mBinding.tvCancel.setOnClickListener(v ->
                mPresenter.cancelClick()
        );

        mBinding.ivCardAdd.setOnClickListener(v ->
                mPresenter.clickCardAdd()
        );
        mPresenter.getRegisterCardList();
    }

    @Override
    public void setRegisterCardList(ArrayList<CardRegisterList.CardRegisterListResult> cardRegisterListResultArrayList) {
        if (cardRegisterListResultArrayList.size() == 0) {
            mBinding.lvCardList.setVisibility(View.GONE);
            mBinding.tvCheckCardList.setVisibility(View.VISIBLE);
        } else {
            mBinding.lvCardList.setVisibility(View.VISIBLE);
            mBinding.tvCheckCardList.setVisibility(View.GONE);
        }
        CardRegisterListAdapter cardRegisterListAdapter = new CardRegisterListAdapter(this, cardRegisterListResultArrayList, this, mPresenter);
        mBinding.lvCardList.setAdapter(cardRegisterListAdapter);
    }

    @Override
    public void showDeleteCardDialog(String cardCode) {
        Dialog build = new Dialog(this);
        build.requestWindowFeature(Window.FEATURE_NO_TITLE);
        build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        build.setContentView(new CommonDialogView(this, "카드 삭제", "선택하신 카드를 삭제하시겠습니까?", false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.ivConfirm) {
                    mPresenter.setCardDelete(cardCode);
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
