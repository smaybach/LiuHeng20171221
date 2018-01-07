package com.example.lenovo.liuheng20171221;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.liuheng20171221.bean.AddBean;
import com.example.lenovo.liuheng20171221.bean.GouBean;
import com.example.lenovo.liuheng20171221.bean.XiangBean;
import com.example.lenovo.liuheng20171221.presenter.GouPresenter;
import com.example.lenovo.liuheng20171221.view.IGouView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiangActivity extends AppCompatActivity implements IGouView {

    @BindView(R.id.xq_banner)
    SimpleDraweeView xqBanner;
    @BindView(R.id.xq_title)
    TextView xqTitle;
    @BindView(R.id.xq_price)
    TextView xqPrice;
    @BindView(R.id.add_gw)
    TextView addGw;
    private GouPresenter presenter;
    public int cont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        ButterKnife.bind(this);
        presenter = new GouPresenter(this);
        //接受
        Intent intent = getIntent();
        cont = intent.getIntExtra("ip", 0);
        presenter.ShowPerXiang(cont);
        presenter.ShowAdd(cont);



    }
    @Override
    public void onGou(GouBean bean) {

    }
    @Override
    public void onAdd(final AddBean bean) {
        //添加购物车
        addGw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = bean.getCode();
                if (code.equals("0")) {
                    Toast.makeText(XiangActivity.this, bean.getMsg(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(XiangActivity.this, "添加失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //详情页面
    @Override
    public void onXiangSucss(XiangBean bean) {
        //根据pid获取图片名字和价格
        cont = bean.getData().getPid();
        String[] str = bean.getData().getImages().split("\\|");
        String s = str[0];
        Glide.with(this).load(s).into(xqBanner);
        xqTitle.setText(bean.getData().getTitle());
        xqPrice.setText("￥"+bean.getData().getPscid());
    }
}
