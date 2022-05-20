package com.example.lostandfound;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context shangxiawen;
    public static final String CREATE_ITEM = "create table Item ("
            +"u_id text primary key,"
            +"name text,"
            +"phone text,"
            +"description text,"
            +"date text,"
            +"location text,"
            +"type text)";

    public DatabaseHelper(@Nullable Context shangxiawen, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(shangxiawen, name, factory, version);
        this.shangxiawen = shangxiawen;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM);
        Toast.makeText(shangxiawen,"create success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Person");
        onCreate(db);
    }
}
