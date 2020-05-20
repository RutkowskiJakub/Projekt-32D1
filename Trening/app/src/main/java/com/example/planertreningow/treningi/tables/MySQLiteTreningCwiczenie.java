package com.example.planertreningow.treningi.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.planertreningow.treningi.encje.TreningCwiczenie;

public class MySQLiteTreningCwiczenie extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public MySQLiteTreningCwiczenie(Context context){
        super(context, "trening_cwiczenieDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE =
                "create table trening_cwiczenie" +
                        "(trening_id integer not null," +
                        "cwiczenie_id integer not null);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS trening_cwiczenie");
        onCreate(db);
    }

    public void dodaj(TreningCwiczenie cwiczenieSeria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("trening_id", cwiczenieSeria.getTrening_id());
        values.put("cwiczenie_id", cwiczenieSeria.getCwiczenie_id());

        db.insert("trening_cwiczenie", null, values);
        db.close();
    }

    public void usun(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("trening_cwiczenie", "trening_id = ?",new String[] { id });
        db.close();

    }

    public int aktualizuj(TreningCwiczenie treningCwiczenie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trening_id", treningCwiczenie.getTrening_id());
        values.put("cwiczenie_id", treningCwiczenie.getCwiczenie_id());

        int i = db.update("trening_cwiczenie", values, "cwiczenie_id = ?", new String[]{String.valueOf(treningCwiczenie.getCwiczenie_id())});
        db.close();

        return i;
    }

    /**
     *
     * @param id cwiczenie_id
     * @return element by cwiczenie_id
     */
    public TreningCwiczenie pobierz(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("trening_cwiczenie", new String[]{"trening_id", "cwiczenie_id"}, "cwiczenie_id = ?", new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        TreningCwiczenie treningCwiczenie = new TreningCwiczenie(cursor.getInt(0), cursor.getInt(1) );

        return treningCwiczenie;
    }

    public Cursor lista(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from trening_cwiczenie",null);
    }
}
