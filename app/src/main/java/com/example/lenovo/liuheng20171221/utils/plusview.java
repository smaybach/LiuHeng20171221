package com.example.lenovo.liuheng20171221.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lenovo.liuheng20171221.R;

/**
 * Created by lenovo on 2017/12/21.
 */
public class plusview extends LinearLayout {
    Button revserse,tianjian;
    EditText edittext;
    private  int mcount = 1;//定义一个常量 用于购物车就入时默认显示为1
    public plusview(Context context) {
        super(context);
    }
    public  plusview(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        //首先加载布局文件
        View view = View.inflate(context, R.layout.plus_layout,null);
        //初始化控件
        revserse = view.findViewById(R.id.revserse);//减号
        tianjian = view.findViewById(R.id.tianjian);//加好
        edittext = view.findViewById(R.id.edittext);//加号减号中间的输入框
        //控件初始化玩之后 控件设置点击事件
        revserse.setOnClickListener(new OnClickListener() {
            //减号的点击事件
            @Override
            public void onClick(View view) {
                String trims = edittext.getText().toString().trim();//获取输入框的值去掉空格
                int coun = Integer.valueOf(trims);//把从输入框的得到的值转换成整形
                if (coun>1){
                    mcount = coun-1;
                    edittext.setText(mcount+"");
                    if (listener!=null){
                        listener.click(mcount);
                    }
                }
            }
        });
        //加号的点击事件
        tianjian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = edittext.getText().toString().trim();//获取输入框的值去掉空格
                int coun = Integer.valueOf(trim)+1;//把从输入框的得到的值转换成整形
                mcount = coun;
                edittext.setText(coun+"");
                if (listener!=null){
                    listener.click(coun);
                }
            }
        });
        //输入框的点击事件
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        addView(view);  //添加布局

    }

    public plusview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setEditText(int num){
        if(edittext != null){
            edittext.setText(num+"");
        }
    }
    public ClickListener listener;
    public void setListener(ClickListener listener ){
        this.listener=listener;
    }
    /**
     * 加减号的点击事件
     */
    public  interface  ClickListener{
        public void click(int coun);
    }
}