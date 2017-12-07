package com.example.user.bookmanager.models.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.user.bookmanager.utils.Utilites;

/**
 * Created by user on 06.12.17.
 */

public class BookDBHelper extends SQLiteOpenHelper {

  /**
   * DataBase info
   */
  private static final String DB_NAME = "books_db";
  private static final int DB_VERSION = 1;

  /**
   * Author table columns
   */
  private final String TABLE_AUTHOR =      "authors";
  private final String TABLE_AUTHOR_ID =   "author_id";
  private final String TABLE_AUTHOR_NAME = "author_name";
  private final String TABLE_AUTHOR_YEAR = "author_year";

  /**
   * Books table columns
   */
  private final String TABLE_BOOK =            "books";
  private final String TABLE_BOOK_ID =         "book_id";
  private final String TABLE_BOOK_NAME =       "book_name";
  private final String TABLE_BOOK_YEAR =       "book_year";
  private final String TABLE_BOOK_FK_AUTHOR =  "author_id";
  private final String TABLE_BOOK_FK_COMPANY = "company_id";

  /**
   * Company table columns
   */
  private final String TABLE_COMPANY =      "companyes";
  private final String TABLE_COMPANY_ID =   "company_id";
  private final String TABLE_COMPANY_NAME = "company_name";


  public final String SQL_CREATE_TABLE_BOOK
      ="CREATE TABLE IF NOT EXISTS " + TABLE_BOOK + "("
      + TABLE_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + TABLE_BOOK_NAME + " TEXT, "
      + TABLE_BOOK_YEAR + " TEXT, "
      + TABLE_BOOK_FK_AUTHOR + " INTEGER, "
      + TABLE_BOOK_FK_COMPANY + " INTEGER, "
      + "FOREIGN KEY("+ TABLE_BOOK_FK_AUTHOR + ") REFERENCES " + TABLE_AUTHOR +"(" + TABLE_AUTHOR_ID + ")"
      + "FOREIGN KEY("+ TABLE_BOOK_FK_COMPANY + ") REFERENCES " + TABLE_COMPANY +"(" + TABLE_COMPANY_ID + ")"
      + ");";

  public final String SQL_CREATE_TABLE_AUTHOR
      = "CREATE TABLE IF NOT EXISTS " + TABLE_AUTHOR + "("
      + TABLE_AUTHOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + TABLE_AUTHOR_NAME + " TEXT NOT NULL,"
      + TABLE_AUTHOR_YEAR + "INTEGER NOT NULL"
      + ");";

  public final String SQL_CREATE_TABLE_COMPANY
      = "CREATE TABLE IF NOT EXISTS " + TABLE_COMPANY + "("
      + TABLE_COMPANY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
      + TABLE_COMPANY_NAME + "TEXT"
      +");";

  public BookDBHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }


  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {

    Log.d(Utilites.LOG_TAG, "");

  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }
}
