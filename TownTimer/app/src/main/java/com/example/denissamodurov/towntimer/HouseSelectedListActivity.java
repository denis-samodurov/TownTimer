package com.example.denissamodurov.towntimer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v4.view.*;

import com.example.denissamodurov.towntimer.houseInstanceClass.House;

/**
 * Created by denissamodurov on 20/05/2017.
 */

public class HouseSelectedListActivity extends FragmentActivity {
    private static final String CURRENT_POSITION_SOURCE = "Current position source";
    private static final String CURRENT_POSITION_RESULT = "Current position result";

    private ViewPager mHousePreveiw;
    private PagerAdapter mHousePreveiwAdapter;
    private int mCurrentPosition;

    public static Intent newIntent(Context packageContext, int currentPosition){
        Intent intent = new Intent(packageContext, HouseSelectedListActivity.class);
        intent.putExtra(CURRENT_POSITION_SOURCE, currentPosition);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_selected_list_activity);

        mCurrentPosition = getIntent().getIntExtra(CURRENT_POSITION_SOURCE, DefaultSettings.DEFAULT_START_POSITION);
        setupResultFromHouseSelectedListActivity(mCurrentPosition);

        mHousePreveiw = (ViewPager) findViewById(R.id.house_selected_list_activity);
        mHousePreveiwAdapter = new HousePreveiwAdapter(getSupportFragmentManager());
        mHousePreveiw.setAdapter(mHousePreveiwAdapter);
        mHousePreveiw.setCurrentItem(mCurrentPosition);

        mHousePreveiw.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                setupResultFromHouseSelectedListActivity(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setupResultFromHouseSelectedListActivity(int position){
        Intent data = new Intent();
        data.putExtra(CURRENT_POSITION_RESULT, position);
        setResult(RESULT_OK, data);
    }



    private class HousePreveiwAdapter extends FragmentPagerAdapter {

        public HousePreveiwAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return HouseSelestedListFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return House.HouseLabel.values().length;
        }
    }

    public static int getCurrentPosition(Intent result) {
        return result.getIntExtra(CURRENT_POSITION_RESULT, DefaultSettings.DEFAULT_START_POSITION);
    }
}
