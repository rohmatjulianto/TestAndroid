package com.rhmt.testandroid;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.rhmt.testandroid.model.ListUser;
import com.rhmt.testandroid.model.MainViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class DetailActivity extends AppCompatActivity {

    public final static String Extra_id = "id";
    TextView tvUserId, tvId, tvTitle, tvCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvUserId = findViewById(R.id.tv_userId);
        tvId = findViewById(R.id.tv_id);
        tvTitle = findViewById(R.id.tv_title);
        tvCompleted = findViewById(R.id.tv_completed);

        int id = getIntent().getIntExtra(Extra_id, 0);
        if (id != 0){
            MainViewModel model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
            model.getDataId(id);
            model.getAllData().observe(this, new Observer<ArrayList<ListUser>>() {
                @Override
                public void onChanged(ArrayList<ListUser> listUsers) {
                    for (int i = 0; i < listUsers.size(); i++){
                        tvUserId.setText( String.format(Integer.toString(listUsers.get(i).getUser_id()), "%f"));
                        tvId.setText( String.format(Integer.toString(listUsers.get(i).getId()), "%f"));
                        tvTitle.setText(listUsers.get(i).getTitle());
                        tvCompleted.setText(listUsers.get(i).getCompleted().toString());
                    }
                }
            });
        }


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Detail");

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
