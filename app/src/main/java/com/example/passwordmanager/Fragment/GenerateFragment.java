package com.example.passwordmanager.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.R;

import java.security.SecureRandom;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenerateFragment extends Fragment {

    EditText edt_generate_password;
    TextView generate_password_length;
    Button btn_generate_password;
    CheckBox chckbx_upper, chckbx_lower, chckbx_number, chckbx_special;
    SeekBar generate_seekbar;

    int passwordLength=4;
    String ALPHABET = "";

    private static final Random RANDOM = new SecureRandom();
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";


    public GenerateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_generate, container, false);

        edt_generate_password=view.findViewById(R.id.edt_generate_password);
        btn_generate_password=view.findViewById(R.id.btn_generate_password);
        chckbx_upper=view.findViewById(R.id.chckbx_upper);
        chckbx_lower=view.findViewById(R.id.chckbx_lower);
        chckbx_number=view.findViewById(R.id.chckbx_number);
        chckbx_special=view.findViewById(R.id.chckbx_special);
        generate_password_length=view.findViewById(R.id.generate_password_length);
        generate_seekbar=view.findViewById(R.id.genereate_seekbar);

        generate_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                passwordLength=progress;
                generate_password_length.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_generate_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ALPHABET="";
                if (chckbx_number.isChecked() || chckbx_special.isChecked() || chckbx_upper.isChecked() || chckbx_lower.isChecked()){
                    if (chckbx_upper.isChecked()){
                        ALPHABET=ALPHABET+UPPER;
                    }
                    if (chckbx_lower.isChecked()){
                        ALPHABET=ALPHABET+LOWER;
                    }
                    if (chckbx_number.isChecked()){
                        ALPHABET=ALPHABET+DIGITS;
                    }
                    if (chckbx_special.isChecked()){
                        ALPHABET=ALPHABET+PUNCTUATION;
                    }
                    String password = generatePassword(passwordLength);
                    edt_generate_password.setText(password);
                } else {
                    Toast.makeText(getActivity(),"Select type",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private String generatePassword(int passwordLength) {
        StringBuilder returnValue = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

}
