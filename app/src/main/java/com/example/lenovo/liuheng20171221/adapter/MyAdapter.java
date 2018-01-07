package com.example.lenovo.liuheng20171221.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.liuheng20171221.R;
import com.example.lenovo.liuheng20171221.bean.GouBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by lenovo on 2017/12/21.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private Context context;
    private GouBean bean;
    private OnItemClickListener mOnItemClickListener;

    public MyAdapter(Context context, GouBean bean) {
        this.context = context;
        this.bean = bean;
    }
    //点击的接口
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //拿到我们自己定义的布局
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.recy_item, parent, false));
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        //加载文字
        holder.tv.setText(bean.getTuijian().getList().get(position).getTitle());
        holder.jq.setText(bean.getTuijian().getList().get(position).getPrice()+"");
        String[] str =bean.getTuijian().getList().get(position).getImages().split("\\|");
        String s = str[0];
        //图片构造者
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(s)
                .setAutoPlayAnimations(true)
                .build();
        holder.simpleDraweeView.setController(controller);
        //判断图片点击
        if(mOnItemClickListener != null){
            holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.simpleDraweeView,position);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return bean.getTuijian().getList().size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView tv;
        TextView jq;
        public MyHolder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            jq = itemView.findViewById(R.id.jq);
        }
    }
}
