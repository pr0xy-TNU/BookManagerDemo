package com.example.user.bookmanager.services;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.user.bookmanager.models.entityes.Author;
import com.example.user.bookmanager.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 07.12.17.
 */

public class AuthorService extends BaseService {


  public AuthorService(Context context) {
    super(context);
  }

  public void addAuthor(Author author) {
    SQLiteDatabase dataBase = getHelper().getWritableDatabase();

    dataBase.beginTransaction();
    try {
      ContentValues cv = new ContentValues();
      cv.put(Utils.TABLE_AUTHOR_NAME, author.getName());
      cv.put(Utils.TABLE_AUTHOR_YEAR, author.getYearOld());

      dataBase.insertOrThrow(Utils.TABLE_AUTHOR, null, cv);
      dataBase.setTransactionSuccessful();
    } catch (Exception e) {
      Log.d(Utils.OPERATION_STATUS_ERROR, "Error to add data to table ->" + Utils.TABLE_AUTHOR);

    } finally {
      dataBase.endTransaction();
      dataBase.close();
    }

  }

  @SuppressLint("DefaultLocale")
  public List<Author> getAllAuthors() {
    SQLiteDatabase database = getHelper().getWritableDatabase();
    database.beginTransaction();
    List<Author> authorList = new ArrayList<>();
    Author temp;
    String consoleInfo;
    try {
      Cursor cursor = database.query(Utils.TABLE_AUTHOR, null, null, null, null, null, null);
      if (cursor.moveToFirst()) {
        int id = cursor.getColumnIndex(Utils.TABLE_AUTHOR_ID);
        int name = cursor.getColumnIndex(Utils.TABLE_AUTHOR_NAME);
        int yearOld = cursor.getColumnIndex(Utils.TABLE_AUTHOR_YEAR);
        do {
          temp = new Author(cursor.getInt(id),
              cursor.getString(name),
              cursor.getInt(yearOld));
          authorList.add(temp);
          consoleInfo = String.format("%d%s%d",
              cursor.getInt(id),
              cursor.getString(name),
              cursor.getInt(yearOld));
          Log.d(Utils.LOG_TAG, consoleInfo);
        } while (cursor.moveToNext());
      }else {
        Log.d(Utils.LOG_TAG, Utils.OPERATION_STATUS_DATA_NOT_FOUND);
      }

    } catch (Exception e) {
      Log.d(Utils.OPERATION_STATUS_ERROR,
          "Error to get all data fro table ->" + Utils.TABLE_AUTHOR);
    }
    return authorList;
  }

}
