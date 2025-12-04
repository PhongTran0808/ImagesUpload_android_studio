package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_AVATAR = "avatar";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_FULLNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_AVATAR + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_FULLNAME, user.getFullName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_GENDER, user.getGender());
        // Avatar ban đầu có thể null hoặc mặc định
        if (user.getAvatar() != null) {
            values.put(COLUMN_AVATAR, user.getAvatar());
        }

        long result = db.insert(TABLE_USER, null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " +
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " +
                COLUMN_USERNAME + "=?", new String[]{username});
        User user = null;
        if (cursor.moveToFirst()) {
             int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
             String pass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
             String fullName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULLNAME));
             String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
             String gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER));
             String avatar = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR));
             user = new User(id, username, pass, fullName, email, gender, avatar);
        }
        cursor.close();
        return user;
    }

    public boolean updateUserAvatar(int userId, String avatarPath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_AVATAR, avatarPath);
        int rows = db.update(TABLE_USER, values, COLUMN_ID + " = ?", new String[]{String.valueOf(userId)});
        return rows > 0;
    }
}
