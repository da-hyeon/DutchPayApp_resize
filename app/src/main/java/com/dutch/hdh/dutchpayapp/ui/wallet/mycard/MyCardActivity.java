package com.dutch.hdh.dutchpayapp.ui.wallet.mycard;

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

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardInfoBinding;
import com.dutch.hdh.dutchpayapp.ui.view.CommonDialogView;
import com.dutch.hdh.dutchpayapp.util.Trace;

import java.util.ArrayList;

public class MyCardActivity extends BaseActivity implements MyCardContract.View {

    private ActivityCardInfoBinding mBinding;
    private MyCardContract.Presenter mPresenter;
    private MyApplication mMyApplication;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_card_info);

    }

    @Override
    public void initData() {
        mPresenter = new MyCardPresenter(this, this);
        mMyApplication = MyApplication.getInstance();


        //취소
        mBinding.tvCancel.setOnClickListener(v -> {
                    mPresenter.clickCancel();
                }
        );
        //카드등록
        mBinding.llCardAdd.setOnClickListener(v -> {
                    mPresenter.clickCardAdd();
                }
        );
        mBinding.ivCardRegistrationManagement.setOnClickListener(v -> {
                    mPresenter.clickCardManagement();
                }
        );
        //카드 목록 버튼
        mBinding.ivLeftArrow.setOnClickListener(v ->
                mPresenter.clickLeft(mBinding.vpCardList, mBinding.tlIndicator)
        );
        mBinding.ivRightArrow.setOnClickListener(v -> {
//                    Log.e("테스트", mBinding.vpCardList.getAdapter().getCount() + "");
                    mPresenter.clickRight(mBinding.vpCardList, mBinding.tlIndicator);
                }

        );
        mPresenter.getRegisterCardList();
    }

    @Override
    public void setRegisterCardList(ArrayList<CardRegisterList.CardRegisterListResult> cardRegisterListResultArrayList) {
        if (cardRegisterListResultArrayList.size() == 0) {
            mBinding.vpCardList.setVisibility(View.GONE);
        } else {
            mBinding.vpCardList.setVisibility(View.VISIBLE);
        }
        CardImageSliderAdapter cardImageSliderAdapter = new CardImageSliderAdapter(this, cardRegisterListResultArrayList, this);
        mBinding.vpCardList.setAdapter(cardImageSliderAdapter);
        mBinding.tlIndicator.setupWithViewPager(mBinding.vpCardList, true);
        mPresenter.checkViewPager(mBinding.vpCardList, mBinding.tlIndicator);
    }

    @Override
    public void showRepresentativeCardDialog(String mainCardCode, String subCardCode) {

        Dialog build = new Dialog(this);
        build.requestWindowFeature(Window.FEATURE_NO_TITLE);
        build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        build.setContentView(new CommonDialogView(this, "대표카드 등록", "선택하신 카드를 대표결제 카드로 등록하시겠습니까?", false, v ->  {
            if (v.getId() == R.id.ivConfirm) {
                mPresenter.setRepresentativeCard(mainCardCode, subCardCode);
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
