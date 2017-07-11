package com.example.denissamodurov.towntimer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.ImageView;

import com.example.denissamodurov.towntimer.houseInstanceClass.House;
import com.example.denissamodurov.towntimer.houseInstanceClass.House.HouseLabel;
import com.example.denissamodurov.towntimer.houseInstanceClass.HouseFactory;

/**
 * Created by denissamodurov on 20/05/2017.
 */

public class HouseSelestedListFragment extends Fragment {
    private static final String POSITION_HOUSE_LABEL = "Number of house label";

    private ImageView mStartHouseImage;

    static HouseSelestedListFragment newInstance(int page) {
        HouseSelestedListFragment houseSelestedListFragment = new HouseSelestedListFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(POSITION_HOUSE_LABEL, page);
        houseSelestedListFragment.setArguments(arguments);
        return houseSelestedListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.house_selested_list_fragment, null);

        mStartHouseImage = (ImageView) view.findViewById(R.id.house_selected_list_activity_image_view);

        int housePosition = getArguments().getInt(POSITION_HOUSE_LABEL);
        HouseLabel currentHouse = HouseLabel.values()[housePosition];
        mStartHouseImage.setImageResource(HouseFactory.getHouseEndImage(currentHouse));

        return view;
    }
}
