package com.example.passwordmanager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.passwordmanager.Database.DatabaseClient;
import com.example.passwordmanager.Database.Pass;

public class AddPassActivity extends AppCompatActivity {

    private EditText editTextUrl,editTextUsername,editTextEmail,editTextPassword,editTextName,editTextComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pass);


        editTextUrl=findViewById(R.id.editTextUrl);
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextName=findViewById(R.id.editTextName);
        editTextComment=findViewById(R.id.editTextComment);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask(){
        final String sUrl=editTextUrl.getText().toString();
        final String sUsername=editTextUsername.getText().toString();
        final String sEmail=editTextEmail.getText().toString();
        final String sPassword=editTextPassword.getText().toString();
        final String sWebSite=editTextName.getText().toString();
        final String sComment=editTextComment.getText().toString();

        if (sUrl.isEmpty()){
            editTextUrl.setError("Task required");
            editTextUrl.requestFocus();
            return;
        }
        if (sUsername.isEmpty()){
            editTextUsername.setError("Required");
            editTextUsername.requestFocus();
            return;
        }
        if (sEmail.isEmpty()){
            editTextEmail.setError("Required");
            editTextEmail.requestFocus();
            return;
        }
        if (sPassword.isEmpty()){
            editTextPassword.setError("Required");
            editTextPassword.requestFocus();
            return;
        }
        if (sWebSite.isEmpty()){
            editTextName.setError("Required");
            editTextName.requestFocus();
            return;
        }
        if (sComment.isEmpty()){
            editTextComment.setError("Required");
            editTextComment.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Pass pass=new Pass();
                pass.setUrl(sUrl);
                pass.setUsername(sUsername);
                pass.setEmail(sEmail);
                pass.setPassword(sPassword);
                pass.setWebsite(sWebSite);
                pass.setComment(sComment);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().passDao().insert(pass);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }
}
