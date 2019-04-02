package com.dutch.hdh.dutchpayapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;


public class BaseFragment extends Fragment {

    private MainActivity mMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mMainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainActivity.checkUiAndTitle();
    }

    /**
     * 메인 프래그먼트 빼고 모두 스택에서 제거
     */
    public void setDefaultMainStack(){
        FragmentManager fragmentManager = getFragmentManager();
        int count = fragmentManager.getBackStackEntryCount() - 1;
        for (int i = 0; i < count; ++i) {
            fragmentManager.popBackStack();
        }
    }
}
