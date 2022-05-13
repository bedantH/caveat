package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mad.db";
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

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + query_title + " TEXT, " + query_stud_course + " TEXT, " + query_tags + " TEXT, " + query_msg + " TEXT, " + isAnonymousBool + " BOOL)";

        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void insertQuery(Query query) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(query_title, query.getTitle());
        contentValues.put(query_stud_course, query.getCourse());
        contentValues.put(query_tags, query.getTags());
        contentValues.put(query_msg, query.getQuery());
        contentValues.put(isAnonymousBool, query.isAnonymous());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            System.out.println("Query not inserted");
        } else {
            System.out.println("Query inserted");
        }
    }

    public List getQueries() {
        List queries = new ArrayList();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Query query = new Query();
                query.setTitle(cursor.getString(1));
                query.setCourse(cursor.getString(2));
                query.setTags(cursor.getString(3));
                query.setQuery(cursor.getString(4));
                query.setAnonymous(cursor.getInt(5) == 1);

                queries.add(query);
                } while (cursor.moveToNext());
        }
        return queries;
    }
}
