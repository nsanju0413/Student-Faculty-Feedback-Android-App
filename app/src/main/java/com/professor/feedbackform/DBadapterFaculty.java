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
public class DBadapterFaculty {

    static final String TAG = "msg";
    static final String DATABASE_NAME = "Feedback";
    static final String TBL_SCAN = "faculty";

    static final String KEYID = "id";
    static final String NAME = "name";
    static final String MOBILE = "mobile";
    static final String MAIL = "mail";
    static final String DEPARTMENT = "depart";
    static final String USERNAME = "username";
    static final String PASSWORD = "password";


    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBadapterFaculty(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table faculty(id integer primary key autoincrement,name text not null,mobile text not null,mail text not null,depart text not null,username text not null,password text not null);");
        }

        public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {

            Log.w(TAG, "Upgrading database from version" + oldversion
                    + "to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS faculty");

            onCreate(db);


        }
    }

    public DBadapterFaculty open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public long insertfaculty(String name, String mobile, String mail, String depart, String username, String password) {
        ContentValues con = new ContentValues();

        con.put(NAME, name);
        con.put(MOBILE, mobile);
        con.put(MAIL, mail);
        con.put(DEPARTMENT, depart);
        con.put(USERNAME, username);
        con.put(PASSWORD, password);

        return db.insert(TBL_SCAN, null, con);
    }

    public String getpassword(String username) {

        if (username.equals("")) return "";
        ///   SQLiteDatabase database = this.getWritableDatabase();

        DBHelper.getWritableDatabase();

        String selectQuery = "SELECT  password FROM faculty WHERE username='" + username + "'";
        Log.d("query=", selectQuery);

        // Open a database for reading and writing


        // Cursor provides read and write access for the
        // data returned from a database query

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to the first row
        String str = "";
        if (cursor.moveToFirst()) {
            str = cursor.getString(0);
        }

        return str;
    }

    public String getmobile(String username) {

        if (username.equals("")) return "";
        ///   SQLiteDatabase database = this.getWritableDatabase();

        DBHelper.getWritableDatabase();

        String selectQuery = "SELECT  mobile FROM faculty WHERE username='" + username + "'";
        Log.d("query=", selectQuery);

        // Open a database for reading and writing


        // Cursor provides read and write access for the
        // data returned from a database query

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to the first row
        String str = "";
        if (cursor.moveToFirst()) {
            str = cursor.getString(0);
        }

        return str;
    }

    public Cursor fetchPassword(String scantext) {
        Cursor myCursor = db.query(true, TBL_SCAN, new String[]{USERNAME, PASSWORD}, USERNAME + "=" + scantext + "", null, null, null, null, null);
        if (myCursor != null) {
            myCursor.moveToFirst();
        }
        return myCursor;
    }

    //	public Cursor fetchUserAccount(String scantext)
//	{
//		Cursor myCursor=db.query(true,TBL_SCAN, new String[] {ACCOUTNAME,ACCOUNTNUMBER,ACCOUNTBALANCE}, ACCOUNTUSERNAME+"="+scantext+"",null,null,null,null,null);
//		if(myCursor!=null)
//		{
//			myCursor.moveToFirst();
//		}
//		return myCursor;
//	}
    void deleteOneRecord(String rowid) {
        db.delete(TBL_SCAN, rowid + "=" + KEYID, null);
    }

    long upDateRecord(String rowid, String name, String mobile, String mail, String depart) {

        ContentValues con = new ContentValues();

        con.put(NAME, name);
        con.put(MOBILE, mobile);
        con.put(MAIL, mail);
        con.put(DEPARTMENT, depart);

        System.out.println("values Updated successfully.......");
        return db.update(TBL_SCAN, con, rowid + "=" + KEYID, null);
    }

    Cursor getAllAccounts() {
        String[] columns = {KEYID, NAME, MOBILE, MAIL, DEPARTMENT, USERNAME, PASSWORD};

        return db.query(TBL_SCAN, columns, null, null, null, null, null);
    }

}

