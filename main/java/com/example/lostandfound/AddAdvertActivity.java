package com.example.lostandfound;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.UUID;

public class AddAdvertActivity extends AppCompatActivity{

    public EditText etName;
    public EditText etPhoneNum;
    public EditText etDesc;
    public EditText etData;
    public EditText etLocation;
    public Button btn_save;
    private DatabaseHelper dbHelper;
    public RadioGroup radioGroup;
    public RadioButton btn_lost;
    public RadioButton btn_found;
    public String type;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar getSupportAB = getSupportActionBar();
        if(!(getSupportAB == null))
        {
            getSupportAB.hide();
        }
        etName = findViewById(R.id.etName);
        etPhoneNum = findViewById(R.id.etPhoneNum);
        etDesc = findViewById(R.id.etDesc);
        etData = findViewById(R.id.etData);
        etLocation = findViewById(R.id.etLocation);
        btn_save = findViewById(R.id.btn_save);
        radioGroup = findViewById(R.id.radioGroup);
        btn_lost = findViewById(R.id.btn_lost);
        btn_found = findViewById(R.id.btn_found);
        dbHelper = new DatabaseHelper(this,"LocalDatabase.db",null,1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == btn_lost.getId()){
                    type = btn_lost.getText().toString();
                }
                if(i == btn_found.getId()){
                    type = btn_found.getText().toString();
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                String u_id = UUID.randomUUID().toString();
                String name = etName.getText().toString();
                String phone = etPhoneNum.getText().toString();
                String description = etDesc.getText().toString();
                String date = etData.getText().toString();
                String location = etLocation.getText().toString();
                contentValues.put("u_id",u_id);
                contentValues.put("name",name);
                contentValues.put("phone",phone);
                contentValues.put("description",description);
                contentValues.put("date",date);
                contentValues.put("location",location);
                contentValues.put("type",type);
                db.insert("Item",null,contentValues);
                Toast.makeText(AddAdvertActivity.this,"Add successfully",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }


}