package com.example.passwordmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.passwordmanager.Database.Pass;

public class PassViewActivity extends AppCompatActivity {

    private TextView passName, passEmail, passSite;
    private TextInputEditText passKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_view);

        passName=findViewById(R.id.vTextName);
        passEmail=findViewById(R.id.vTextEmail);
        passKey=findViewById(R.id.vTextPassword);
        passSite=findViewById(R.id.vTextWebsite);

        final Pass pass = (Pass) getIntent().getSerializableExtra("pass");
        loadPass(pass);

        passSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pass.getUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.edit_view,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionbar_edit:
                final Pass pass = (Pass) getIntent().getSerializableExtra("pass");
                Intent intent=new Intent(PassViewActivity.this,UpdatePassActivity.class);
                intent.putExtra("pass",pass);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadPass(Pass pass) {

        passName.setText(pass.getWebsite());
        passEmail.setText(pass.getEmail());
        passKey.setText(pass.getPassword());
        passSite.setText(pass.getUrl());
    }
}
