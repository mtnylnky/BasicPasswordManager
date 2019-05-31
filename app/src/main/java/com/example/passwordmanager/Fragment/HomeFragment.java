package com.example.passwordmanager.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordmanager.Adapter.PassAdapter;
import com.example.passwordmanager.AddPassActivity;
import com.example.passwordmanager.Database.DatabaseClient;
import com.example.passwordmanager.Database.Pass;
import com.example.passwordmanager.R;

import java.util.List;


public class HomeFragment extends Fragment {
    private FloatingActionButton buttonAddPass;
    private RecyclerView recyclerView;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_passlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        buttonAddPass = view.findViewById(R.id.floating_button_add);
        buttonAddPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPassActivity.class);
                startActivity(intent);
            }
        });


        getTasks();

        return view;
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Pass>> {

            @Override
            protected List<Pass> doInBackground(Void... voids) {
                List<Pass> passList = DatabaseClient
                        .getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .passDao()
                        .getAll();
                return passList;
            }

            @Override
            protected void onPostExecute(List<Pass> pass) {
                super.onPostExecute(pass);
                PassAdapter adapter = new PassAdapter(getActivity(), pass);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}
