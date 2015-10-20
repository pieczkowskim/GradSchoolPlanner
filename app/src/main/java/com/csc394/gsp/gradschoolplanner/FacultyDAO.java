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
public class FacultyDAO {

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private String[] allFacultyCols = {MySQLiteHelper.FACULTY_COLUMN_ID,
            MySQLiteHelper.FACULTY_COLUMN_FNAME, MySQLiteHelper.FACULTY_COLUMN_LNAME,
            MySQLiteHelper.FACULTY_COLUMN_EMAIL, MySQLiteHelper.FACULTY_COLUMN_PERMID};

    public FacultyDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Faculty createFaculty(String fname, String lname, String email, long permId) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.FACULTY_COLUMN_FNAME, fname);
        values.put(MySQLiteHelper.FACULTY_COLUMN_LNAME, lname);
        values.put(MySQLiteHelper.FACULTY_COLUMN_EMAIL, email);
        values.put(MySQLiteHelper.FACULTY_COLUMN_PERMID, permId);

        long insertId = db.insert(MySQLiteHelper.TABLE_FACULTY , null,
                values);
        Cursor cursor = db.query(MySQLiteHelper.TABLE_FACULTY ,
                allFacultyCols, MySQLiteHelper.FACULTY_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Faculty newFaculty = cursorToFaculty(cursor);
        cursor.close();
        return newFaculty;
    }

    public void deleteFaculty(Faculty faculty) {
        long id = faculty.getFacultyId();
        System.out.println("Faculty member deleted with id: " + id);
        db.delete(MySQLiteHelper.TABLE_FACULTY, MySQLiteHelper.FACULTY_COLUMN_ID
                + " = " + id, null);
    }

    public List<Faculty> getAllFaculty() {
        List<Faculty> facultyList = new ArrayList<Faculty>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_FACULTY,
                allFacultyCols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Faculty faculty = cursorToFaculty(cursor);
            //degree.add(degree);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return facultyList;
    }


    private Faculty cursorToFaculty(Cursor cursor) {
        Faculty faculty= new Faculty();
        faculty.setFacultyId(cursor.getLong(0));
        faculty.setFirstName(cursor.getString(1));
        faculty.setLastName(cursor.getString(2));
        faculty.setEmail(cursor.getString(3));
        faculty.setPermissionsId(cursor.getLong(4));
        return faculty;
    }
}

