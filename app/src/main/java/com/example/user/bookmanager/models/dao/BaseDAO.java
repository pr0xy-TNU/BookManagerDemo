package com.example.user.bookmanager.models.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 07.12.17.
 */

class BaseDAO {

  private LibraryDBHelper mHelper;
  private SQLiteDatabase mDatabase;
  private Context mContext;


  public BaseDAO(Context context) {
    this.mContext = context;
    mHelper = LibraryDBHelper.getInstance(context);
  }

  public LibraryDBHelper getHelper() {
    return mHelper;
  }

}
