package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "caveat1.db";
    public static final String ID = "id";

    /*
     * Table names
    CREATE TABLE IF NOT EXISTS `query` (
        `id` INTEGER PRIMARY KEY AUTOINCREMENT,
        `title` TEXT,
        `course` TEXT,
        `tags` TEXT,
        `query` TEXT,
        `anonymous` BOOL
    );
    *
    * QUERY TABLE
     */

    public static final String TABLE_NAME = "Queries";
    public static final String query_title = "Title";
    public static final String query_stud_course = "Course";
    public static final String query_tags = "Tags";
    public static final String query_msg= "QueryMessage";
    public static final String isAnonymousBool = "IsAnonymous";

    public static final String TABLE_NAME1 = "Suggestions";
    public static final String suggestion_title = "Title";
    public static final String suggestion_stud_course = "Course";
    public static final String suggestion_tags = "Tags";
    public static final String suggestion_msg= "SuggestionMessage";
    public static final String suggestion_isAnonymousBool = "IsAnonymous";

    public static final String TABLE_NAME2 = "Users";
    public static final String user_name = "Name";
    public static final String user_email = "Email";
    public static final String user_password = "Password";

    String createQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + query_title + " TEXT, " + query_stud_course + " TEXT, " + query_tags + " TEXT, " + query_msg + " TEXT, " + isAnonymousBool + " BOOL)";

    String query2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + suggestion_title + " TEXT, " + suggestion_stud_course + " TEXT, " + suggestion_tags + " TEXT, " + suggestion_msg + " TEXT, " + suggestion_isAnonymousBool + " BOOL)";

    String query3 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + user_name + " TEXT, " + user_email + " TEXT, " + user_password + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(query3);
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }

    public int insertQuery(Query query) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(query_title, query.getTitle());
        contentValues.put(query_stud_course, query.getCourse());
        contentValues.put(query_tags, query.getTags());
        contentValues.put(query_msg, query.getQuery());
        contentValues.put(isAnonymousBool, query.isAnonymous());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return 0;
        } else {
            return 1;
        }
    }
    
    public int insertSuggestion(Suggestion suggestion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(suggestion_title, suggestion.getTitle());
        contentValues.put(suggestion_stud_course, suggestion.getCourse());
        contentValues.put(suggestion_tags, suggestion.getTags());
        contentValues.put(suggestion_msg, suggestion.getSuggestion());
        contentValues.put(suggestion_isAnonymousBool, suggestion.isAnonymous());

        long result = db.insert(TABLE_NAME1, null, contentValues);

        if (result == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public Cursor readAllData() {
        System.out.println("Read all data called");
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        System.out.println("Cursor count: " + cursor.getCount());
        return cursor;
    }

    public int getCountOfQueries(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    public int getCountOfSuggestions(){
        String query = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    public Cursor readAllSuggestions(){
        System.out.println("Read all suggestion called");
        String query = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        System.out.println("Cursor count: " + cursor.getCount());
        return cursor;
    }

    public int insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(user_name, user.getName());
        contentValues.put(user_email, user.getEmail());
        contentValues.put(user_password, user.getPassword());

        long result = db.insert(TABLE_NAME2, null, contentValues);

        if (result == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public Cursor readAllUsers(){
        System.out.println("Read all users called");
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        System.out.println("Cursor count: " + cursor.getCount());
        return cursor;
    }

    public Cursor checkEmailAndPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE " + user_email + " = '" + email + "' AND " + user_password + " = '" + password + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            return cursor;
        } else {
            System.out.println("No user found");
        }
        return null;
    }
}
