package com.example.lostandfound;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public TextView txDesc;
    public TextView tvDate;
    public TextView tvLocation;
    public Button buttonRemove;
    public String u_id;
    private DatabaseHelper dbHelper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(!(actionBar == null))
        {
            actionBar.hide();
        }
        txDesc = findViewById(R.id.txDesc);
        tvDate = findViewById(R.id.tvDate);
        tvLocation = findViewById(R.id.tvLocation);
        buttonRemove = findViewById(R.id.buttonRemove);
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,1);
        u_id = getIntent().getStringExtra("u_id");
        txDesc.setText(getIntent().getStringExtra("description"));
        tvDate.setText(getIntent().getStringExtra("date"));
        tvLocation.setText(getIntent().getStringExtra("location"));
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Item","u_id=?",new String[]{u_id});
                Intent i = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}