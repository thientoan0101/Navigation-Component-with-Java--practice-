package com.example.pt_navigation;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pt_navigation.Models.PopularModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    FirebaseFirestore db;
    PopularModel product;
    Context context;
    String proID;

    ImageView imageView;
    TextView textView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        textView = getActivity().findViewById(R.id.nameDetail);
//        imageView = getActivity().findViewById(R.id.imageDetail);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        textView = root.findViewById(R.id.nameDetail);
        imageView = root.findViewById(R.id.imageDetail);
        proID = DetailFragmentArgs.fromBundle(getArguments()).getId();



        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("PopularProducts").document(proID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                product = documentSnapshot.toObject(PopularModel.class);
                Toast.makeText(context, product.getName(), Toast.LENGTH_LONG).show();

                textView.setText(product.getName());
                Glide.with(context).load(product.getImg_url()).into(imageView);
//                Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
//        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
//        bottomNavigationView.setVisibility(View.GONE);




    }














}