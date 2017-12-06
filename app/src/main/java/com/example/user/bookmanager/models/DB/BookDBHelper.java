package com.example.user.bookmanager.models.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 06.12.17.
 */

public class BookDBHelper extends SQLiteOpenHelper {

  private final String DB_NAME = "books_db";
  private final String TABLE_AUTHOR = "authors";
  private final String TABLE_BOOK = "books";
  private final String TABLE_COMPANY = "companyes";
  private final int DB_VERSION = 1;

  public final String TABLE_BOOK_CREATE
      = String.format("CREATE %s ("
      + "book_id INTEGER PRIMARY KEY, "
      + "book_name TEXT, "
      + "book_author TEXT NOT NULL,"
      + "book_company INTEGER NOT NULL);", TABLE_BOOK);

  public final String TABLE_AUTHOR_CREATE
      = String.format("CREATE %s ("
      + "author_id INTEGER PRIMARY KEY, "
      + "author_name TEXT, "
      + "author_year INTEGER NOT NULL);", TABLE_AUTHOR);

  public final String TABLE_COMPANY_CREATE
      = String.format("CREATE %s ("
      + "company_id INTEGER PRIMARY KEY"
      + "company_name TEXT NOT NULL);", TABLE_COMPANY);

  public BookDBHelper(Context context, String name,
      CursorFactory factory, int version) {
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {

  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }
}
