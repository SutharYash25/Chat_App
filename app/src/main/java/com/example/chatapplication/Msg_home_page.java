package com.example.chatapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Msg_home_page extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Msg_home_page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Msg.
     */
    // TODO: Rename and change types and number of parameters
    public static Msg_home_page newInstance(String param1, String param2) {
        Msg_home_page fragment = new Msg_home_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView recyclerView ;
    My_Adapter myAdapter ;
    ArrayList<Modal> list;

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

        View view = inflater.inflate(R.layout.fragment_msg, container, false);

        recyclerView = view.findViewById(R.id.recycleview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();

        list.add(new Modal("Yash","hiii",R.drawable.chatlogo));
        list.add(new Modal("Suthar","hiii",R.drawable.chatlogo));
        list.add(new Modal("Deep","hiii",R.drawable.chatlogo));
        list.add(new Modal("lalit","hiii",R.drawable.chatlogo));
        list.add(new Modal("jeet","hiii",R.drawable.chatlogo));
        list.add(new Modal("Nirav","hiii",R.drawable.chatlogo));
        list.add(new Modal("Sharad","hiii",R.drawable.chatlogo));
        list.add(new Modal("Dhruv","hiii",R.drawable.chatlogo));
        list.add(new Modal("Anshu","hiii",R.drawable.chatlogo));

        System.out.println(list+"----------");

        myAdapter = new My_Adapter(list);
        recyclerView.setAdapter(myAdapter);

        return view;
    }
}