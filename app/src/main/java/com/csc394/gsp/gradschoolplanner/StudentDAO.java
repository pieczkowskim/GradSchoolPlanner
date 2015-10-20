package com.csc394.gsp.gradschoolplanner;

import java.lang.String;import java.lang.System;import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gosia_Samosia on 10/10/15.
 */
public class StudentDAO {

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private String[] allStudentCols = {MySQLiteHelper.STUDENTS_COLUMN_ID,
            MySQLiteHelper.STUDENTS_COLUMN_FNAME, MySQLiteHelper.STUDENTS_COLUMN_LNAME,
            MySQLiteHelper.STUDENTS_COLUMN_EMAIL, MySQLiteHelper.STUDENTS_COLUMN_DEGREEID,
            MySQLiteHelper.STUDENTS_COLUMN_STARTTERMQ, MySQLiteHelper.STUDENTS_COLUMN_STARTTERMY,
            MySQLiteHelper.STUDENTS_COLUMN_COURSESPERQ, MySQLiteHelper.STUDENTS_COLUMN_PERMID};

    public StudentDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Students createStudent(String fname, String lname, String email, long degreeid, String startQ,
                                  long startY, long coursesperq, long permId) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.STUDENTS_COLUMN_FNAME, fname);
        values.put(MySQLiteHelper.STUDENTS_COLUMN_LNAME, lname);
        values.put(MySQLiteHelper.STUDENTS_COLUMN_EMAIL, email);
        values.put(MySQLiteHelper.STUDENTS_COLUMN_DEGREEID, degreeid);
        values.put(MySQLiteHelper.STUDENTS_COLUMN_STARTTERMQ, startQ);
        values.put(MySQLiteHelper.STUDENTS_COLUMN_STARTTERMY, startY);
        values.put(MySQLiteHelper.STUDENTS_COLUMN_COURSESPERQ, coursesperq);
        values.put(MySQLiteHelper.STUDENTS_COLUMN_PERMID, permId);

        long insertId = db.insert(MySQLiteHelper.TABLE_STUDENTS , null,
                values);
        Cursor cursor = db.query(MySQLiteHelper.TABLE_STUDENTS,
                allStudentCols, MySQLiteHelper.STUDENTS_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Students newStudent= cursorToStudent(cursor);
        cursor.close();
        return newStudent;
    }

    public void deleteStudent(Students student) {
        long id = student.getStudentId();
        System.out.println("Student deleted with id: " + id);
        db.delete(MySQLiteHelper.TABLE_STUDENTS, MySQLiteHelper.STUDENTS_COLUMN_ID
                + " = " + id, null);
    }

    public List<Students> getAllStudents() {
        List<Students> students = new ArrayList<Students>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_STUDENTS,
                allStudentCols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Students student = cursorToStudent(cursor);
            //degree.add(degree);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return students;
    }


    private Students cursorToStudent(Cursor cursor) {
        Students student= new Students();
        student.setStudentId(cursor.getLong(0));
        student.setFirstName(cursor.getString(1));
        student.setLastName(cursor.getString(2));
        student.setEmail(cursor.getString(3));
        student.setDegreeId(cursor.getLong(4));
        student.setStartTermQ(cursor.getString(5));
        student.setStartTermY(cursor.getLong(6));
        student.setcoursesPerQ(cursor.getLong(7));
        student.setPermissionsId(cursor.getLong(8));
        return student;
    }
}

