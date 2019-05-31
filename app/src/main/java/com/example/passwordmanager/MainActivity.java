package com.example.passwordmanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.passwordmanager.Fragment.GenerateFragment;
import com.example.passwordmanager.Fragment.HomeFragment;
import com.example.passwordmanager.Fragment.MoreFragment;
import com.example.passwordmanager.Fragment.StrengthFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFrame=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFrame=new HomeFragment();
                    break;
                case R.id.nav_generate:
                    selectedFrame=new GenerateFragment();
                    break;
                case R.id.nav_strength:
                    selectedFrame=new StrengthFragment();
                    break;
                case R.id.nav_more:
                    selectedFrame=new MoreFragment();
                    break;
            }
            if (selectedFrame!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,selectedFrame).commit();
            }
            return false;
        }
    };
}
