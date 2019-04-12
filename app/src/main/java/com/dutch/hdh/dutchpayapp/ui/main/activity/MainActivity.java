package com.dutch.hdh.dutchpayapp.ui.main.activity;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMainBinding;
import com.kinda.alert.KAlertDialog;

public class MainActivity extends BaseActivity implements MainActivityContract.View {

    private MyApplication myApplication;

    private ActivityMainBinding mBinding;
    public MainActivityContract.Presenter mPresenter;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setMainActiviy(this);
        mPresenter = new MainActivityPresenter(this, this, getSupportFragmentManager(), mBinding.drawerLayout, this);
        toggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //객체생성 및 데이터 초기화
        initData();


        mBinding.navigationView.tvNameTitle.setSelected(true);

        //메뉴버튼
        mBinding.Appbar.loMenu.setOnClickListener(v ->
                mPresenter.clickMenu()
        );

        //뒤로 버튼
        mBinding.Appbar.loLeftIcon.setOnClickListener(v -> {
                    if (mBinding.Appbar.imageBack.getVisibility() == View.VISIBLE) {
                        mPresenter.clickBack();
                    }
                }
        );

        //나가기 버튼
        mBinding.navigationView.imageExit.setOnClickListener(v ->
                mPresenter.clickExit()
        );

        //로그인 버튼
        mBinding.navigationView.llLogin.setOnClickListener(v ->
                mPresenter.clickLogin()
        );

        //회원가입 버튼
        mBinding.navigationView.llRegister.setOnClickListener(v ->
                mPresenter.clickRegister()
        );

        //개인결제 시작하기 버튼
        mBinding.navigationView.llSolopayStart.setOnClickListener(v ->
                mPresenter.clickSolopayStart()
        );

        //더치페이 시작하기 버튼
        mBinding.navigationView.llDutchpayStart.setOnClickListener(v ->
                {}
        );

        //이벤트 버튼
        mBinding.navigationView.llEvent.setOnClickListener(v ->
                mPresenter.clickEvent()
        );

        //My 페이지 버튼
        mBinding.navigationView.llMyPage.setOnClickListener(v ->
                mPresenter.clickMyPage()
        );

        //My 그룹 버튼
        mBinding.navigationView.llMyGroup.setOnClickListener(v ->
               mPresenter.clickMyGroup()
        );

        //My 지갑 버튼
        mBinding.navigationView.llMyWallet.setOnClickListener(v ->
                {}
        );

        //공지사항 버튼
        mBinding.navigationView.llNotice.setOnClickListener(v ->
                {}
        );

        //이용안내 버튼
        mBinding.navigationView.llService.setOnClickListener(v ->
                {}
        );

        //고객센터(로그아웃) 버튼
        mBinding.navigationView.llCustomerCenter.setOnClickListener(v ->
                mPresenter.clickLogout()
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    @Override
    public void initData() {
        //myApplication Activity 등록
        myApplication = MyApplication.getInstance();
        myApplication.setActivity(this);

        //drawerLayout 등록
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //메인 프래그먼트 등록
        mPresenter.initMainFragment();

        //로그인 상태 불러오기
        mPresenter.initLoginState();
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (myApplication.tutorialCheck) {
//
//            /*
//             * 튜토리얼 등록
//             */
//            mPresenter.initToturial(mBinding.Appbar.loMenu , "더보기 메뉴" , "여러 메뉴를 볼 수 있습니다.");
//            mPresenter.initToturial(mBinding.Appbar.loLeftIcon , "알람" , "새로운 정보를 표시합니다.");
//            mPresenter.initToturial(mBinding.fragmentMain.ivSoloPay , "개인결제", "개인으로 결제를 할 수 있습니다.");
//            mPresenter.initToturial(mBinding.fragmentMain.ivDutchPay ,  "더치페이", "원하는 사람들과 금액을 나눠서 결제를 할 수 있습니다.");
//            mPresenter.finishRegisterToturial();
//            /*
//             * 튜토리얼 시작
//             */
//
//            myApplication.tutorialCheck = false;
//        }
//    }

    /**
     * 뒤로가기 클릭
     */
    @Override
    public void onBackPressed() {
        mPresenter.clickBack();
    }

    /**
     * 메뉴 보이기
     */
    @Override
    public void showDrawerLayout() {
        mBinding.drawerLayout.openDrawer(GravityCompat.END);
    }

    /**
     * Toast 보이기
     */
    @Override
    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserInfo(boolean state) {
        if (state) {
            //레이아웃 숨기기
            mBinding.navigationView.layoutLogin.setVisibility(View.GONE);

            //버튼,글씨 숨기기
            mBinding.navigationView.imageLogin.setVisibility(View.GONE);
            mBinding.navigationView.imageRegister.setVisibility(View.GONE);
            mBinding.navigationView.tvLogin.setVisibility(View.GONE);
            mBinding.navigationView.tvRegister.setVisibility(View.GONE);

            mBinding.navigationView.tvNameTitle.setText(myApplication.getUserInfo().getUserName() + "님, 안녕하세요!");
        } else {
            //레이아웃 보이기
            mBinding.navigationView.layoutLogin.setVisibility(View.VISIBLE);

            //버튼,글씨 보이기
            mBinding.navigationView.imageLogin.setVisibility(View.VISIBLE);
            mBinding.navigationView.imageRegister.setVisibility(View.VISIBLE);
            mBinding.navigationView.tvLogin.setVisibility(View.VISIBLE);
            mBinding.navigationView.tvRegister.setVisibility(View.VISIBLE);

            mBinding.navigationView.tvNameTitle.setText("에 오신것을 환영합니다.");
        }
    }

    /**
     * 벨 보이기
     */
    @Override
    public void showBell() {
        mBinding.Appbar.ivBell.setVisibility(View.VISIBLE);
    }

    /**
     * 나가기 보이기
     */
    @Override
    public void showExit() {
        mBinding.Appbar.imageBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFailDialog(String title ,String content) {
        new KAlertDialog(this, KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> sDialog.dismissWithAnimation())
                .show();
    }

    /**
     * 타이틀 변경하기
     */
    @Override
    public void changeTitle(String title) {
        mBinding.Appbar.txtAppBar.setText(title);
    }

    /**
     * 현재화면 체크 및 Title 변경하기
     */
    public void checkUiAndTitle() {
        mPresenter.onFragmentResume();
    }

    /**
     * 메뉴 감추기
     */
    @Override
    public void hideDrawerLayout() {
        mBinding.drawerLayout.closeDrawer(GravityCompat.END);
    }

    /**
     * 벨 감추기
     */
    @Override
    public void hideBell() {
        mBinding.Appbar.ivBell.setVisibility(View.GONE);
    }

    /**
     * 나가기 감추기
     */
    @Override
    public void hideExit() {
        mBinding.Appbar.imageBack.setVisibility(View.GONE);
    }

    /**
     * 앱 종료
     */
    @Override
    public void removeActivity() {
        finish();
    }
}