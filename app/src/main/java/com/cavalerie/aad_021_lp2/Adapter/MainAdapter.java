package com.cavalerie.aad_021_lp2.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.cavalerie.aad_021_lp2.Fragment.Learning_leaderFragment;
import com.cavalerie.aad_021_lp2.Fragment.SkillIQLeaderFragment;

public class MainAdapter extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"Learning Leader", "Skill IQ Leaders"};

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Learning_leaderFragment.newInstance();
            case 1:
                return SkillIQLeaderFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
