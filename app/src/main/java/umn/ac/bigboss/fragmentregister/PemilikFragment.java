package umn.ac.bigboss.fragmentregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import umn.ac.bigboss.R;
import umn.ac.bigboss.pemilik.PemilikHomeActivity;


public class PemilikFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pemilik, container, false);
        Button btn_register_pemilik = view.findViewById(R.id.btn_register);
        btn_register_pemilik.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PemilikHomeActivity.class);
            startActivity(intent);

        });
        return view;

    }
    



}