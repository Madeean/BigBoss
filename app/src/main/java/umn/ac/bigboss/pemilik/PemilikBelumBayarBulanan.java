package umn.ac.bigboss.pemilik;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import umn.ac.bigboss.R;


public class PemilikBelumBayarBulanan extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pemilik_belum_bayar_bulanan, container, false);
    }
}