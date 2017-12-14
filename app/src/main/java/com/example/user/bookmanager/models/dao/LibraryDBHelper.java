package com.example.user.bookmanager.models.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.user.bookmanager.utils.Utils;

/**
 * Created by user on 07.12.17.
 */

public class LibraryDBHelper extends SQLiteOpenHelper {

  String DB_NAME = "books_db";
  int DB_VERSION = 2;

  /**
   * Author table columns
   */
  String TABLE_AUTHOR = "authors";
  String TABLE_AUTHOR_ID = "author_id";
  String TABLE_AUTHOR_NAME = "author_name";
  String TABLE_AUTHOR_YEAR = "author_year";

  /**
   * Books table columns
   */
  String TABLE_BOOK = "booksess";
  String TABLE_BOOK_ID = "book_id";
  String TABLE_BOOK_NAME = "book_name";
  String TABLE_BOOK_YEAR = "book_year";
  String TABLE_BOOK_FK_AUTHOR = "author_id";
  String TABLE_BOOK_FK_COMPANY = "company_id";

  /**
   * Company table columns
   */
  String TABLE_COMPANY = "companyes";
  String TABLE_COMPANY_ID = "company_id";
  String TABLE_COMPANY_NAME = "company_name";

  public final String SQL_CREATE_TABLE_BOOK
      = "CREATE TABLE IF NOT EXISTS "
      + TABLE_BOOK + "("
      + TABLE_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + TABLE_BOOK_NAME + " TEXT, "
      + TABLE_BOOK_YEAR + " INTEGER, "
      + TABLE_BOOK_FK_AUTHOR + " INTEGER, "
      + TABLE_BOOK_FK_COMPANY + " INTEGER, "
      + "FOREIGN KEY(" + TABLE_BOOK_FK_AUTHOR + ") REFERENCES " + TABLE_AUTHOR + "(" + TABLE_AUTHOR_ID + "), "
      + "FOREIGN KEY(" + TABLE_BOOK_FK_COMPANY + ") REFERENCES " + TABLE_COMPANY + "(" + TABLE_COMPANY_ID + ")"
      + ");";
 /* public final String SQL_CREATE_TABLE_BOOK
      = "CREATE TABLE IF NOT EXISTS "
      + TABLE_BOOK + "("
      + TABLE_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + TABLE_BOOK_NAME + " TEXT, "
      + TABLE_BOOK_YEAR + " INTEGER, "
      + TABLE_BOOK_FK_AUTHOR + " INTEGER, "
      + TABLE_BOOK_FK_COMPANY + " INTEGER"
      + ");";*/



  public final String SQL_CREATE_TABLE_AUTHOR
      = "CREATE TABLE IF NOT EXISTS " + TABLE_AUTHOR + "("
      + TABLE_AUTHOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + TABLE_AUTHOR_NAME + " TEXT NOT NULL, "
      + TABLE_AUTHOR_YEAR + " INTEGER NOT NULL"
      + ");";

  public final String SQL_CREATE_TABLE_COMPANY
      = "CREATE TABLE IF NOT EXISTS " + TABLE_COMPANY + "("
      + TABLE_COMPANY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + TABLE_COMPANY_NAME + " TEXT"
      + ");";


  public static LibraryDBHelper instance;

  public static synchronized LibraryDBHelper getInstance(Context context) {
    if (instance == null) {
      return new LibraryDBHelper(context.getApplicationContext());
    }
    return instance;
  }


  public LibraryDBHelper(Context context) {
    super(context, Utils.DB_NAME, null, Utils.DB_VERSION);
  }


  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(SQL_CREATE_TABLE_AUTHOR);
    sqLiteDatabase.execSQL(SQL_CREATE_TABLE_COMPANY);
    sqLiteDatabase.execSQL(SQL_CREATE_TABLE_BOOK);
    Log.i(Utils.LOG_TAG, "Tables was created");
  }

  @Override
  public void onConfigure(SQLiteDatabase db) {
    super.onConfigure(db);
    db.setForeignKeyConstraintsEnabled(true);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHOR);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
    Log.i(Utils.LOG_TAG, "onUpgrade: books_db");
    onCreate(sqLiteDatabase);
  }
}
