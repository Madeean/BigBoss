package umn.ac.bigboss.pemilik;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import umn.ac.bigboss.LoginActivity;
import umn.ac.bigboss.R;


public class PemilikSetting extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar my_toolbar;

    TextView my_toolbar_title;

    Button btn_logout_setting_pemilik;

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pemilik_setting, container, false);

//        my_toolbar = view.findViewById(R.id.my_toolbar_setting_pemilik);
//        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);
//        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));
//
//
//        my_toolbar_title.setText("Setting");
//        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
//        my_toolbar_title.setTextSize(16);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(my_toolbar);
//
//
//        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));
//
        btn_logout_setting_pemilik = view.findViewById(R.id.btn_logout_setting_pemilik);
        btn_logout_setting_pemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
//
//        drawerLayout = view.findViewById(R.id.drawer_layout);
//        navigationView = view.findViewById(R.id.nav_view);
//        drawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.globe_pemilik:
//                        Intent intent = new Intent(getActivity(), PemilikHome.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.search_pemilik:
//                        Intent intent1 = new Intent(getActivity(), PemilikBelumBayarBulanan.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.setting_pemilik:
//                        Intent intent2 = new Intent(getActivity(), PemilikSetting.class);
//                        startActivity(intent2);
//                        break;
//                }
//                return false;
//            }
//        });

        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);
        my_toolbar = view.findViewById(R.id.my_toolbar_setting_pemilik);
        my_toolbar_title = my_toolbar.findViewById(R.id.my_toolbar_title);

        my_toolbar.setBackgroundColor(getResources().getColor(R.color.abuabumuda));

        my_toolbar_title.setText("Setting");
        my_toolbar_title.setTextColor(getResources().getColor(R.color.hitam));
        my_toolbar_title.setTextSize(16);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(my_toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        my_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.list));

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawerLayout, my_toolbar, R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.hitam));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.globe_pemilik:
                        Intent intent = new Intent(getActivity(), PemilikHome.class);
                        startActivity(intent);
                        break;
                    case R.id.search_pemilik:
                        Fragment fragment = new PemilikBelumBayarBulanan();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container_pemilik, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case R.id.setting_pemilik:
                        Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent2);
                        getActivity().finish();
                        break;
                }
                return false;
            }
        });




        return view;
    }




}