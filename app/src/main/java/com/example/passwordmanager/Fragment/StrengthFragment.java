package com.example.passwordmanager.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.passwordmanager.R;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class StrengthFragment extends Fragment {
    EditText edt_strength;
    TextView txt_upper, txt_lower, txt_number, txt_special, txt_matchupWord;

    final String[]mostupl = {"123456","password","123456789","12345678","12345","111111","1234567","sunshine","qwerty","iloveyou",
            "princess","admin","welcome","666666","abc123","football","123123","monkey","654321","!@#$%^&*","charlie","aa123456",
            "donald","password1","qwerty123","qwaszx123","qwaszx1"};

    public StrengthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_strength, container, false);

        edt_strength=view.findViewById(R.id.edt_strength);
        txt_lower=view.findViewById(R.id.txt_lower);
        txt_upper=view.findViewById(R.id.txt_upper);
        txt_number=view.findViewById(R.id.txt_number);
        txt_special=view.findViewById(R.id.txt_special);
        txt_matchupWord=view.findViewById(R.id.txt_matchupWord);

        edt_strength.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password= edt_strength.getText().toString();
                passwordstrengthvalidate(password);
                matchvalidate(password);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void matchvalidate(String password) {
        txt_matchupWord.setVisibility(View.INVISIBLE);
        for (String item: mostupl){
            if (password.equals(item)){
                txt_matchupWord.setVisibility(View.VISIBLE);
            }
        }
    }

    public void passwordstrengthvalidate(String password){
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digitcase = Pattern.compile("[0-9]");
        Pattern specialcase = Pattern.compile("[^A-Za-z0-9]");

        if (!uppercase.matcher(password).find()){
            txt_upper.setTextColor(Color.BLACK);
        }else{
            txt_upper.setTextColor(Color.GREEN);
        }
        if (!lowercase.matcher(password).find()){
            txt_lower.setTextColor(Color.BLACK);
        }else{
            txt_lower.setTextColor(Color.GREEN);
        }
        if (!digitcase.matcher(password).find()){
            txt_number.setTextColor(Color.BLACK);
        }else{
            txt_number.setTextColor(Color.GREEN);
        }
        if (!specialcase.matcher(password).find()){
            txt_special.setTextColor(Color.BLACK);
        }else{
            txt_special.setTextColor(Color.GREEN);
        }
    }
}
