package com.example.lenovo.liuheng20171221.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.liuheng20171221.R;
import com.example.lenovo.liuheng20171221.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/12/21.
 */

public class FragmentMy extends Fragment {
    @BindView(R.id.btn)
    TextView btn;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_my, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick(R.id.btn)
    public void onViewClicked() {
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
//        Toast.makeText(getActivity(), "欢迎来到登录页面", Toast.LENGTH_SHORT).show();
    }
}
