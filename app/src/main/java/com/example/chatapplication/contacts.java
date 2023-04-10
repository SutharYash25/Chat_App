package com.example.chatapplication;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.security.Permission;
import java.sql.SQLOutput;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contacts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contacts extends Fragment {


    Button getcontacts;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public contacts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contacts.
     */
    // TODO: Rename and change types and number of parameters
    public static contacts newInstance(String param1, String param2) {
        contacts fragment = new contacts();
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

            getcontacts = getcontacts.findViewById(R.id.contactsbtn_id);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);


    }

    public void btnGetContactPressed(View V) {
        getPhoneContacts();
    }

    private void getPhoneContacts() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }


        getcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver();
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                Log.i("CONTACT_PROVIDER_DEMO", "TOTAL # OF CONTACTS ::: " + Integer.toString(cursor.getCount()));
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
//                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                        String contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        System.out.println("data ----->>>>>"+contactName);
                        System.out.println("data ----->>>>>"+contactNumber);
                        Log.i("CONTACT_PROVIDER_DEMO", "Contact Name ::: " + contactName + " phone # :::" + contactNumber);
                    }
                }
            }
        });
    }

    private ContentResolver getContentResolver() {
        return null;
    }
}
