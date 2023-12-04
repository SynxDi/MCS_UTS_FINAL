package com.example.mcs_uts_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTicketHelper extends SQLiteOpenHelper {

    // Nama dan versi database
    private static final String DATABASE_NAME = "ticket_db";
    private static final int DATABASE_VERSION = 1;

    // Nama tabel dan kolom-kolom dalam tabel
    public static final String TABLE_TICKET = "tickets";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_NO_TELEPON = "no_telepon";
    public static final String COLUMN_QUANTITY = "quantity";

    // Query pembuatan tabel TICKET
    private static final String CREATE_TABLE_TICKET = "CREATE TABLE " + TABLE_TICKET + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAMA + " TEXT,"
            + COLUMN_NO_TELEPON + " TEXT,"
            + COLUMN_QUANTITY + " INTEGER"
            + ")";

    // Konstruktor
    public DatabaseTicketHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Override method onCreate untuk membuat tabel TICKET saat database dibuat
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TICKET);
    }

    // Override method onUpgrade untuk menghapus tabel TICKET saat melakukan upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKET);
        onCreate(db);
    }

    // Method untuk mendapatkan semua tiket dari tabel TICKET
    public Cursor getAllTickets() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_TICKET, null, null, null, null, null, null);
    }


    // Method untuk menghapus semua tiket dari tabel TICKET
    public void deleteAllTickets() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TICKET, null, null);
    }
}
