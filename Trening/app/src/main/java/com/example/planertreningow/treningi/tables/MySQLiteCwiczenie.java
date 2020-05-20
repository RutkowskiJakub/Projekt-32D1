package com.example.planertreningow.treningi.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.planertreningow.treningi.encje.Cwiczenie;

public class MySQLiteCwiczenie extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public MySQLiteCwiczenie(Context context){
        super(context, "cwiczeniaDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE =
                "create table cwiczenia " +
                        "(_id integer primary key autoincrement," +
                        "name text not null);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS cwiczenia");
            onCreate(db);
    }

    public void dodaj(Cwiczenie cwiczenie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", cwiczenie.getName());

        db.insert("cwiczenia", null, values);
        db.close();
    }

    public void usun(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cwiczenia", "_id = ?",new String[] { id });
        db.close();

    }

    public int aktualizuj(Cwiczenie cwiczenie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", cwiczenie.getName());

        int i = db.update("cwiczenia", values, "_id = ?", new String[]{String.valueOf(cwiczenie.get_id())});
        db.close();

        return i;
    }

    public Cwiczenie pobierz(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("cwiczenia", new String[]{"_id", "name"}, "_id = ?", new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Cwiczenie cwiczenie = new Cwiczenie(cursor.getString(1));
        cwiczenie.set_id(Integer.parseInt(cursor.getString(0)));

        return cwiczenie;
    }

    public Cwiczenie pobierzPoNazwie(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("cwiczenia", new String[]{"_id", "name"}, "name = ?", new String[]{String.valueOf(name)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Cwiczenie trening = new Cwiczenie(cursor.getString(1));
        trening.set_id(Integer.parseInt(cursor.getString(0)));

        return trening;
    }

    public Cursor lista(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from cwiczenia",null);
    }
}
