package ru.miet.toeat.ui.favorites;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FavsFragStateAdapter extends FragmentStateAdapter {
    public FavsFragStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new FavDishesFragment();
        }
        else{
            return new FavIngridientsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
