package com.rhmt.testandroid;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.rhmt.testandroid.model.ListUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    public final static String Extra_id = "id";
    ListUser listUser;
    TextView tvName, tvEmail, tvPhone, tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.name);
        tvEmail = findViewById(R.id.email);
        tvPhone = findViewById(R.id.phone);
        tvAddress = findViewById(R.id.address);

        listUser = getIntent().getParcelableExtra(Extra_id);
        if (listUser != null){


            if (getSupportActionBar() != null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

            }
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
