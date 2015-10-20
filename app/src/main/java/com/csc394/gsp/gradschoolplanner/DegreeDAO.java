package com.csc394.gsp.gradschoolplanner;

/**
 * Created by Gosia_Samosia on 10/10/15.
 */

import java.lang.String;import java.lang.System;import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;import com.csc394.gsp.gradschoolplanner.MySQLiteHelper;

public class DegreeDAO {

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private String[] allDegCols = { MySQLiteHelper.DEGREE_COLUMN_ID,
            MySQLiteHelper.DEGREE_COLUMN_NAME };

    public DegreeDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Degree createDegree(String name) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.DEGREE_COLUMN_ID, name);
        long insertId = db.insert(MySQLiteHelper.TABLE_DEGREE , null,
                values);
        Cursor cursor = db.query(MySQLiteHelper.TABLE_DEGREE ,
                allDegCols, MySQLiteHelper.DEGREE_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Degree newComment = cursorToDegree(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteDegree(Degree degree) {
        long id = degree.getDegreeId();
        System.out.println("Degree deleted with id: " + id);
        db.delete(MySQLiteHelper.TABLE_DEGREE, MySQLiteHelper.DEGREE_COLUMN_ID
                + " = " + id, null);
    }

    public List<Degree> getAllDegs() {
        List<Degree> degrees = new ArrayList<Degree>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_DEGREE,
                allDegCols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Degree degree = cursorToDegree(cursor);
            //degree.add(degree);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return degrees;
    }


    private Degree cursorToDegree(Cursor cursor) {
        Degree degree = new Degree();
        degree.setDegreeId(cursor.getLong(0));
        degree.setDegreeName(cursor.getString(1));
        return degree;
    }
}
