package com.example.user.bookmanager.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.user.bookmanager.utils.Utils;

/**
 * Created by user on 07.12.17.
 */

class LibraryDBHelper extends SQLiteOpenHelper {

  public final String SQL_CREATE_TABLE_BOOK
      = "CREATE TABLE IF NOT EXISTS " + Utils.TABLE_BOOK + "("
      + Utils.TABLE_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + Utils.TABLE_BOOK_NAME + " TEXT, "
      + Utils.TABLE_BOOK_YEAR + " TEXT, "
      + Utils.TABLE_BOOK_FK_AUTHOR + " INTEGER, "
      + Utils.TABLE_BOOK_FK_COMPANY + " INTEGER, "
      + "FOREIGN KEY(" + Utils.TABLE_BOOK_FK_AUTHOR + ") REFERENCES " + Utils.TABLE_AUTHOR + "("
      + Utils.TABLE_AUTHOR_ID + "), "
      + "FOREIGN KEY(" + Utils.TABLE_BOOK_FK_COMPANY + ") REFERENCES " + Utils.TABLE_COMPANY + "("
      + Utils.TABLE_COMPANY_ID + ")"
      + ");";

  public final String SQL_CREATE_TABLE_AUTHOR
      = "CREATE TABLE IF NOT EXISTS " + Utils.TABLE_AUTHOR + "("
      + Utils.TABLE_AUTHOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + Utils.TABLE_AUTHOR_NAME + " TEXT NOT NULL,"
      + Utils.TABLE_AUTHOR_YEAR + "INTEGER NOT NULL"
      + ");";

  public final String SQL_CREATE_TABLE_COMPANY
      = "CREATE TABLE IF NOT EXISTS " + Utils.TABLE_COMPANY + "("
      + Utils.TABLE_COMPANY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
      + Utils.TABLE_COMPANY_NAME + "TEXT"
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

    Log.d(Utils.LOG_TAG, "");

  }

  @Override
  public void onConfigure(SQLiteDatabase db) {
    super.onConfigure(db);
    db.setForeignKeyConstraintsEnabled(true);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_COMPANY);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_AUTHOR);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_BOOK);
    onCreate(sqLiteDatabase);
  }
}
