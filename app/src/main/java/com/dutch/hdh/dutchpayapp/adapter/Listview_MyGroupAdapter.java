package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.databinding.ItemMyGroupBinding;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;
import com.dutch.hdh.dutchpayapp.ui.register.password.Register_PaymentPasswordFragment;

import java.util.ArrayList;

public class Listview_MyGroupAdapter extends BaseAdapter {

    private ItemMyGroupBinding mBinding;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private ArrayList<MyGroup> mMyGroupArrayList;
    private MyApplication mMyApplication;

    public Listview_MyGroupAdapter(Context mContext, ArrayList<MyGroup> mMyGroupArrayList, FragmentManager mFragmentManager) {
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

        mBinding.tvPersonCount.setText(mMyGroupArrayList.get(position).getParticipants() + "명");
        mBinding.tvGroupName.setText(mMyGroupArrayList.get(position).getGroupName());

        //편집 클릭
        mBinding.btEdit.setOnClickListener(v1 -> {
            mMyApplication.entranceGroupPath = true;

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

            mMyApplication.getMyGroup_EditFragment().setArguments(null);
            fragmentTransaction.replace(R.id.flFragmentContainer, mMyApplication.getMyGroup_EditFragment(), MyGroup_EditFragment.class.getName());
            fragmentTransaction.addToBackStack(MyGroup_EditFragment.class.getName());
            fragmentTransaction.commit();
        });

        v.setTag(mBinding);
        return v;
    }
}
