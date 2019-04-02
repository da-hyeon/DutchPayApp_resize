package com.dutch.hdh.dutchpayapp.ui.main.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.login.LoginFragment;
import com.dutch.hdh.dutchpayapp.ui.main.fragment.MainFragment;
import com.dutch.hdh.dutchpayapp.ui.register.allview.Register_ViewAllTermsConditionsFragment;
import com.dutch.hdh.dutchpayapp.ui.register.form.Register_FormFragment;
import com.dutch.hdh.dutchpayapp.ui.register.password.Register_PaymentPasswordFragment;
import com.dutch.hdh.dutchpayapp.ui.register.success.Register_SuccessFragment;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private FragmentManager mFragmentManager;
    private MyApplication myApplication;

    private long mLastTime;

    private DrawerLayout mDrawerLayout;

    private MainFragment mMainFragment;
    private LoginFragment mLoginFragment;
    public Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment;

    //private ArrayList<SimpleTarget> targetArrayList;

    /**
     * 생성자
     */
    public MainActivityPresenter(MainActivityContract.View mView, Context mContext, FragmentManager mFragmentManager, DrawerLayout mDrawerLayout, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mDrawerLayout = mDrawerLayout;
        this.mActivity = mActivity;

        mLoginFragment = new LoginFragment();
        mRegister_termsConditionsAgreementFragment = Register_TermsConditionsAgreementFragment.getInstance();
        mMainFragment = new MainFragment();

        myApplication = MyApplication.getInstance();
        //targetArrayList = new ArrayList<>();
    }

    /**
     * MainFragmnet 등록
     */
    @Override
    public void onFragmentResume() {
        if (getCurrentFragment() instanceof LoginFragment) {
            mView.changeTitle("로그인");
            mView.hideBell();
            mView.showExit();
        }
        else if (getCurrentFragment() instanceof Register_TermsConditionsAgreementFragment ||
                getCurrentFragment() instanceof Register_ViewAllTermsConditionsFragment ||
                getCurrentFragment() instanceof Register_FormFragment ||
                getCurrentFragment() instanceof Register_PaymentPasswordFragment) {
            mView.changeTitle("회원가입");
            mView.hideBell();
            mView.showExit();
        }
        else if (getCurrentFragment() instanceof Register_SuccessFragment) {
            mView.changeTitle("회원가입 완료");
        }

        else if (getCurrentFragment() instanceof MainFragment) {
            initLoginState();
            mView.changeTitle("");
            mView.showBell();
            mView.hideExit();
        }
    }

    /**
     * MainFragmnet 등록
     */
    @Override
    public void initMainFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (mMainFragment == null) {
            mMainFragment = (MainFragment) mFragmentManager.findFragmentByTag(MainFragment.class.getName());
            if (mMainFragment == null) {
                mMainFragment = new MainFragment();
            }
        }
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
        fragmentTransaction.replace(R.id.flFragmentContainer, mMainFragment, MainFragment.class.getName());
        fragmentTransaction.addToBackStack(MainFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 로그인 상태 확인 후 전달
     */
    @Override
    public void initLoginState() {
        mView.showUserInfo(myApplication.getUserInfo().isUserState());
    }

// 튜토리얼
//    @Override
//    public void initToturial(ImageView imageView, String title, String content) {
//        ImageView image = imageView;
//        int[] imageLocation = new int[2];
//        image.getLocationInWindow(imageLocation);
//        float imageX = imageLocation[0] + image.getWidth() / 2f;
//        float imageY = imageLocation[1] + image.getHeight() / 2f;
//        float imageRadius = image.getWidth() / 2f;
//
//        targetArrayList.add(
//                new SimpleTarget.Builder(mActivity)
//                .setPoint(imageX, imageY)
//                .setShape(new Circle(imageRadius))
//                .setTitle(title)
//                .setDescription(content)
//                .build());
//    }
//
//    @Override
//    public void initToturial(LinearLayout linearLayout, String title, String content) {
//        LinearLayout layout = linearLayout;
//        int[] layoutLocation = new int[2];
//        layout.getLocationInWindow(layoutLocation);
//        float layoutX = layoutLocation[0] + layout.getWidth() / 2f;
//        float layoutY = layoutLocation[1] + layout.getHeight() / 2f;
//        float layoutRadius = layout.getWidth() / 2f;
//
//        targetArrayList.add(
//                new SimpleTarget.Builder(mActivity)
//                .setPoint(layoutX, layoutY)
//                .setShape(new Circle(layoutRadius))
//                .setTitle(title)
//                .setDescription(content)
//                .build());
//    }
//
//    @Override
//    public void finishRegisterToturial() {
//        Spotlight.with(mActivity)
//                .setOverlayColor(R.color.background1)
//                .setDuration(1000L)
//                .setAnimation(new DecelerateInterpolator(2f))
//                .setTargets(targetArrayList.get(0), targetArrayList.get(1) , targetArrayList.get(2), targetArrayList.get(3) )
//                .setClosedOnTouchedOutside(true)
//                .start();
//    }


    /**
     * 메뉴 클릭 이벤트 처리
     */
    @Override
    public void clickMenu() {
        mView.showDrawerLayout();
    }

    /**
     * 나가기 클릭 이벤트 처리
     */
    @Override
    public void clickExit() {
        mView.hideDrawerLayout();
    }

    /**
     * 뒤로가기 이벤트 처리
     */
    @Override
    public void clickBack() {

        List fragmentList = mFragmentManager.getFragments();
        for (int i = 0; i < fragmentList.size(); i++) {
            if (fragmentList.get(i) instanceof Register_ViewAllTermsConditionsFragment) {
                ((Register_ViewAllTermsConditionsFragment) fragmentList.get(i)).onBackPress();
                return;
            }
        }


        if (mFragmentManager.getBackStackEntryCount() > 1) {
            mFragmentManager.popBackStack();
            return;
        }

        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mView.hideDrawerLayout();
            return;
        }

        exitApp();
    }

    /**
     * 로그인 클릭 이벤트 처리
     */
    @Override
    public void clickLogin() {
        //메뉴 닫기 , 프래그먼트 닫기
        mView.hideDrawerLayout();
        setDefaultMainStack();

        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
        fragmentTransaction.replace(R.id.flFragmentContainer, mLoginFragment, LoginFragment.class.getName());
        fragmentTransaction.addToBackStack(LoginFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 회원가입 클릭 이벤트 처리
     */
    @Override
    public void clickRegister() {
        //메뉴 닫기 , 프래그먼트 닫기
        mView.hideDrawerLayout();
        setDefaultMainStack();

        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
        fragmentTransaction.replace(R.id.flFragmentContainer, mRegister_termsConditionsAgreementFragment, Register_TermsConditionsAgreementFragment.class.getName());
        fragmentTransaction.addToBackStack(Register_TermsConditionsAgreementFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 개인결제 클릭 이벤트 처리
     */
    @Override
    public void clickSolopayStart() {

    }

    /**
     * 더치페이시작하기 클릭 이벤트 처리
     */
    @Override
    public void clickDutchpayStart() {

    }

    /**
     *  Event 클릭 이벤트 처리
     */
    @Override
    public void clickEvent() {

    }

    /**
     * My그룹 클릭 이벤트 처리
     */
    @Override
    public void clickMyGroup() {

    }

    /**
     * My지갑 클릭 이벤트 처리
     */
    @Override
    public void clickMyWallet() {

    }

    /**
     * 공지사항 클릭 이벤트 처리
     */
    @Override
    public void clickNotice() {

    }

    /**
     * 이용안내 클릭 이벤트 처리
     */
    @Override
    public void clickService() {

    }

    /**
     * 고객센터 클릭 이벤트 처리
     */
    @Override
    public void clickCustomerCenter() {

    }

    /**
     * 로그아웃 클릭 이벤트 처리
     */
    @Override
    public void clickLogout() {
        mView.hideDrawerLayout();

        setDefaultMainStack();
        myApplication.getUserInfo().setUserState(false);
        initLoginState();

        mMainFragment.showUserInfo(null, 0, false) ;
    }

    /**
     * 현재 보여지고 있는 fragment 가져오기
     */
    private Fragment getCurrentFragment() {
        if (mFragmentManager == null) {
            return null;
        }
        int count = mFragmentManager.getBackStackEntryCount();
        if (count > 0) {
            FragmentManager.BackStackEntry frgmt = mFragmentManager.getBackStackEntryAt(count - 1);
            return mFragmentManager.findFragmentByTag(frgmt.getName());
        }

        return null;
    }

    /**
     * 메인 프래그먼트 빼고 모두 스택에서 제거
     */
    private void setDefaultMainStack(){
        int count = mFragmentManager.getBackStackEntryCount() - 1;
        for (int i = 0; i < count; ++i) {
            mFragmentManager.popBackStack();
        }
    }

    /**
     * 두번 누르면 앱종료 2sec
     */
    private void exitApp() {
        if (mLastTime + 2000 < System.currentTimeMillis() || mLastTime == 0) {
            mView.showToast("한 번 더 누르면 종료됩니다.");
            mLastTime = System.currentTimeMillis();
        } else {
            mView.removeActivity();
        }
    }
}
