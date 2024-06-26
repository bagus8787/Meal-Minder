package com.example.MealMinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.MealMinder.fragment.HomePageFragment;
import com.example.MealMinder.helper.SessionManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

        SessionManager sessionManager = new SessionManager(requireContext());

        Button btnLogout = rootView.findViewById(R.id.buttonLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();

                sessionManager.setNameUser("");
                sessionManager.setNoUser("");
                sessionManager.setTanggalLahir("");

                Intent intent = new Intent(requireContext(), IntroActivity.class);
                startActivity(intent);
            }
        });

        TextView giveFeedback = rootView.findViewById(R.id.giveusFeedback);
        giveFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), feedback.class);
                startActivity(intent);
            }
        });

        TextView setLocation = rootView.findViewById(R.id.textViewLocLoad);
        setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), GmapsActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgEdit = rootView.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(V->{
            Intent intent = new Intent(requireContext(), EditProfileActivity.class);
            startActivity(intent);
        });

        TextView textView_show_full_name = rootView.findViewById(R.id.textView_show_full_name);
        TextView textView_show_email = rootView.findViewById(R.id.textView_show_email);

        TextView tglLahir = rootView.findViewById(R.id.textView16);
        TextView phone = rootView.findViewById(R.id.textView18);

        textView_show_full_name.setText(sessionManager.getNamaUser());
        tglLahir.setText(sessionManager.getTanggalLahir());
        phone.setText(sessionManager.getNoUser());

        FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mCurrentUser != null) {
            TextView tvEmail = rootView.findViewById(R.id.textView12);
            tvEmail.setText(mCurrentUser.getEmail().toString());
            textView_show_email.setText(mCurrentUser.getEmail().toString());
        }


        return rootView;
    }


}