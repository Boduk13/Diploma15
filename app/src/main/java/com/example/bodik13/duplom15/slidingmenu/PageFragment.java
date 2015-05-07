package com.example.bodik13.duplom15.slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bodik13.duplom15.R;

public class PageFragment extends Fragment {

    ///edit texts
    EditText firstname;
    EditText lastname;
    EditText mail;
    EditText phone;
    EditText adress;
    EditText age;
    EditText drivingExp;
    //---

	public PageFragment(){}


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
         
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstname = (EditText) view.findViewById(R.id.firstName);
        lastname = (EditText) view.findViewById(R.id.lastName);
        mail = (EditText) view.findViewById(R.id.mail);
        phone = (EditText) view.findViewById(R.id.phone);
        adress = (EditText) view.findViewById(R.id.adress);
        age = (EditText) view.findViewById(R.id.age);
        drivingExp = (EditText) view.findViewById(R.id.drivingExp);





    }
}
