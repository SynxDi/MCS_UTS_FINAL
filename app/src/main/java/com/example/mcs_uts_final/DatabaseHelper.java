// Package untuk kelas ini
package com.example.mcs_uts_final;

// Import library yang diperlukan
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

// Deklarasi kelas DatabaseHelper yang menggextend SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {

    // Nama database
    public static final String databaseName = "Signup.db";

    // Konstruktor DatabaseHelper
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    // Override method onCreate untuk membuat tabel allusers saat database dibuat
    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
    }

    // Override method onUpgrade untuk menghapus tabel allusers saat melakukan upgrade
    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int ii) {
        MyDatabase.execSQL("drop table if exists allusers");
    }

    // Method untuk memasukkan data pengguna baru ke dalam tabel allusers
    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = MyDatabase.insert("allusers",null,contentValues);

        if (result == -1){
            return  false;
        } else {
            return true;
        }
    }

    // Method untuk memeriksa apakah email tertentu sudah terdaftar dalam tabel allusers
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});
        if(cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    // Method untuk memeriksa apakah email dan password sesuai dalam tabel allusers
    public Boolean checkEmailPass(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email,password});
        if(cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }
}
