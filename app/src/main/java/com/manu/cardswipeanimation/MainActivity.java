package com.manu.cardswipeanimation;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LinkagePager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.LinkagePagerContainer;
import me.crosswall.lib.coverflow.core.PageItemClickListener;

public class MainActivity extends AppCompatActivity {

    ArrayList<CardModel> stringArrayList = new ArrayList<>();
    private LinkagePagerContainer customPagerContainer;
    private LinkagePager pager;
    private LinearLayout backgroundLayout;
    ViewPagerIndicator mViewPagerIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringArrayList.add(new CardModel("#CF3A61","#BD3558"));
        stringArrayList.add(new CardModel("#DB6521","#FF7626"));
        stringArrayList.add(new CardModel("#D10000","#FF0000"));
        stringArrayList.add(new CardModel("#1FA4DE","#24BDFF"));
        stringArrayList.add(new CardModel("#1C733B","#27A153"));

        backgroundLayout =  findViewById(R.id.backgroundLayout);
        mViewPagerIndicator =  findViewById(R.id.view_pager_indicator);
        customPagerContainer =  findViewById(R.id.pager_container);
        customPagerContainer.setPageItemClickListener(new PageItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                pager.setCurrentItem(position);
            }
        });

        final LinkagePager cover = customPagerContainer.getViewPager();
        PagerAdapter coverAdapter = new MyPagerAdapter();
        cover.setAdapter(coverAdapter);
        cover.setOffscreenPageLimit(5);
        cover.addOnPageChangeListener(new LinkagePager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                backgroundLayout.setBackgroundColor(Color.parseColor(stringArrayList.get(i).getBgColor()));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        new CoverFlow.Builder()
                .withLinkage(cover)
                .pagerMargin(0f)
                .scale(0.3f)
                .spaceSize(0f)
                .build();


        pager =  findViewById(R.id.pager);
        MyPagerFragmentAdapter  adapterViewPager = new MyPagerFragmentAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(5);
        pager.setAdapter(adapterViewPager);
        cover.setLinkagePager(pager);
        pager.setLinkagePager(cover);
        mViewPagerIndicator.setupWithViewPager(cover);
    }



    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            LayoutInflater   mLayoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
                    CardView cardView =  itemView.findViewById(R.id.cardView);
            cardView.setCardBackgroundColor(Color.parseColor(stringArrayList.get(position).getCardColor()));
            LinearLayout linearLayout = itemView.findViewById(R.id.containerLayout);
            linearLayout.setBackgroundColor(Color.parseColor(stringArrayList.get(position).getBgColor()));
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }


    public  class MyPagerFragmentAdapter extends FragmentPagerAdapter {
        private  int NUM_ITEMS = 5;

        public MyPagerFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return ItemFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return ItemFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return ItemFragment.newInstance(2, "Page # 3");
                case 3: // Fragment # 0 - This will show FirstFragment different title
                    return ItemFragment.newInstance(1, "Page # 4");
                case 4: // Fragment # 1 - This will show SecondFragment
                    return ItemFragment.newInstance(2, "Page # 5");
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

}