package com.example.planertreningow.treningi.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.planertreningow.treningi.encje.Seria;

public class MySQLiteSeria extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public MySQLiteSeria(Context context){
        super(context, "serieDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE =
                "create table serie " +
                        "(_id integer primary key autoincrement," +
                        "repeats text not null," +
                        "weight text not null);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS serie");
        onCreate(db);
    }

    public void dodaj(Seria seria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("repeats", seria.getRepeats());
        values.put("weight", seria.getWeights());

        db.insert("serie", null, values);

        db.close();
    }

    public void usun(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("serie", "_id = ?",new String[] { id });
        db.close();

    }

    public int aktualizuj(Seria seria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("repeats", seria.getRepeats());
        values.put("weight", seria.getWeights());

        int i = db.update("serie", values, "_id = ?", new String[]{String.valueOf(seria.get_id())});
        db.close();

        return i;
    }

    public Seria pobierz(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("serie", new String[]{"_id", "repeats", "weight"}, "_id = ?", new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        Seria seria = new Seria(Integer.parseInt(cursor.getString(1)), Double.parseDouble(cursor.getString(2)));
        seria.set_id(Integer.parseInt(cursor.getString(0)));

        return seria;
    }

    public Seria pobierzPo(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("serie", new String[]{"_id", "repeats", "weight"}, "_id = ?", new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        Seria seria = new Seria(Integer.parseInt(cursor.getString(1)), Double.parseDouble(cursor.getString(2)));
        seria.set_id(Integer.parseInt(cursor.getString(0)));

        return seria;
    }

    public Cursor lista(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from serie",null);
    }
}
