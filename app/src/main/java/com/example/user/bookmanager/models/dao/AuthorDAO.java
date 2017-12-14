package com.example.user.bookmanager.models.dao;

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

public class AuthorDAO extends BaseDAO {

  private SQLiteDatabase dataBase;


  public AuthorDAO(Context context) {
    super(context);
  }

  public void addAuthor(Author author) {
    dataBase = getHelper().getWritableDatabase();

    dataBase.beginTransaction();
    try {
      ContentValues cv = new ContentValues();
      cv.put(Utils.TABLE_AUTHOR_NAME, author.getName());
      cv.put(Utils.TABLE_AUTHOR_YEAR, author.getYearOld());
      dataBase.insertOrThrow(Utils.TABLE_AUTHOR, null, cv);
      Log.i(Utils.LOG_TAG, "addAuthor: Author was added");
      dataBase.setTransactionSuccessful();
    } catch (Exception e) {
      Log.d(Utils.OPERATION_STATUS_ERROR, "Error to add data to table ->" + Utils.TABLE_AUTHOR);
    } finally {
      dataBase.endTransaction();
      dataBase.close();
    }

  }


  public void addAuthors(List<Author> authorList) {
    for (Author item : authorList
        ) {
      addAuthor(item);
    }
  }

  @SuppressLint("DefaultLocale")
  public List<Author> getAllAuthors() {
    dataBase = getHelper().getWritableDatabase();
    List<Author> authorList = new ArrayList<>();
    Author temp;
    try {
      Cursor cursor = dataBase.query(Utils.TABLE_AUTHOR, null, null, null, null, null, null);
      if (cursor.moveToFirst()) {

        int idColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_ID);
        int nameColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_NAME);
        int yearOldColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_YEAR);

        do {

          int id = cursor.getInt(idColumnIndex);
          String name = cursor.getString(nameColumnIndex);
          int year = cursor.getInt(yearOldColumnIndex);

          temp = new Author(id, name, year);
          authorList.add(temp);
        } while (cursor.moveToNext());
      } else {
        Log.d(Utils.LOG_TAG, Utils.OPERATION_STATUS_DATA_NOT_FOUND);
      }

    } catch (Exception e) {
      Log.d(Utils.OPERATION_STATUS_ERROR,
          "Error to get all data fro table ->" + Utils.TABLE_AUTHOR);
    }
    return authorList;
  }

  public Author getAuthorById(int author_id) {
    Author tempAuthor = new Author();
    dataBase = getHelper().getReadableDatabase();
    @SuppressLint("DefaultLocale") String sqlQuery = String
        .format("select * from %s where %s.%s = %d", Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR,
            Utils.TABLE_AUTHOR_ID, author_id);
    Cursor cursor = dataBase.rawQuery(sqlQuery, null);
    if (cursor.moveToNext()){
      int authorIdIndexColumn = cursor.getColumnIndex(Utils.TABLE_AUTHOR_ID);
      int authorNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_NAME);
      int authorYearIndexColumn = cursor.getColumnIndex(Utils.TABLE_AUTHOR_YEAR);
      tempAuthor.set_id(cursor.getInt(authorIdIndexColumn));
      tempAuthor.setName(cursor.getString(authorNameColumnIndex));
      tempAuthor.setYearOld(cursor.getInt(authorYearIndexColumn));
    }else {
      tempAuthor = null;
    }
    return tempAuthor;
  }
}
