package com.csc394.gsp.gradschoolplanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;import java.lang.Override;import java.lang.String;

/**
 * Created by MP on 10/5/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    //degree table
    public static final String TABLE_DEGREE = "degree";
    public static final String DEGREE_COLUMN_ID = "_id";
    public static final String DEGREE_COLUMN_NAME = "name";

    //permissions table
    public static final String TABLE_PERMISSIONS = "permissions";
    public static final String PERM_COLUMN_ID = "_id";
    public static final String PERM_COLUMN_NAME = "type";

    //students table
    public static final String TABLE_STUDENTS = "students";
    public static final String STUDENTS_COLUMN_ID = "_id";
    public static final String STUDENTS_COLUMN_FNAME = "first_name";
    public static final String STUDENTS_COLUMN_LNAME = "last_name";
    public static final String STUDENTS_COLUMN_EMAIL = "email";
    public static final String STUDENTS_COLUMN_DEGREEID = "degree_id";
    public static final String STUDENTS_COLUMN_STARTTERMQ = "start_term_quarter";
    public static final String STUDENTS_COLUMN_STARTTERMY = "start_term_year";
    public static final String STUDENTS_COLUMN_COURSESPERQ = "courses_per_q";
    public static final String STUDENTS_COLUMN_PERMID = "permissions_id";

    //faculty table
    public static final String TABLE_FACULTY = "faculty";
    public static final String FACULTY_COLUMN_ID = "_id";
    public static final String FACULTY_COLUMN_FNAME = "first_name";
    public static final String FACULTY_COLUMN_LNAME = "last_name";
    public static final String FACULTY_COLUMN_EMAIL = "email";
    public static final String FACULTY_COLUMN_PERMID = "permissions_id";

    //offered table
    public static final String TABLE_OFFERED= "offered";
    public static final String OFFERED_COLUMN_ID = "_id";
    public static final String OFFERED_COLUMN_TERM = "term";
    public static final String OFFERED_COLUMN_YEAR = "year";

    //courses table
    public static final String TABLE_COURSES = "courses";
    public static final String COURSES_COLUMN_ID = "_id";
    public static final String COURSES_COLUMN_DESCR = "description";
    public static final String COURSES_COLUMN_DEGREEID = "degree_id";
    public static final String COURSES_COLUMN_HASPREREQ = "has_prereq";
    public static final String COURSES_COLUMN_TYPE = "type";
    public static final String COURSES_COLUMN_CREDITHRS = "credit_hours";

    //courses-prereqs intersection table
    public static final String TABLE_COURSESPREREQS = "courses_prereqs";
    public static final String COURSESPREREQS_COLUMN_COURSEID = "_id_course_id";
    public static final String COURSESPREREQS_COLUMN_PREREQID = "_id_prereq_id";

    //
    //courses-offered intersection table
    public static final String TABLE_COURSESOFFERED = "courses_offered";
    public static final String COURSESOFFERED_COLUMN_COURSEID = "_id_course_id";
    public static final String COURSESOFFERED_COLUMN_TERMID = "_id_term_id";


    private static final String DATABASE_NAME = "capstone.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_TABLE_DEGREE = "create table "
            + TABLE_DEGREE + "(" + DEGREE_COLUMN_ID
            + " integer primary key autoincrement, " + DEGREE_COLUMN_NAME
            + " text not null);";

    private static final String DATABASE_CREATE_TABLE_PERMISSIONS = "create table "
            + TABLE_PERMISSIONS + "(" + PERM_COLUMN_ID
            + " integer primary key autoincrement, " + PERM_COLUMN_NAME
            + " text not null);";

    private static final String DATABASE_CREATE_TABLE_STUDENTS = "create table "
            + TABLE_STUDENTS + "("
            + STUDENTS_COLUMN_ID + " integer primary key autoincrement, "
            + STUDENTS_COLUMN_FNAME + "text not null,"
            + STUDENTS_COLUMN_LNAME + "text not null,"
            + STUDENTS_COLUMN_EMAIL + "text not null,"
            + STUDENTS_COLUMN_DEGREEID + "integer not null,"
            + STUDENTS_COLUMN_STARTTERMQ + "text not null,"
            + STUDENTS_COLUMN_STARTTERMY + "integer not null,"
            + STUDENTS_COLUMN_COURSESPERQ + "integer not null,"
            + STUDENTS_COLUMN_PERMID + " integer not null);";

    private static final String DATABASE_CREATE_TABLE_FACULTY = "create table "
            + TABLE_FACULTY + "("
            + FACULTY_COLUMN_ID + " integer primary key autoincrement, "
            + FACULTY_COLUMN_FNAME + "text not null,"
            + FACULTY_COLUMN_LNAME + "text not null,"
            + FACULTY_COLUMN_EMAIL + "text not null,"
            + FACULTY_COLUMN_PERMID + " integer not null);";

    private static final String DATABASE_CREATE_TABLE_OFFERED = "create table "
            + TABLE_OFFERED + "("
            + OFFERED_COLUMN_ID + " integer primary key autoincrement, "
            + OFFERED_COLUMN_TERM + "text not null,"
            + OFFERED_COLUMN_YEAR + " integer not null);";

    private static final String DATABASE_CREATE_TABLE_COURSES= "create table "
            + TABLE_COURSES + "("
            + COURSES_COLUMN_ID + " integer primary key autoincrement, "
            + COURSES_COLUMN_DESCR + "text not null,"
            + COURSES_COLUMN_DEGREEID + "integer not null,"
            + COURSES_COLUMN_HASPREREQ + "integer not null,"
            + COURSES_COLUMN_TYPE + "text not null,"
            + COURSES_COLUMN_CREDITHRS + " integer not null);";

    private static final String DATABASE_CREATE_TABLE_COURSESPREREQS= "create table "
            + TABLE_COURSESPREREQS + "("
            + COURSESPREREQS_COLUMN_COURSEID + " integer not null, "
            + COURSESPREREQS_COLUMN_PREREQID + " integer not null, "
            + "foreign key(" + COURSESPREREQS_COLUMN_COURSEID
            + ")" + "references" + TABLE_COURSES + "(" + COURSES_COLUMN_ID + "),"
            + "foreign key(" + COURSESPREREQS_COLUMN_PREREQID
            + ")" + "references" + TABLE_COURSES + "(" + COURSES_COLUMN_ID + "),"
            + "primary key (" + COURSESPREREQS_COLUMN_COURSEID
            + "," + COURSESPREREQS_COLUMN_PREREQID + ")"
            + ");";

    private static final String DATABASE_CREATE_TABLE_COURSESOFFERED= "create table "
            + TABLE_COURSESOFFERED + "("
            + COURSESOFFERED_COLUMN_COURSEID + " integer not null, "
            + COURSESOFFERED_COLUMN_TERMID + " integer not null, "
            + "foreign key(" + COURSESOFFERED_COLUMN_COURSEID
            + ")" + "references" + TABLE_COURSES + "(" + COURSES_COLUMN_ID + "),"
            + "foreign key(" + COURSESOFFERED_COLUMN_TERMID
            + ")" + "references" + TABLE_OFFERED + "(" + OFFERED_COLUMN_ID + "),"
            + "primary key (" + COURSESOFFERED_COLUMN_COURSEID
            + "," + COURSESOFFERED_COLUMN_TERMID + ")"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_TABLE_DEGREE);
        database.execSQL(DATABASE_CREATE_TABLE_PERMISSIONS);
        database.execSQL(DATABASE_CREATE_TABLE_STUDENTS);
        database.execSQL(DATABASE_CREATE_TABLE_FACULTY);
        database.execSQL(DATABASE_CREATE_TABLE_OFFERED);
        database.execSQL(DATABASE_CREATE_TABLE_COURSES);
        database.execSQL(DATABASE_CREATE_TABLE_COURSESPREREQS);
        database.execSQL(DATABASE_CREATE_TABLE_COURSESOFFERED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEGREE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERMISSIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACULTY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OFFERED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSESPREREQS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSESOFFERED);
        onCreate(db);
    }
}
