package com.professor.feedbackform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HOME on 3/8/2016.
 */
public class DBadapterComment {

    static final String TAG = "msg";
    static final String DATABASE_NAME = "FeedbackForm";
    static final String TBL_SCAN = "comment";

    static final String KEYID = "id";
    static final String NAME = "name";
    static final String STATUS = "status";
    static final String SUBJECT_NAME = "subject";
    static final String RATING = "rating";
    static final String COMMENT = "comment";


    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBadapterComment(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table comment(id integer primary key autoincrement,name text not null,status text not null,subject text not null,rating text not null,comment text not null);");
        }

        public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {

            Log.w(TAG, "Upgrading database from version" + oldversion
                    + "to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS comment");

            onCreate(db);


        }
    }

    public DBadapterComment open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public long insertcomment(String name, String status, String subjectname, String rating, String comment) {
        ContentValues con = new ContentValues();

        con.put(NAME, name);
        con.put(STATUS, status);
        con.put(SUBJECT_NAME, subjectname);
        con.put(RATING, rating);
        con.put(COMMENT, comment);


        return db.insert(TBL_SCAN, null, con);
    }

    void deleteOneRecord(String rowid) {
        db.delete(TBL_SCAN, rowid + "=" + KEYID, null);
    }

    long upDateRecord(String rowid, String name, String status, String subjectname, String rating, String comment) {

        ContentValues con = new ContentValues();

        con.put(NAME, name);
        con.put(STATUS, status);
        con.put(SUBJECT_NAME, subjectname);
        con.put(RATING, rating);
        con.put(COMMENT, comment);

        System.out.println("values Updated successfully.......");
        return db.update(TBL_SCAN, con, rowid + "=" + KEYID, null);
    }

    Cursor getAllAccounts() {
        String[] columns = {KEYID, NAME, STATUS, SUBJECT_NAME, RATING, COMMENT};

        return db.query(TBL_SCAN, columns, null, null, null, null, null);
    }

}

