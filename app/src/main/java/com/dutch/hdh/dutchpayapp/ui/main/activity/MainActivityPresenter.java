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
import com.dutch.hdh.dutchpayapp.ui.event.main.Event_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.login.LoginFragment;
import com.dutch.hdh.dutchpayapp.ui.main.fragment.MainFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.directinput.MyGroup_DirectInputFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.main.MyGroup_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory.MyGroup_TelephoneDirectoryFragment;
import com.dutch.hdh.dutchpayapp.ui.mypage.main.MyPage_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.register.allview.Register_ViewAllTermsConditionsFragment;
import com.dutch.hdh.dutchpayapp.ui.register.form.Register_FormFragment;
import com.dutch.hdh.dutchpayapp.ui.register.password.Register_PaymentPasswordFragment;
import com.dutch.hdh.dutchpayapp.ui.register.success.Register_SuccessFragment;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;
import com.dutch.hdh.dutchpayapp.ui.solopay.SoloPayFragment;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;

    private long mLastTime;

    private DrawerLayout mDrawerLayout;

    private MainFragment mMainFragment;
    private LoginFragment mLoginFragment;

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
        mMainFragment = new MainFragment();

        mMyApplication = MyApplication.getInstance();
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

        else if(getCurrentFragment() instanceof SoloPayFragment){
            mView.changeTitle("결제");
            mView.hideBell();
            mView.showExit();
        }

        else if(getCurrentFragment() instanceof MyGroup_MainFragment){
            mView.changeTitle("My 그룹");
            mView.hideBell();
            mView.showExit();
        }

        else if(getCurrentFragment() instanceof MyGroup_EditFragment){
            if(mMyApplication.entranceGroupPath) {
                mView.changeTitle("My 그룹 편집");
            } else {
                mView.changeTitle("My 그룹 신규추가");
            }
            mView.hideBell();
            mView.showExit();
        }

        else if(getCurrentFragment() instanceof MyGroup_TelephoneDirectoryFragment){
            mView.changeTitle("전화부호부");
            mView.hideBell();
            mView.showExit();
        }

        else if(getCurrentFragment() instanceof Event_MainFragment){
            mView.changeTitle("이벤트");
            mView.hideBell();
            mView.showExit();
        }

        else if(getCurrentFragment() instanceof MyPage_MainFragment){
            mView.changeTitle("내정보");
            mView.hideBell();
            mView.showExit();
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
        mView.showUserInfo(mMyApplication.getUserInfo().isUserState());
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
            if (fragmentList.get(i) instanceof MyGroup_DirectInputFragment) {
                ((MyGroup_DirectInputFragment) fragmentList.get(i)).onBackPressed();
                return;
            }
            if (fragmentList.get(i) instanceof MyGroup_EditFragment) {
                ((MyGroup_EditFragment) fragmentList.get(i)).onBackPressed();
                return;
            }
            if (fragmentList.get(i) instanceof MyGroup_TelephoneDirectoryFragment) {
                ((MyGroup_TelephoneDirectoryFragment) fragmentList.get(i)).onBackPressed();
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

        if (getCurrentFragment() instanceof LoginFragment)
            return;

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

        if (getCurrentFragment() instanceof Register_TermsConditionsAgreementFragment)
            return;

        setDefaultMainStack();
        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyApplication.getRegister_TermsConditionsAgreementFragment(), Register_TermsConditionsAgreementFragment.class.getName());
        fragmentTransaction.addToBackStack(Register_TermsConditionsAgreementFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 개인결제 클릭 이벤트 처리
     */
    @Override
    public void clickSolopayStart() {
        //메뉴 닫기 , 프래그먼트 닫기
        mView.hideDrawerLayout();

        if (getCurrentFragment() instanceof SoloPayFragment)
            return;

        if(!mMyApplication.getUserInfo().isUserState()){
            mView.showFailDialog("실패" , "로그인을 해주세요.");
            return;
        }

        setDefaultMainStack();

        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
        SoloPayFragment soloPayFragment = new SoloPayFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, soloPayFragment, SoloPayFragment.class.getName());
        fragmentTransaction.addToBackStack(SoloPayFragment.class.getName());
        fragmentTransaction.commit();
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
        //메뉴 닫기 , 프래그먼트 닫기
        mView.hideDrawerLayout();

        if (getCurrentFragment() instanceof Event_MainFragment)
            return;

        setDefaultMainStack();

        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        Event_MainFragment event_MainFragment = new Event_MainFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, event_MainFragment, Event_MainFragment.class.getName());
        fragmentTransaction.addToBackStack(Event_MainFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * My 페이지 클릭 이벤트 처리
     */
    @Override
    public void clickMyPage() {
        //메뉴 닫기 , 프래그먼트 닫기
        mView.hideDrawerLayout();

        if (getCurrentFragment() instanceof MyPage_MainFragment)
            return;

        if(!mMyApplication.getUserInfo().isUserState()){
            mView.showFailDialog("실패" , "로그인을 해주세요.");
            return;
        }
        setDefaultMainStack();

        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        MyPage_MainFragment mMyPage_MainFragment = new MyPage_MainFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyPage_MainFragment, MyPage_MainFragment.class.getName());
        fragmentTransaction.addToBackStack(MyPage_MainFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * My 그룹 클릭 이벤트 처리
     */
    @Override
    public void clickMyGroup() {
        //메뉴 닫기 , 프래그먼트 닫기
        mView.hideDrawerLayout();

        if (getCurrentFragment() instanceof MyGroup_MainFragment)
            return;


        if(!mMyApplication.getUserInfo().isUserState()){
            mView.showFailDialog("실패" , "로그인을 해주세요.");
            return;
        }

        setDefaultMainStack();

        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        MyGroup_MainFragment mMyGroup_MainFragment = new MyGroup_MainFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyGroup_MainFragment, MyGroup_MainFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_MainFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * My 지갑 클릭 이벤트 처리
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
        mMyApplication.getUserInfo().setUserState(false);
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
