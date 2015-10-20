package com.csc394.gsp.gradschoolplanner;

/**
 * Created by Gosia_Samosia on 10/10/15.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PermissionsDAO {

    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;
    private String[] allPermCols = { MySQLiteHelper.PERM_COLUMN_ID,
            MySQLiteHelper.PERM_COLUMN_NAME };

    public PermissionsDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Permissions createPermission(String type) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.PERM_COLUMN_ID, type);
        long insertId = db.insert(MySQLiteHelper.TABLE_PERMISSIONS , null,
                values);
        Cursor cursor = db.query(MySQLiteHelper.TABLE_PERMISSIONS,
                allPermCols, MySQLiteHelper.PERM_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Permissions newPermission = cursorToPermission(cursor);
        cursor.close();
        return newPermission;
    }

    public void deletePermission(Permissions permission) {
        long id = permission.getPermissionsId();
        System.out.println("Permission deleted with id: " + id);
        db.delete(MySQLiteHelper.TABLE_PERMISSIONS, MySQLiteHelper.PERM_COLUMN_ID
                + " = " + id, null);
    }

    public List<Permissions> getAllPermissions() {
        List<Permissions> permissions= new ArrayList<Permissions>();

        Cursor cursor = db.query(MySQLiteHelper.TABLE_PERMISSIONS,
                allPermCols, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Permissions permission = cursorToPermission(cursor);
            //permission.add(permission);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return permissions;
    }


    private Permissions cursorToPermission(Cursor cursor) {
        Permissions permission = new Permissions();
        permission.setPermissionsId(cursor.getLong(0));
        permission.setPermissionsType(cursor.getString(1));
        return permission;
    }
}
