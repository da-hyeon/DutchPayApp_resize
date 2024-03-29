package com.dutch.hdh.dutchpayapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.dutch.hdh.dutchpayapp.data.db.PersonalPaymentInformation;
import com.dutch.hdh.dutchpayapp.data.db.Product;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;
import com.dutch.hdh.dutchpayapp.data.util.ServerAPI;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.DutchpayNewFragment;
import com.dutch.hdh.dutchpayapp.ui.dialog.payment_info.Payment_InfomationDialog;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private static MyApplication appInstance;

    public static boolean tutorialCheck ;

    //true = 편집 , false = 신규추가
    public static boolean entranceGroupPath;

    //true = 진행중 , false = 진행 종료.
    private static boolean onGoingCheck;

    //true = 로그인 , false = 비로그인
    public static boolean isLoginState;

    private static final String BASE_URL = "https://dutchkor02.cafe24.com/";

    //더치페이 그룹 체크용
    private boolean dutchpayGroup;
    private boolean dutchpayBack;

    //타임아웃
    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 15;
    private static final int READ_TIMEOUT = 15;
    private static OkHttpClient client;
    private static ServerAPI Interface;

    private UserInfo mUserInfo;
    private Product mProduct;
    private PersonalPaymentInformation mPersonalPaymentInformation;

    private Register_TermsConditionsAgreementFragment mRegister_termsConditionsAgreementFragment;
    private MyGroup_EditFragment mMyGroup_EditFragment;
    private DutchpayNewFragment mDutchpayNewFragment;
    private Payment_InfomationDialog mPayment_InfomationDialog;

    private Activity mActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        this.dutchpayGroup = false;
    }

    /**
     * MyApplication Singleton
     */
    public static MyApplication getInstance() {
        if (appInstance == null) {
            appInstance = new MyApplication();
            tutorialCheck = true;
        }
        return appInstance;
    }

    /**
     * UserInfo Singleton
     */
    public UserInfo getUserInfo() {
        if (mUserInfo == null) {
            mUserInfo = new UserInfo();
        }
        return mUserInfo;
    }
    public void setUserInfo(UserInfo mUserInfo) {
        this.mUserInfo = mUserInfo;
    }


    /**
     * Product Singleton
     */
    public Product getProduct() {
        if (mProduct == null)
            mProduct = new Product();

        return mProduct;
    }
    public void setProduct(Product mProduct) {
        this.mProduct = mProduct;
    }


    /**
     * PersonalPaymentInformation Singleton
     */
    public PersonalPaymentInformation getPersonalPaymentInformation() {
        if (mPersonalPaymentInformation == null)
            mPersonalPaymentInformation = new PersonalPaymentInformation("금홍짬뽕" , "2019-04-04" , 300000);

        return mPersonalPaymentInformation;
    }

    /**
     * Register_TermsConditionsAgreementFragment Singleton
     */
    public Register_TermsConditionsAgreementFragment getRegister_TermsConditionsAgreementFragment() {
        if (mRegister_termsConditionsAgreementFragment == null)
            mRegister_termsConditionsAgreementFragment = new Register_TermsConditionsAgreementFragment();

        return mRegister_termsConditionsAgreementFragment;
    }

    /**
     * MyGroup_MainFragment Singleton
     */
    public MyGroup_EditFragment getMyGroup_EditFragment() {
        if (mMyGroup_EditFragment == null)
            mMyGroup_EditFragment = new MyGroup_EditFragment();

        return mMyGroup_EditFragment;
    }

    /**
     * DutchpayNewFragment Singleton
     */
    public DutchpayNewFragment getDutchpayNewFragment() {
        if(mDutchpayNewFragment == null)
            mDutchpayNewFragment = new DutchpayNewFragment();
		return mDutchpayNewFragment;
    }

	/**
     * Payment_InfomationDialog Singleton
     */
    public Payment_InfomationDialog getPaymentDialog(Context context) {
        if (mPayment_InfomationDialog == null)
            mPayment_InfomationDialog = new Payment_InfomationDialog(context);

        return mPayment_InfomationDialog;
    }




    public void setDutchpayNewFragment(DutchpayNewFragment mDutchpayNewFragment) {
        this.mDutchpayNewFragment = mDutchpayNewFragment;
    }

    /**
     * getActivity
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * setActivity
     */
    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * ServerAPI Adapter
     */
    public synchronized static ServerAPI getRestAdapter() {
        if (Interface == null) {
            //통신로그를 확인하기 위한 부분
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //쿠키 메니저의 cookie policy를 변경 합니다.
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            //OkHttpClient를 생성합니다.
            client = configureClient(new OkHttpClient().newBuilder()) //인증서 무시
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
                    .cookieJar(new JavaNetCookieJar(cookieManager)) //쿠키메니져 설정
                    .addInterceptor(httpLoggingInterceptor) //http 로그 확인
                    .build();

            //Retrofit 설정
            Interface = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ServerAPI.class); //인터페이스 연결
        }
        return Interface;
    }

    /**
     * UnCertificated 허용
     */
    public static OkHttpClient.Builder configureClient(final OkHttpClient.Builder builder) {
        final TrustManager[] certs = new TrustManager[]{new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain,
                                           final String authType) {
            }

            @Override
            public void checkClientTrusted(final X509Certificate[] chain,
                                           final String authType) {
            }
        }};

        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, certs, new SecureRandom());
        } catch (final java.security.GeneralSecurityException ex) {
            ex.printStackTrace();
        }

        try {
            final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(final String hostname, final SSLSession session) {
                    return true;
                }
            };

            builder.sslSocketFactory(ctx.getSocketFactory()).hostnameVerifier(hostnameVerifier);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return builder;
    }


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public boolean isDutchpayGroup() {
        return dutchpayGroup;
    }

    public void setDutchpayGroup(boolean dutchpayGroup) {
        this.dutchpayGroup = dutchpayGroup;
    }

    public boolean isDutchpayBack() {
        return dutchpayBack;
    }

    public void setDutchpayBack(boolean dutchpayBack) {
        this.dutchpayBack = dutchpayBack;
    }
}
