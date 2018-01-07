package com.example.lenovo.liuheng20171221;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lenovo.liuheng20171221.fragment.FragmentCar;
import com.example.lenovo.liuheng20171221.fragment.FragmentHome;
import com.example.lenovo.liuheng20171221.fragment.FragmentMy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.sp)
    RadioButton sp;
    @BindView(R.id.gwc)
    RadioButton gwc;
    @BindView(R.id.my)
    RadioButton my;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.vp)
    ViewPager vp;

    private List<Fragment> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iniD();
        //拿到适配器
        MyAdapters myAdapters = new MyAdapters(getSupportFragmentManager());
        vp.setAdapter(myAdapters);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg.check(R.id.sp);
                        break;
                    case 1:
                        rg.check(R.id.gwc);
                        break;
                    case 2:
                        rg.check(R.id.my);
                    default:
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.sp:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.gwc:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.my:
                        vp.setCurrentItem(2);
                        break;
                    default:
                        break;

                }
            }
        });




    }

    private void iniD() {
        list.add(new FragmentHome());
        list.add(new FragmentCar());
        list.add(new FragmentMy());
    }


    class MyAdapters extends FragmentPagerAdapter {

        public MyAdapters(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


}
