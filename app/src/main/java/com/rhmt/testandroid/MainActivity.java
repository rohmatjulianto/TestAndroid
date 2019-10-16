package com.rhmt.testandroid;

import android.os.Bundle;


import com.rhmt.testandroid.adapter.MainAdapter;
import com.rhmt.testandroid.model.ListUser;
import com.rhmt.testandroid.model.MainViewModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvShow = findViewById(R.id.rv_show);
        rvShow.setLayoutManager(new LinearLayoutManager( this));
        adapter = new MainAdapter(this);
        adapter.notifyDataSetChanged();
        rvShow.setAdapter(adapter);

        MainViewModel model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        model.getData();

        model.getAllData().observe(this, new Observer<ArrayList<ListUser>>() {
            @Override
            public void onChanged(ArrayList<ListUser> listusers) {
                if (listusers != null){
                    adapter.setmDataUsers(listusers);
                }
            }
        });
    }

}
