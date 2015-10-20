package com.csc394.gsp.gradschoolplanner;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gosia_Samosia on 10/10/15.
 */
public class OfferedDAO {

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private String[] allOfferedCols = { MySQLiteHelper.OFFERED_COLUMN_ID,
            MySQLiteHelper.OFFERED_COLUMN_TERM, MySQLiteHelper.OFFERED_COLUMN_YEAR};

    public OfferedDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Offered createOffered(String term, long year) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.OFFERED_COLUMN_TERM, term);
        values.put(MySQLiteHelper.OFFERED_COLUMN_YEAR, year);

        long insertId = db.insert(MySQLiteHelper.TABLE_OFFERED , null,
                values);
        Cursor cursor = db.query(MySQLiteHelper.TABLE_OFFERED ,
                allOfferedCols, MySQLiteHelper.OFFERED_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Offered newOffered = cursorToOffered(cursor);
        cursor.close();
        return newOffered;
    }

    public void deleteOffered(Offered offer) {
        long id = offer.getOfferedId();
        System.out.println("Offered term deleted with id: " + id);
        db.delete(MySQLiteHelper.TABLE_OFFERED, MySQLiteHelper.OFFERED_COLUMN_ID
                + " = " + id, null);
    }

    public List<Offered> getAllOffered() {
        List<Offered> offereds= new ArrayList<Offered>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_OFFERED,
                allOfferedCols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Offered offer = cursorToOffered(cursor);
            //degree.add(degree);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return offereds;
    }


    private Offered cursorToOffered(Cursor cursor) {
        Offered offered= new Offered();
        offered.setOfferedId(cursor.getLong(0));
        offered.setTerm(cursor.getString(1));
        offered.setYear(cursor.getLong(2));
        return offered;
    }
}

