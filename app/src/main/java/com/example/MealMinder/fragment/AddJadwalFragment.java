package com.example.MealMinder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MealMinder.AddJadwalActivity;
import com.example.MealMinder.AturAlarm;
import com.example.MealMinder.R;
import com.example.MealMinder.adapter.HomeAdapter;
import com.example.MealMinder.model.MealData;

public class AddJadwalFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;

    private String mParam1;
    private String mParam2;

    public AddJadwalFragment() {
        // Required empty public constructor
    }

    public static AddJadwalFragment newInstance(String param1, String param2) {
        AddJadwalFragment fragment = new AddJadwalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecycler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_addjadwal, container, false);
        Button tambahJadwalButton = view.findViewById(R.id.btn_tambah_jadwal);
        recyclerView = view.findViewById(R.id.recycler_add);
        tambahJadwalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddJadwalActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initRecycler(){
        HomeAdapter adapter = new HomeAdapter(getContext(),MealData.getMaps(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void onTambahJadwalClicked() {
        Intent intent = new Intent(getActivity(), AturAlarm.class);
        startActivity(intent);
    }
}
