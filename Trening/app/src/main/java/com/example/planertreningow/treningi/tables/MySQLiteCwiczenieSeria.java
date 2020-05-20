package com.example.planertreningow.treningi.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.planertreningow.treningi.encje.CwiczenieSeria;
import com.example.planertreningow.treningi.encje.Seria;

public class MySQLiteCwiczenieSeria extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public MySQLiteCwiczenieSeria(Context context){
        super(context, "cwiczenie_seriaDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE =
                "create table cwiczenie_seria " +
                        "(cwiczenie_id integer not null," +
                        "waga text not null," +
                        "powtorzenia text not null);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cwiczenie_seria");
        onCreate(db);
    }

    public void dodaj(CwiczenieSeria cwiczenieSeria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("cwiczenie_id", cwiczenieSeria.getCwiczenie_id());
        values.put("powtorzenia", cwiczenieSeria.getRepeats());
        values.put("waga", cwiczenieSeria.getWeights());

        db.insert("cwiczenie_seria", null, values);
        db.close();
    }

    public void usun(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cwiczenie_seria", "cwiczenie_id = ?",new String[] { id });
        db.close();

    }

    public int aktualizuj(CwiczenieSeria cwiczenieSeria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cwiczenie_id", cwiczenieSeria.getCwiczenie_id());
        values.put("powtorzenia", cwiczenieSeria.getRepeats());
        values.put("waga", cwiczenieSeria.getWeights());

        int i = db.update("cwiczenie_seria", values, "cwiczenie_id = ?", new String[]{String.valueOf(cwiczenieSeria.getCwiczenie_id())});
        db.close();

        return i;
    }

    /**
     *
     * @param id seria_id
     * @return element by cwiczenia_id
     */
    public CwiczenieSeria pobierz(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("cwiczenie_seria", new String[]{"cwiczenie_id", "powtorzenia", "waga"}, "cwiczenie_id = ?", new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Seria seria = new Seria(cursor.getInt(1), cursor.getDouble(2));
        CwiczenieSeria cwiczenie_seria = new CwiczenieSeria(cursor.getInt(0),seria);

        return cwiczenie_seria;
    }

    public Cursor lista(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from cwiczenie_seria",null);
    }
}
