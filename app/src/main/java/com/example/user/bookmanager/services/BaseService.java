package com.example.user.bookmanager.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 07.12.17.
 */

class BaseService {

  private LibraryDBHelper mHelper;
  private SQLiteDatabase mDatabase;
  private Context mContext;


  public BaseService(Context context) {
    this.mContext = context;
    mHelper = LibraryDBHelper.getInstance(context);
  }

  public LibraryDBHelper getHelper() {
    return mHelper;
  }

}
