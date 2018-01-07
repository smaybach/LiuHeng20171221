package com.example.lenovo.liuheng20171221.activity;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.liuheng20171221.R;
import com.example.lenovo.liuheng20171221.bean.WuBean;
import com.example.lenovo.liuheng20171221.utils.plusview;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/21.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context context;
    private List<WuBean.DataBean.ListBean> list;//bean包的集合
    //存放商家的集合
    private Map<String, String> map = new HashMap<>();
    //有参构造
    public ShopAdapter(Context context) {
        this.context = context;
    }
    /**
     * 添加数据并更新
     */
    public void add(WuBean bean) {
        //判段如果list是空的
        if (this.list == null) {
            //那就把listNew出来
            this.list = new ArrayList<>();
        }
        //遍历商家
        for (WuBean.DataBean shop : bean.getData()) {
            map.put(shop.getSellerid(), shop.getSellerName());
            // 遍历商品
            for (int i = 0; i < shop.getList().size(); i++) {
                this.list.add(shop.getList().get(i));
            }
        }
        setFirst(this.list);  //控制是否显示商家
        notifyDataSetChanged();//更新数据
    }
    /**
     * 设置数据源， 控制显示商家
     *
     * @param list 这里把集合给setFirst这个方法就是要设置商品的显示与阴藏
     */
    private void setFirst(List<WuBean.DataBean.ListBean> list) {
        //这里判断  如果集合的字符大于0 你就默认显示一个商家
        if (list.size() > 0) {
            list.get(0).setIsFirst(1);   //这是默认显示商家
            for (int i = 1; i < list.size(); i++) {  //这是For循环把集合里的所有元素循出来
                if (list.get(i).getSellerid() == list.get(i - 1).getSellerid()) {
                    //如果俩个商品是同一个商家的那么就让这两个商品只显示一个商家
                    list.get(i).setIsFirst(2);
                } else {
                    //如果不是 就两个都显示
                    list.get(i).setIsFirst(1);
                    if (list.get(i).isItemSelected()) {
                        list.get(i).setShopSelected(list.get(i).isItemSelected());
                    }
                }
            }
        }
    }

    /**
     * 加载布局文件
     */
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapter_layout, null);
        //添加到ViewHolder里
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ShopAdapter.ViewHolder holder, final int position) {
        // 显示商品图片
        if (list.get(position).getIsFirst() == 1) {
            //显示商家  VISIBLE显示商品
            holder.shop_checkbox.setVisibility(View.VISIBLE);//显示商家
            holder.tv_item_shopcart_shopname.setVisibility(View.VISIBLE);//显示商家
            holder.shop_checkbox.setChecked(list.get(position).isShopSelected());

            //            显示商家的名称
            //            list.get(position).getSellerid() 取到商家的id
            //            map.get（）取到 商家的名称
            holder.tv_item_shopcart_shopname.setText(map.get(String.valueOf(list.get(position).getSellerid())));
        } else {
            holder.shop_checkbox.setVisibility(View.GONE);//隐藏商家
            holder.tv_item_shopcart_shopname.setVisibility(View.GONE);//隐藏商家
        }
        //控制 商品的  checkbox
        holder.item_checkbox.setChecked(list.get(position).isItemSelected());
        //这是分割  由于接口中的图片是连这的所以必须要分割
        String[] url = list.get(position).getImages().split("\\|");
        // 从分割完之后返回的数组中设置所要显示的图片
        // ImageLoader.getInstance().displayImage(url[0], holder.item_pic);
        Uri uri = Uri.parse(url[0]);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.item_pic.setController(controller);
        //设置所要显示的文字 也是商家
        holder.item_name.setText(list.get(position).getTitle());
        //设置所要显示的价格
        holder.item_price.setText(list.get(position).getPrice() + "");
        //计算完所友的商品总价后在这设置显示
        holder.plus_view_id.setEditText(list.get(position).getNum());
        // 商家的checkbox  多选框
        holder.shop_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.get(position).setShopSelected(holder.shop_checkbox.isChecked());

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(position).getSellerid() == list.get(i).getSellerid()) {
                        list.get(i).setItemSelected(holder.shop_checkbox.isChecked());
                    }
                }
                notifyDataSetChanged();//更新数据源
                sum(list);//计算总价
            }
        });
        // 商品的checkbox  多选框
        holder.item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setItemSelected(holder.item_checkbox.isChecked());
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(i).getSellerid() == list.get(j).getSellerid() && !list.get(j).isItemSelected()) {
                            list.get(i).setShopSelected(false);
                            break;
                        } else {
                            list.get(i).setShopSelected(true);
                        }
                    }
                }
                notifyDataSetChanged();//更新数据源
                sum(list);//计算总价
            }
        });
        //删除的点击事件
        holder.item_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //从集合中移除商家信息
                list.remove(position);
                setFirst(list);
                notifyDataSetChanged();//更新数据
                sum(list);//算总价
            }
        });
        //加减号
        holder.plus_view_id.setListener(new plusview.ClickListener() {
            @Override
            public void click(int count) {
                list.get(position).setNum(count);
                notifyDataSetChanged();//更新适配器
                sum(list);//计算总价
                if (count <=1){
                    Toast.makeText(context,"已经是最后一条了",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();//三元运算符
    }
    /**
     * 计算总价
     *
     * @param list
     */
    private void sum(List<WuBean.DataBean.ListBean> list) {
        int totalNum = 0;
        float totalMoney = 0.0f;
        boolean allCheck = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isItemSelected()) {
                totalNum += list.get(i).getNum();
                totalMoney += list.get(i).getNum() * list.get(i).getPrice();
            } else {
                allCheck = false;
            }
        }
        listener.setTotal(totalMoney + "", totalNum + "", allCheck);
    }
    public void selectAll(boolean cl) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setShopSelected(cl);
            list.get(i).setItemSelected(cl);
        }
        notifyDataSetChanged();
        sum(list);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        View view; //item_del;
        plusview plus_view_id;
        CheckBox shop_checkbox, item_checkbox;
        TextView tv_item_shopcart_shopname, item_price, item_name, tv_item_shopcart_cloth_size;
        SimpleDraweeView item_pic;
        Button item_del;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            item_del = itemView.findViewById(R.id.item_del);
            shop_checkbox = itemView.findViewById(R.id.shop_checkbox);
            item_checkbox = itemView.findViewById(R.id.item_checkbox);
            tv_item_shopcart_shopname = itemView.findViewById(R.id.tv_item_shopcart_shopname);
            item_price = itemView.findViewById(R.id.item_price);
            item_name = itemView.findViewById(R.id.item_name);
            tv_item_shopcart_cloth_size = itemView.findViewById(R.id.tv_item_shopcart_cloth_size);
            item_pic = itemView.findViewById(R.id.item_pic);
            plus_view_id = itemView.findViewById(R.id.plus_view_id);
        }
    }
    public UpdateUiListener listener;
    public void setListener(UpdateUiListener listener) {
        this.listener = listener;
    }
    //更新接口数据
    public interface UpdateUiListener {
        public void setTotal(String total, String num, boolean allCheck);
    }

}
