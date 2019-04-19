package com.dutch.hdh.dutchpayapp.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.DialogCommonBinding;

public class CommonDialogView extends LinearLayout {

    private Context mContext;
    private OnClickListener mOnClickListener;
    private DialogCommonBinding mBinding;
    private String mTitle;
    private String mContent;
    private boolean mIsCancelGone;


    public CommonDialogView(Context context, String title, String content, boolean ivCancelGone, OnClickListener onClickListener) {
        super(context);
        this.mContext = context;
        this.mTitle = title;
        this.mContent = content;
        this.mIsCancelGone = ivCancelGone;
        this.mOnClickListener = onClickListener;

        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_common, this, true);
        init();
    }

    private void init() {
        if (mIsCancelGone) {
            mBinding.ivCancel.setVisibility(GONE);
        }
        mBinding.ivCancel.setOnClickListener(mOnClickListener);
        mBinding.ivConfirm.setOnClickListener(mOnClickListener);
        mBinding.tvTitle.setText(mTitle);
        mBinding.tvContent.setText(mContent);
    }

}
