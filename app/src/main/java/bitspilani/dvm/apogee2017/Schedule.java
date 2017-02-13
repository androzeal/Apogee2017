package bitspilani.dvm.apogee2017;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends android.support.v4.app.Fragment {
    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_schedule, container, false);

        TabAdapter = new TabPagerAdapter(getChildFragmentManager());
        Tab = (ViewPager)view.findViewById(R.id.pager);
        Tab.setOffscreenPageLimit(3);
        Tab.setAdapter(new TabPagerAdapter(getChildFragmentManager()));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip)view.findViewById(R.id.tabs);

        tabs.setIndicatorHeight(8);
        tabs.setIndicatorColor(getResources().getColor(R.color.accent));
        tabs.setTextColor(getResources().getColor(R.color.white));

        tabs.setUnderlineColor(getResources().getColor(R.color.background_color));
        tabs.setDividerColor(getResources().getColor(R.color.splash_background));
        tabs.setViewPager(Tab);
        return view;
    }

}
