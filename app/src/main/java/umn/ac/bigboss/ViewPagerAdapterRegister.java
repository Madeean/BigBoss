package umn.ac.bigboss;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import umn.ac.bigboss.fragmentregister.PemilikFragment;
import umn.ac.bigboss.fragmentregister.PengontrakFragment;

public class ViewPagerAdapterRegister extends FragmentStateAdapter {
    public ViewPagerAdapterRegister(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PemilikFragment();
            case 1:
                return new PengontrakFragment();
            default:
                return new PemilikFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
