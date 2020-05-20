package com.example.planertreningow.treningi.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.planertreningow.treningi.encje.Cwiczenie;
import com.example.planertreningow.treningi.encje.Trening;

public class MySQLiteTreningi extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public MySQLiteTreningi(Context context){
        super(context, "treningiDB", null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE =
                "create table treningi " +
                        "(_id integer primary key autoincrement," +
                        "name text not null);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS treningi");
        onCreate(db);
    }

    public void dodaj(Trening trening){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", trening.getName());

        db.insert("treningi", null, values);
        db.close();
    }

    public void usun(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("treningi", "_id = ?",new String[] { id });
        db.close();

    }

    public int aktualizuj(Trening trening){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", trening.getName());

        int i = db.update("treningi", values, "_id = ?", new String[]{String.valueOf(trening.get_id())});
        db.close();

        return i;
    }

    public Trening pobierz(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("treningi", new String[]{"_id", "name"}, "_id = ?", new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Trening trening = new Trening(cursor.getString(1));
        trening.set_id(Integer.parseInt(cursor.getString(0)));

        return trening;
    }

    public Trening pobierzPoNazwie(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("treningi", new String[]{"_id", "name"}, "name = ?", new String[]{String.valueOf(name)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Trening trening = new Trening(cursor.getString(1));
        trening.set_id(Integer.parseInt(cursor.getString(0)));

        return trening;
    }

    public Cursor lista(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from treningi",null);
    }
}
