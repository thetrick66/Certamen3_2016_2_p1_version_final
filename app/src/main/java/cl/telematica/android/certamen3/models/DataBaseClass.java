package cl.telematica.android.certamen3.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Patricio on 18-11-2016.
 */

public class DataBaseClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Favorites.db";
    public static final String TABLE_NAME="faorite_table";
    public static final String COL_1="ID";
    public static final String COL_2="TITLE";
    public static final String COL_3="LINK";
    public static final String COL_4="AUTHOR";
    public static final String COL_5="PUBLISHED";
    public static final String COL_6="CONTENT";
    public static final String COL_7="IMAGE";

    public DataBaseClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +" ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT,"+COL_3+" TEXT,"+COL_4+" TEXT,"+COL_5+" TEXT,"+COL_6+" TEXT,"+COL_7+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title,String link,String author,String published,String content,String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,link);
        contentValues.put(COL_4,author);
        contentValues.put(COL_5,published);
        contentValues.put(COL_6,content);
        contentValues.put(COL_7,image);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id, String title,String link,String author,String published,String content,String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,link);
        contentValues.put(COL_4,author);
        contentValues.put(COL_5,published);
        contentValues.put(COL_6,content);
        contentValues.put(COL_7,image);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "LINK = ?",new String[] {link});
    }

    public Cursor getThatData(String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE LINK='"+link+"'",null);
        return res;
    }
}
