package com.example.dropnote.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dropnote.model.assignment;
import com.example.dropnote.model.event;
import com.example.dropnote.model.project;
import com.example.dropnote.model.user;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Atribute for SQL

    // Primary Features
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DropNote.db";

    // Table for SQL
    private static final String TABLE_USER = "USER";
    private static final String TABLE_ASSIGNMENT = "ASSIGNMENT";
    private static final String TABLE_EVENT = "EVENT";
    private static final String TABLE_PROJECT = "PROJECT";

    // COLUMN for TABLE_USER
    private static final String COLUMN_ID_USER = "ID_USER";
    private static final String COLUMN_NAMA = "NAMA";
    private static final String COLUMN_TANGGAL_LAHIR = "TANGGAL_LAHIR";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_PASSWORD = "PASSWORD";

    // COLUMN for TABLE_ASSIGNMENT
    private static final String COLUMN_ID_ASSIGNMENT = "ID_ACTIVITY";
    private static final String COLUMN_NAMA_ASSIGNMENT = "NAMA_ASSIGNMENT";
    private static final String COLUMN_TANGGAL_ASSIGNMENT = "TANGGAL_ASSIGNMENT";
    private static final String COLUMN_TIME_ASSIGNMENT = "TIME_ASSIGNMENT";
    private static final String COLUMN_LOCATION_ASSIGNMENT = "LOCATION_ASSIGMENT";
    private static final String COLUMN_DESCRIPTION_ASSIGNMENT = "DESCRIPTION_ASSIGNMENT";
    private static final String COLUMN_STATUS_ASSIGNMENT = "STATUS_ASSIGMENT";

    // COLUMN for TABLE_EVENT
    private static final String COLUMN_ID_EVENT = "ID_EVENT";
    private static final String COLUMN_NAMA_EVENT = "NAMA_EVENT";
    private static final String COLUMN_TANGGAL_EVENT = "TANGGAL_EVENT";
    private static final String COLUMN_TIME_EVENT = "TIME_EVENT";
    private static final String COLUMN_LOCATION_EVENT = "LOCATION_EVENT";
    private static final String COLUMN_DESCRIPTION_EVENT = "DESCRIPTION_EVENT";
    private static final String COLUMN_STATUS_EVENT = "STATUS_EVENT";

    // COLUMN for TABLE_PROJECT
    private static final String COLUMN_ID_PROJECT = "ID_PROJECT";
    private static final String COLUMN_NAMA_PROJECT = "NAMA_PROJECT";
    private static final String COLUMN_TANGGAL_PROJECT = "TANGGAL_PROJECT";
    private static final String COLUMN_TIME_PROJECT = "TIME_PROJECT";
    private static final String COLUMN_LOCATION_PROJECT = "LOCATION_PROJECT";
    private static final String COLUMN_DESCRIPTION_PROJECT = "DESCRIPTION_PROJECT";
    private static final String COLUMN_STATUS_PROJECT = "STATUS_PROJECT";

    // Database Adapter
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL String for Create USER Table
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_ID_USER
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAMA + " TEXT NOT NULL, "
                + COLUMN_TANGGAL_LAHIR + " TEXT NOT NULL, " + COLUMN_EMAIL + " TEXT NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL)";

        // SQL String for Create Assigment Table
        String CREATE_ASSIGNMENT_TABLE = "CREATE TABLE " + TABLE_ASSIGNMENT + "(" + COLUMN_ID_ASSIGNMENT
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ID_USER + " INTEGER NOT NULL, "
                + COLUMN_NAMA_ASSIGNMENT + " TEXT NOT NULL, "
                + COLUMN_TANGGAL_ASSIGNMENT + " TEXT NOT NULL, " + COLUMN_TIME_ASSIGNMENT + " TEXT NOT NULL, "
                + COLUMN_LOCATION_ASSIGNMENT + " TEXT NOT NULL, " + COLUMN_DESCRIPTION_ASSIGNMENT + " TEXT NOT NULL, "
                + COLUMN_STATUS_ASSIGNMENT + " TEXT NOT NULL, "
                + "CONSTRAINT fk_user FOREIGN KEY(ID_USER)  REFERENCES USER(ID_USER)" + ")";
        ;

        // SQL String for Create Event Table
        String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "(" + COLUMN_ID_EVENT
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ID_USER + " INTEGER NOT NULL, "
                + COLUMN_NAMA_EVENT + " TEXT NOT NULL, "
                + COLUMN_TANGGAL_EVENT + " TEXT NOT NULL, " + COLUMN_TIME_EVENT + " TEXT NOT NULL, "
                + COLUMN_LOCATION_EVENT + " TEXT NOT NULL, " + COLUMN_DESCRIPTION_EVENT + " TEXT NOT NULL, "
                + COLUMN_STATUS_EVENT + " TEXT NOT NULL, "
                + "CONSTRAINT fk_user FOREIGN KEY(ID_USER)  REFERENCES USER(ID_USER)" + ")";

        // SQL String for Create Project Table
        String CREATE_PROJECT_TABLE = "CREATE TABLE " + TABLE_PROJECT + "(" + COLUMN_ID_PROJECT
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ID_USER + " INTEGER NOT NULL,"
                + COLUMN_NAMA_PROJECT + " TEXT NOT NULL, "
                + COLUMN_TANGGAL_PROJECT + " TEXT NOT NULL, " + COLUMN_TIME_PROJECT + " TEXT NOT NULL, "
                + COLUMN_LOCATION_PROJECT + " TEXT NOT NULL, " + COLUMN_DESCRIPTION_PROJECT + " TEXT NOT NULL, "
                + COLUMN_STATUS_PROJECT + " TEXT NOT NULL, "
                + "CONSTRAINT fk_user FOREIGN KEY(ID_USER)  REFERENCES USER(ID_USER)" + ")";


        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ASSIGNMENT_TABLE);
        db.execSQL(CREATE_PROJECT_TABLE);
        db.execSQL(CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        onCreate(db);

        Log.w("DatabaseAdapter", "Upgrading database from verion"
        + oldVersion + " to " + newVersion + ". This will destroy all old data");
    }

    public long InsertUser(user user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMA, user.getNama());
        contentValues.put(COLUMN_TANGGAL_LAHIR, user.getTanggal_lahir());
        contentValues.put(COLUMN_EMAIL, user.getEmail());
        contentValues.put(COLUMN_PASSWORD, user.getPassword());

        long id = db.insert(TABLE_USER, null, contentValues);
        db.close();
        return id;
    }

    public long CheckEmailAndPassword(String email, String password){
        String[] column = {COLUMN_ID_USER};
        SQLiteDatabase db = this.getReadableDatabase();
        String Selection = COLUMN_EMAIL + "=?" + " AND " + COLUMN_PASSWORD + "+?";
        String[] selection = {email,password};
        Cursor cursor = db.query(TABLE_USER,column,Selection,selection,null,null,null);
        long count = cursor.getCount();
        db.close();

        if (count > 0){
            cursor.close();
            return count;
        } else {
            cursor.close();
            return count;
        }
    }

    public user getUser(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USER,
                new String[]{
                        COLUMN_ID_USER,
                        COLUMN_NAMA,
                        COLUMN_TANGGAL_LAHIR,
                        COLUMN_EMAIL,
                        COLUMN_PASSWORD
                }, COLUMN_ID_USER + "=?",
                new String[]{
                        String.valueOf(id)
                },
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        user user = new user(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USER)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL_LAHIR)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
        );

        cursor.close();
        return user;
    }


    public long InsertAssignment(assignment assignment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID_USER, assignment.getId_user());
        contentValues.put(COLUMN_NAMA_ASSIGNMENT, assignment.getNama_activity());
        contentValues.put(COLUMN_TANGGAL_ASSIGNMENT, assignment.getTanggal_activity());
        contentValues.put(COLUMN_LOCATION_ASSIGNMENT, assignment.getLocation_activity());
        contentValues.put(COLUMN_DESCRIPTION_ASSIGNMENT,assignment.getDescription_activity());
        contentValues.put(COLUMN_STATUS_ASSIGNMENT, assignment.getStatus_activity());

        long id = db.insert(TABLE_ASSIGNMENT, null, contentValues);
        db.close();
        return id;
    }

    // Getting All Contacts
    public ArrayList<assignment> getAllAssignment(String date){
        ArrayList<assignment> assignments = new ArrayList<>();

        String Query = "SELECT * FROM " + TABLE_ASSIGNMENT + " WHERE " + COLUMN_TANGGAL_ASSIGNMENT + " < " + date
                + " ORDER BY " + COLUMN_ID_ASSIGNMENT + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query,null);

        if (cursor.moveToFirst()){
            do{
                assignment assignment = new assignment(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_ASSIGNMENT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_ASSIGNMENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL_ASSIGNMENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_ASSIGNMENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION_ASSIGNMENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION_ASSIGNMENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS_ASSIGNMENT))
                );

                assignments.add(assignment);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return assignments;
    }

    public long InsertProject(project project){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID_USER, project.getId_user());
        contentValues.put(COLUMN_NAMA_PROJECT, project.getNama_activity());
        contentValues.put(COLUMN_TANGGAL_PROJECT, project.getTanggal_activity());
        contentValues.put(COLUMN_LOCATION_PROJECT, project.getLocation_activity());
        contentValues.put(COLUMN_DESCRIPTION_PROJECT,project.getDescription_activity());
        contentValues.put(COLUMN_STATUS_PROJECT, project.getStatus_activity());

        long id = db.insert(TABLE_PROJECT, null, contentValues);
        db.close();
        return id;
    }

    public ArrayList<project> getAllProject(String date){
        ArrayList<project> projects = new ArrayList<>();

        String Query = "SELECT * FROM " + TABLE_PROJECT + " WHERE " + COLUMN_TANGGAL_PROJECT + " < " + date
                + " ORDER BY " + COLUMN_ID_PROJECT + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query,null);

        if (cursor.moveToFirst()){
            do{
                project project = new project(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_PROJECT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_PROJECT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL_PROJECT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_PROJECT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION_PROJECT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION_PROJECT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS_PROJECT))
                );
                projects.add(project);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return projects;
    }

    public long InsertEvent(event event1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID_USER, event1.getId_user());
        contentValues.put(COLUMN_NAMA_EVENT, event1.getNama_activity());
        contentValues.put(COLUMN_TANGGAL_EVENT, event1.getTanggal_activity());
        contentValues.put(COLUMN_LOCATION_EVENT, event1.getLocation_activity());
        contentValues.put(COLUMN_DESCRIPTION_EVENT,event1.getDescription_activity());
        contentValues.put(COLUMN_STATUS_EVENT, event1.getStatus_activity());

        long id = db.insert(TABLE_EVENT, null, contentValues);
        db.close();
        return id;
    }

    public ArrayList<event> getAllEvent(String date){
        ArrayList<event> events = new ArrayList<>();

        String Query = "SELECT * FROM " + TABLE_EVENT + " WHERE " + COLUMN_TANGGAL_EVENT + " < " + date
                + " ORDER BY " + COLUMN_ID_EVENT + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query,null);

        if (cursor.moveToFirst()){
            do{
                event event = new event(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_EVENT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_USER)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA_EVENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL_EVENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_EVENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION_EVENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION_EVENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS_EVENT))
                );
                events.add(event);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return events;
    }
}
