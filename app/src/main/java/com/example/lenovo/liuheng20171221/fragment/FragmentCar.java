package com.example.lenovo.liuheng20171221.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.liuheng20171221.R;
import com.example.lenovo.liuheng20171221.activity.ShopAdapter;
import com.example.lenovo.liuheng20171221.bean.WuBean;
import com.example.lenovo.liuheng20171221.presenter.WuPresenter;
import com.example.lenovo.liuheng20171221.view.IWuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/12/21.
 */

public class FragmentCar extends Fragment implements IWuView {
    @BindView(R.id.third_recyclerview)
    RecyclerView thirdRecyclerview;
    @BindView(R.id.third_allselect)
    CheckBox thirdAllselect;
    @BindView(R.id.third_totalprice)
    TextView thirdTotalprice;
    @BindView(R.id.third_totalnum)
    TextView thirdTotalnum;
    @BindView(R.id.third_submit)
    Button thirdSubmit;
    @BindView(R.id.third_pay_linear)
    LinearLayout thirdPayLinear;
    Unbinder unbinder;
    private WuPresenter presenter;
    private ShopAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_car, null);
//找控件
        unbinder = ButterKnife.bind(this, view);
        presenter = new WuPresenter(this);
        presenter.ShowPerWu();
        //线性布局
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        thirdRecyclerview.setLayoutManager(manager);
        //绑定适配器
        adapter = new ShopAdapter(getActivity());
        thirdRecyclerview.setAdapter(adapter);
        thirdAllselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.selectAll(thirdAllselect.isChecked());
            }
        });
        adapter.setListener(new ShopAdapter.UpdateUiListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                thirdAllselect.setChecked(allCheck);
                thirdTotalnum.setText(num);
                thirdTotalprice.setText(total);
            }
        });
        return view;
    }
    @Override
    public void onWuSucss(WuBean bean) {
        adapter.add(bean);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}