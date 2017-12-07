package com.example.user.bookmanager.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.user.bookmanager.models.entityes.Book;
import com.example.user.bookmanager.utils.Utils;

/**
 * Created by user on 07.12.17.
 */

public class BookService  extends BaseService{

  public BookService(Context context) {
    super(context);
  }

  public void addBook(Book book, Context context) {

    SQLiteDatabase dataBase = getHelper().getWritableDatabase();
    dataBase.beginTransaction();
    try {

      ContentValues cv = new ContentValues();
      cv.put(Utils.TABLE_BOOK_NAME, book.getName());
      cv.put(Utils.TABLE_BOOK_YEAR, book.getYear());
      cv.put(Utils.TABLE_BOOK_FK_AUTHOR, book.getAuthorId());
      cv.put(Utils.TABLE_BOOK_FK_COMPANY, book.getCompanyId());

      dataBase.insertOrThrow(Utils.TABLE_BOOK, null, cv);
      dataBase.setTransactionSuccessful();
    } catch (Exception e) {
      Log.d(Utils.OPERATION_STATUS_ERROR, "Error to add data to table ->" + Utils.TABLE_BOOK);
    } finally {
      dataBase.endTransaction();
      dataBase.close();
    }

  }

}
