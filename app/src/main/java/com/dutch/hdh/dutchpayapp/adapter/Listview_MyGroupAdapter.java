package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.GroupList;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.databinding.ItemMyGroupBinding;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.main.MyGroup_MainContract;
import com.dutch.hdh.dutchpayapp.ui.mygroup.main.MyGroup_MainPresenter;
import com.dutch.hdh.dutchpayapp.ui.register.password.Register_PaymentPasswordFragment;
import com.google.gson.Gson;
import com.kinda.alert.KAlertDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Listview_MyGroupAdapter extends BaseAdapter {


    private ItemMyGroupBinding mBinding;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private MyGroup_MainContract.View mView;

    private ArrayList<GroupList> mMyGroupArrayList;
    private MyApplication mMyApplication;

    public Listview_MyGroupAdapter(MyGroup_MainContract.View mView , Context mContext, ArrayList<GroupList> mMyGroupArrayList, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mMyGroupArrayList = mMyGroupArrayList;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public int getCount() {
        return mMyGroupArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMyGroupArrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_my_group, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ItemMyGroupBinding) v.getTag();
        }

        mBinding.tvPersonCount.setText(mMyGroupArrayList.get(position).getParticipantsCount() + "명");
        mBinding.tvGroupName.setText(mMyGroupArrayList.get(position).getGroupName());

        //편집 클릭
        mBinding.btEdit.setOnClickListener(v1 -> {
            mMyApplication.entranceGroupPath = true;

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

            Bundle bundle = new Bundle();
            bundle.putString("jsonString" , mMyGroupArrayList.get(position).getGroup_content_from_json());
            bundle.putString("groupName" , mMyGroupArrayList.get(position).getGroupName());
            bundle.putString("groupCode" , mMyGroupArrayList.get(position).getGroupID());

            mMyApplication.getMyGroup_EditFragment().setArguments(bundle);

            fragmentTransaction.replace(R.id.flFragmentContainer, mMyApplication.getMyGroup_EditFragment(), MyGroup_EditFragment.class.getName());
            fragmentTransaction.addToBackStack(MyGroup_EditFragment.class.getName());
            fragmentTransaction.commit();
        });

        //삭제 클릭
        mBinding.btDelete.setOnClickListener(v1 -> {


            new KAlertDialog(mContext, KAlertDialog.WARNING_TYPE)
                    .setTitleText("경고")
                    .setContentText("정말 " + mMyGroupArrayList.get(position).getGroupName() + "그룹을 삭제하시겠습니까?")
                    .setConfirmText("확인")
                    .setConfirmClickListener(sDialog -> {

                        Call<Void> deleteGroup = MyApplication
                                .getRestAdapter()
                                .deleteGroup(mMyGroupArrayList.get(position).getGroupID());

                        deleteGroup.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    sDialog.dismissWithAnimation();

                                    mView.getmPresenter().initListViewData(mView.getmBinding().lvMyGroup);

                                    //mPresenter.requestGroupList();
                                    notifyDataSetInvalidated();

                                    new KAlertDialog(mContext, KAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("성공")
                                            .setContentText(mMyGroupArrayList.get(position).getGroupName() + "그룹이 정상적으로 삭제되었습니다.")
                                            .setConfirmText("확인")
                                            .setConfirmClickListener(sDialog -> {
                                                sDialog.dismissWithAnimation();
                                            })
                                            .show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                new KAlertDialog(mContext, KAlertDialog.WARNING_TYPE)
                                        .setTitleText("실패")
                                        .setContentText("그룹 삭제에 실패했습니다.")
                                        .setConfirmText("확인")
                                        .setConfirmClickListener(sDialog -> {
                                            sDialog.dismissWithAnimation();
                                        })
                                        .show();
                                Log.d("fail", t.getLocalizedMessage());
                                Log.d("fail", t.getMessage());
                            }
                        });
                    })
                    .setCancelText("취소")
                    .setCancelClickListener(sDialog -> sDialog.dismissWithAnimation())
                    .show();
        });

        v.setTag(mBinding);
        return v;
    }

}
