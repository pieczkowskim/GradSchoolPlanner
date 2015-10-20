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
public class CourseDAO {

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private String[] allCourseCols = { MySQLiteHelper.COURSES_COLUMN_ID,
            MySQLiteHelper.COURSES_COLUMN_DESCR, MySQLiteHelper.COURSES_COLUMN_DEGREEID,
            MySQLiteHelper.COURSES_COLUMN_HASPREREQ, MySQLiteHelper.COURSES_COLUMN_CREDITHRS,
            MySQLiteHelper.COURSES_COLUMN_TYPE};

    public CourseDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Courses createCourse(String descr, long degreeId, long hasPrereq, long creditHours,
                                String type) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COURSES_COLUMN_DESCR, descr);
        values.put(MySQLiteHelper.COURSES_COLUMN_DEGREEID, degreeId);
        values.put(MySQLiteHelper.COURSES_COLUMN_HASPREREQ, hasPrereq);
        values.put(MySQLiteHelper.COURSES_COLUMN_CREDITHRS, creditHours);
        values.put(MySQLiteHelper.COURSES_COLUMN_TYPE, type);

        long insertId = db.insert(MySQLiteHelper.TABLE_COURSES , null,
                values);
        Cursor cursor = db.query(MySQLiteHelper.TABLE_COURSES ,
                allCourseCols, MySQLiteHelper.COURSES_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Courses newCourse = cursorToCourse(cursor);
        cursor.close();
        return newCourse;
    }

    public void deleteCourse(Courses course) {
        long id = course.getCourseId();
        System.out.println("Course deleted with id: " + id);
        db.delete(MySQLiteHelper.TABLE_COURSES, MySQLiteHelper.COURSES_COLUMN_ID
                + " = " + id, null);
    }

    public List<Courses> getAllCourses() {
        List<Courses> courses = new ArrayList<Courses>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_COURSES,
                allCourseCols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
           Courses course = cursorToCourse(cursor);
            //degree.add(degree);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return courses;
    }


    private Courses cursorToCourse(Cursor cursor) {
        Courses course= new Courses();
        course.setCourseId(cursor.getLong(0));
        course.setDescription(cursor.getString(1));
        course.setDegreeId(cursor.getLong(2));
        course.setHasPrereq(cursor.getLong(3));
        course.setCreditHours(cursor.getLong(4));
        course.setType(cursor.getString(5));
        return course;
    }
}

