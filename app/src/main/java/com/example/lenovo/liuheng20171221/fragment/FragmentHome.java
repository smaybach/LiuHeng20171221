package com.example.lenovo.liuheng20171221.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.lenovo.liuheng20171221.R;
import com.example.lenovo.liuheng20171221.XiangActivity;
import com.example.lenovo.liuheng20171221.adapter.MyAdapter;
import com.example.lenovo.liuheng20171221.bean.AddBean;
import com.example.lenovo.liuheng20171221.bean.GouBean;
import com.example.lenovo.liuheng20171221.bean.XiangBean;
import com.example.lenovo.liuheng20171221.presenter.GouPresenter;
import com.example.lenovo.liuheng20171221.view.IGouView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/12/21.
 */

public class FragmentHome extends Fragment implements IGouView {
    public RecyclerView sp_rv;
    private GouPresenter presenter;
    public Window window;

    @BindView(R.id.sp_rv)
    RecyclerView spRv;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        //找控件
        sp_rv = view.findViewById(R.id.sp_rv);
        presenter= new GouPresenter(this);
        presenter.ShowPerGou();
        //设置沉浸式
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window = getActivity().getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onGou(final GouBean bean) {
        sp_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //拿到适配器
        MyAdapter apter = new MyAdapter(getActivity(),bean);
        sp_rv.setAdapter(apter);
        //适配器点击事件
        apter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),XiangActivity.class);
                //直接把bean传过去
                intent.putExtra("ip",bean.getTuijian().getList().get(position).getPid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAdd(AddBean bean) {

    }
    @Override
    public void onXiangSucss(XiangBean bean) {

    }
    //解绑
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
