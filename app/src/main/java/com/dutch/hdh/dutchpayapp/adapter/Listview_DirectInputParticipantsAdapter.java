package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.DirectInputParticipants;
import com.dutch.hdh.dutchpayapp.databinding.ItemGroupDirectinputBinding;
import com.dutch.hdh.dutchpayapp.ui.mygroup.directinput.MyGroup_DirectInputContract;

import java.util.ArrayList;

public class Listview_DirectInputParticipantsAdapter extends BaseAdapter {

    private ItemGroupDirectinputBinding mBinding;
    private MyGroup_DirectInputContract.View mView;

    private Context mContext;
    private ArrayList<DirectInputParticipants> mGroupParticipantsArrayList;

    public Listview_DirectInputParticipantsAdapter(MyGroup_DirectInputContract.View mView , Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mGroupParticipantsArrayList = new ArrayList<>();
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
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_group_directinput, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ItemGroupDirectinputBinding) v.getTag();
        }

        mBinding.ibDelete.setOnClickListener(v1 -> {
            removeMember(position);

            mView.changeAddMemberCount(getCount());
            notifyDataSetInvalidated();
        });

        clearTextChangedListener(mBinding.etName);
        clearTextChangedListener(mBinding.etPhoneNumber);

        mBinding.etName.setText(mGroupParticipantsArrayList.get(position).getName());
        mBinding.etPhoneNumber.setText(mGroupParticipantsArrayList.get(position).getPhoneNumber());

        mBinding.etName.addTextChangedListener(mGroupParticipantsArrayList.get(position).getNameTextWatcher());
        mBinding.etPhoneNumber.addTextChangedListener(mGroupParticipantsArrayList.get(position).getPhoneNumberWatcher());

        v.setTag(mBinding);
        return v;
    }

    public void addMember(){
        mGroupParticipantsArrayList.add(new DirectInputParticipants());
    }

    public void removeMember(int position){
        if(mGroupParticipantsArrayList.size() > 1) {
            mGroupParticipantsArrayList.remove(position);
        }
    }

    public boolean isEmptyText(){
        int count = getCount();
        for (int i = 0 ; i < count ; i++) {
            if(mGroupParticipantsArrayList.get(i).getName().equals("") || mGroupParticipantsArrayList.get(i).getPhoneNumber().equals("")){
                return true;
            }
        }
        return false;
    }

    private void clearTextChangedListener(EditText editText) {
        //리스트 목록의 모든 리스너를 대상으로 검사하여 삭제해 준다
        int count = getCount();
        for (int i = 0 ; i < count ; i++) {
            editText.removeTextChangedListener(mGroupParticipantsArrayList.get(i).getNameTextWatcher());
            editText.removeTextChangedListener(mGroupParticipantsArrayList.get(i).getPhoneNumberWatcher());
        }
    }

    public ArrayList<DirectInputParticipants> getList() {
        return mGroupParticipantsArrayList;
    }
}
