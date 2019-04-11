package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.GroupParticipants;
import com.dutch.hdh.dutchpayapp.databinding.ItemGroupParticipantsBinding;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditContract;

import java.util.ArrayList;

public class Listview_GroupParticipantsAdapter extends BaseAdapter  {

    private ItemGroupParticipantsBinding mBinding;
    private MyGroup_EditContract.View mView;

    private Context mContext;
    private ArrayList<GroupParticipants> mGroupParticipantsArrayList;
    private MyApplication myApplication;

    public Listview_GroupParticipantsAdapter(MyGroup_EditContract.View mView , Context mContext) {
        this.mContext = mContext;
        this.mView = mView;
        mGroupParticipantsArrayList = new ArrayList<>();
        myApplication = MyApplication.getInstance();

        //신규추가일 경우
        if(!myApplication.entranceGroupPath){
            mGroupParticipantsArrayList.add(new GroupParticipants(myApplication.getUserInfo().getUserName() , myApplication.getUserInfo().getUserPhone()));
            this.mView.changePersonCount(getCount());
        }
    }

    @Override
    public int getCount() {
        return mGroupParticipantsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGroupParticipantsArrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_group_participants, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ItemGroupParticipantsBinding) v.getTag();
        }

        if(mGroupParticipantsArrayList.get(position).getPhoneNumber().equals(myApplication.getUserInfo().getUserPhone())){
            mBinding.ibDelete.setVisibility(View.GONE);
            mBinding.vDelete.setVisibility(View.GONE);
        } else {
            mBinding.ibDelete.setVisibility(View.VISIBLE);
            mBinding.vDelete.setVisibility(View.VISIBLE);
        }

        mBinding.tvName.setSelected(true);
        mBinding.tvName.setText(mGroupParticipantsArrayList.get(position).getName());
        mBinding.tvPhoneNumber.setText(mGroupParticipantsArrayList.get(position).getPhoneNumber());

        mBinding.vDelete.setOnClickListener(v1->
            deleteItem(position)
        );

        v.setTag(mBinding);
        return v;
    }

    public void setList(ArrayList<GroupParticipants> mGroupParticipantsArrayList) {
        this.mGroupParticipantsArrayList = mGroupParticipantsArrayList;
    }

    public ArrayList<GroupParticipants> getList() {
        return mGroupParticipantsArrayList;
    }

    public void addItem(String name , String phoneNumber){
        mGroupParticipantsArrayList.add(new GroupParticipants(name , phoneNumber));
    }

    public void deleteItem(int position){
        if(mGroupParticipantsArrayList.get(position) != null) {
            mGroupParticipantsArrayList.remove(position);
            mView.changePersonCount(getCount());
            notifyDataSetInvalidated();
        }
    }
}
