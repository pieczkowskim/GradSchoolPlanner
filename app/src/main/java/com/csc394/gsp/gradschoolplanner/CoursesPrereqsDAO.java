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
public class CoursesPrereqsDAO {

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private String[] allCoursesPrereqsCols = { MySQLiteHelper.COURSESPREREQS_COLUMN_COURSEID,
            MySQLiteHelper.COURSESPREREQS_COLUMN_PREREQID};

    public CoursesPrereqsDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CoursesPrereqs createCoursesPrereqs(long courseid, long termid) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COURSESPREREQS_COLUMN_COURSEID, courseid);
        values.put(MySQLiteHelper.COURSESPREREQS_COLUMN_PREREQID, termid);

        db.insert(MySQLiteHelper.TABLE_COURSESPREREQS, null, values);

        Cursor cursor = db.query(MySQLiteHelper.TABLE_COURSESPREREQS ,
                allCoursesPrereqsCols, MySQLiteHelper.COURSESPREREQS_COLUMN_COURSEID + " = " + courseid + " AND " +
                        MySQLiteHelper.COURSESPREREQS_COLUMN_PREREQID + " = " + termid , null,
                null, null, null);

        cursor.moveToFirst();
        CoursesPrereqs newCoursesPrereqs= cursorToCoursesPrereqs(cursor);
        cursor.close();
        return newCoursesPrereqs;
    }

    public void deleteCoursesPrereqs(CoursesPrereqs coursePrereq) {
        long id = coursePrereq.getFCourseId();
        System.out.println("Course prereq deleted with id: " + id);
        db.delete(MySQLiteHelper.TABLE_COURSESPREREQS, MySQLiteHelper.COURSESPREREQS_COLUMN_COURSEID
                + " = " + id, null);
    }

    public List<CoursesPrereqs> getAllCoursesPrereqs() {
        List<CoursesPrereqs> coursePrereq= new ArrayList<CoursesPrereqs>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_COURSESPREREQS,
                allCoursesPrereqsCols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CoursesPrereqs coursePre= cursorToCoursesPrereqs(cursor);
            //degree.add(degree);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return coursePrereq;
    }


    private CoursesPrereqs cursorToCoursesPrereqs(Cursor cursor) {
        CoursesPrereqs coursePrereq= new CoursesPrereqs();
        coursePrereq.setFCourseId(cursor.getLong(0));
        coursePrereq.setFPrereqId(cursor.getLong(1));
        return coursePrereq;
    }
}

