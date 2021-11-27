package com.example.newskg.adadpter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.newskg.fragments.HomeFragment;
import com.example.newskg.fragments.ScienceFragment;
import com.example.newskg.fragments.SportsFragment;
import com.example.newskg.fragments.TechnologyFragment;

public class FragmentAdapter  extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager,lifecycle);

    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SportsFragment();
            case 2:
                return new ScienceFragment();

        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
