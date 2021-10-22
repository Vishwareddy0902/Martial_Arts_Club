package com.example.martialartsclub.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price";
    private static final String COLOR_KEY = "color";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createSQLDatabase = "create table " + MARTIAL_ARTS_TABLE +
                "( " + ID_KEY + " integer primary key autoincrement" +
                ", " + NAME_KEY + " text" + ", " + PRICE_KEY + " real" +
                ", " + COLOR_KEY + " text" +" )";
        db.execSQL(createSQLDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + MARTIAL_ARTS_TABLE);
        onCreate(db);

    }

    public void addMartialArt(MartialArt martialArt){

        SQLiteDatabase database = getWritableDatabase();
        String addMartialArtSQLCommand = "insert into " + MARTIAL_ARTS_TABLE +
                " values(null, '" + martialArt.getMartialArtName()
                +"', '" + martialArt.getMartialArtPrice()
                +"', '" + martialArt.getMartialArtColor()
                + "')";
        database.execSQL(addMartialArtSQLCommand);
        database.close();

    }

    public void deleteMartialArtByID(int id){

        SQLiteDatabase database = getWritableDatabase();
        String deleteMartialArtSQLCommand = "delete from " + MARTIAL_ARTS_TABLE +
                                            " where " + ID_KEY +" = " + id;
        database.execSQL(deleteMartialArtSQLCommand);
        database.close();

    }

    public void updateMartialArt(int martialArtID , String martialArtName ,
                                 double martialArtPrice, String martialArtColor){

        SQLiteDatabase database = getWritableDatabase();
        String updateMartialArtSQLCommand = "update " + MARTIAL_ARTS_TABLE + " set "
                + NAME_KEY + " = '" + martialArtName
                + "', " + PRICE_KEY + " = '" + martialArtPrice
                + "', " + COLOR_KEY + " = '" + martialArtColor
                + "' " + "where " + ID_KEY + " = " + martialArtID;
        database.execSQL(updateMartialArtSQLCommand);
        database.close();

    }

    public ArrayList<MartialArt> returnAllMartialArts(){

        SQLiteDatabase database = getWritableDatabase();
        String SQLQueryCommand = "select * from " + MARTIAL_ARTS_TABLE;
        Cursor cursor = database.rawQuery(SQLQueryCommand,null);

        ArrayList<MartialArt> martialArts = new ArrayList<>();

        while(cursor.moveToNext()){
            MartialArt currentMartialArt = new MartialArt(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3));
            martialArts.add(currentMartialArt);
        }

        database.close();
        return martialArts;

    }

    public MartialArt returnMartialArtByID(int id){

        SQLiteDatabase database = getWritableDatabase();
        String SQLQueryCommand = "select * from " + MARTIAL_ARTS_TABLE +
                                 " where " + ID_KEY + " = " + id;
        Cursor cursor = database.rawQuery(SQLQueryCommand,null);

        MartialArt martialArt = null;

        if (cursor.moveToFirst()){
            martialArt = new MartialArt(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3));
        }

        database.close();
        return martialArt;

    }

}
