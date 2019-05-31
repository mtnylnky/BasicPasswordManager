package com.example.passwordmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.passwordmanager.Database.DatabaseClient;
import com.example.passwordmanager.Database.Pass;

public class UpdatePassActivity extends AppCompatActivity {

    private EditText editTextUrl,editTextUsername,editTextEmail,editTextPassword,editTextName,editTextComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pass);

        editTextUrl=findViewById(R.id.editTextUrl);
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextName=findViewById(R.id.editTextName);
        editTextComment=findViewById(R.id.editTextComment);

        final Pass pass = (Pass) getIntent().getSerializableExtra("pass");

        loadPass(pass);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updatePass(pass);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.delete_update,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionbar_delete:
                final Pass pass = (Pass) getIntent().getSerializableExtra("pass");
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePassActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deletePass(pass);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadPass(Pass pass) {

        editTextUrl.setText(pass.getUrl());
        editTextUsername.setText(pass.getUsername());
        editTextEmail.setText(pass.getEmail());
        editTextPassword.setText(pass.getPassword());
        editTextName.setText(pass.getWebsite());
        editTextComment.setText(pass.getComment());

    }

    private void updatePass(final Pass pass) {
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
            editTextUsername.setError("Task required");
            editTextUsername.requestFocus();
            return;
        }
        if (sEmail.isEmpty()){
            editTextEmail.setError("Task required");
            editTextEmail.requestFocus();
            return;
        }
        if (sPassword.isEmpty()){
            editTextPassword.setError("Task required");
            editTextPassword.requestFocus();
            return;
        }
        if (sWebSite.isEmpty()){
            editTextName.setError("Task required");
            editTextName.requestFocus();
            return;
        }
        if (sComment.isEmpty()){
            editTextComment.setError("Task required");
            editTextComment.requestFocus();
            return;
        }

        class UpdatePass extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                pass.setUrl(sUrl);
                pass.setUsername(sUsername);
                pass.setEmail(sEmail);
                pass.setPassword(sPassword);
                pass.setWebsite(sWebSite);
                pass.setComment(sComment);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .passDao()
                        .update(pass);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdatePassActivity.this, MainActivity.class));
            }
        }

        UpdatePass ut = new UpdatePass();
        ut.execute();
    }

    private void deletePass(final Pass pass) {
        class DeletePass extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .passDao()
                        .delete(pass);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdatePassActivity.this, MainActivity.class));
            }
        }

        DeletePass dt = new DeletePass();
        dt.execute();

    }
}
