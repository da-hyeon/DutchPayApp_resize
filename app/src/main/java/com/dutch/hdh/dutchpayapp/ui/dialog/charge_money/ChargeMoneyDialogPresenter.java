package com.dutch.hdh.dutchpayapp.ui.dialog.charge_money;

import android.content.Context;

import com.dutch.hdh.dutchpayapp.R;

public class ChargeMoneyDialogPresenter implements ChargeMoneyDialogContract.Presenter {

    private ChargeMoneyDialogContract.View mView;
    private Context mContext;

    public ChargeMoneyDialogPresenter(ChargeMoneyDialogContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void inputAmountText(String num) {
        mView.showHangleText(converter(num));
    }

    @Override
    public void clickChargeAccount() {
        mView.changeChargeAccountBackgroundAndTextColor(true);
        mView.changeChargeCardBackgroundAndTextColor(false);
        mView.changeInfoText(mContext.getResources().getString(R.string.solopay_chargeMoney_dialog_accountInfoText));
    }

    @Override
    public void clickChargeCard() {
        mView.changeChargeAccountBackgroundAndTextColor(false);
        mView.changeChargeCardBackgroundAndTextColor(true);
        mView.changeInfoText(mContext.getResources().getString(R.string.solopay_chargeMoney_dialog_cardInfoText));
    }

    @Override
    public void clickCancel() {
        mView.removeDialog();
    }

    public String converter(String amount) {
        if (!amount.equals("")) {
            //0보다 커야하고
            if (amount.length() > 0) {
                //200만원보다 크면 안됨
                if (Integer.parseInt(amount) > 2000000) {
                    mView.changeAmountText("2000000");
                    return "최대 200만원까지 송금할 수 있습니다.";
                }

                String[] han1 = {"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
                String[] han2 = {"", "십", "백", "천"};
                String[] han3 = {"", "만", "억", "조", "경"};

                StringBuffer result = new StringBuffer();
                int len = amount.length();

                for (int i = len - 1; i >= 0; i--) {
                    result.append(han1[Integer.parseInt(amount.substring(len - i - 1, len - i))]);
                    if (Integer.parseInt(amount.substring(len - i - 1, len - i)) > 0) {
                        result.append(han2[i % 4]);
                    }
                    if (i % 4 == 0)
                        result.append(han3[i / 4]);
                }

                return result.toString() + "원";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

}
