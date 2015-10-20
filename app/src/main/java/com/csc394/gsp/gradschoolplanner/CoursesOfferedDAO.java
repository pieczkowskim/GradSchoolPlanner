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
public class CoursesOfferedDAO {

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private String[] allCoursesOfferedCols = { MySQLiteHelper.COURSESOFFERED_COLUMN_COURSEID,
            MySQLiteHelper.COURSESOFFERED_COLUMN_TERMID};

    public CoursesOfferedDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CoursesOffered createCoursesOffered(long courseid, long termid) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COURSESOFFERED_COLUMN_COURSEID, courseid);
        values.put(MySQLiteHelper.COURSESOFFERED_COLUMN_TERMID, termid);

        db.insert(MySQLiteHelper.TABLE_COURSESOFFERED, null, values);

        Cursor cursor = db.query(MySQLiteHelper.TABLE_COURSESOFFERED ,
                allCoursesOfferedCols, MySQLiteHelper.COURSESOFFERED_COLUMN_COURSEID + " = " + courseid + " AND " +
                MySQLiteHelper.COURSESOFFERED_COLUMN_TERMID + " = " + termid , null,
                null, null, null);

        cursor.moveToFirst();
        CoursesOffered newCourseOffered = cursorToCoursesOffered(cursor);
        cursor.close();
        return newCourseOffered;
    }

    public void deleteCoursesOffered(CoursesOffered coursesOffered) {
        long id = coursesOffered.getFCourseId();
        System.out.println("Courses offered deleted with id: " + id);
        db.delete(MySQLiteHelper.TABLE_COURSESOFFERED, MySQLiteHelper.COURSESOFFERED_COLUMN_COURSEID
                + " = " + id, null);
    }

    public List<CoursesOffered> getAllCoursesOffered() {
        List<CoursesOffered> coursesOffereds= new ArrayList<CoursesOffered>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_COURSESOFFERED,
                allCoursesOfferedCols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CoursesOffered coffer= cursorToCoursesOffered(cursor);
            //degree.add(degree);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return coursesOffereds;
    }


    private CoursesOffered cursorToCoursesOffered(Cursor cursor) {
        CoursesOffered courseOffered= new CoursesOffered();
        courseOffered.setFCourseId(cursor.getLong(0));
        courseOffered.setfTermId(cursor.getLong(1));
        return courseOffered;
    }
}

