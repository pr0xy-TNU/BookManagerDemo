package com.example.user.bookmanager.services;

import static com.example.user.bookmanager.utils.Utils.TABLE_COMPANY;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.user.bookmanager.models.entityes.Company;
import com.example.user.bookmanager.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 07.12.17.
 */

public class CompanyService extends BaseService {

  public CompanyService(Context context) {
    super(context);
  }

  public void addCompany(Company company) {
    SQLiteDatabase database = getHelper().getWritableDatabase();

    database.beginTransaction();
    try {
      ContentValues cv = new ContentValues();
      cv.put(Utils.TABLE_COMPANY, company.getName());
      database.insertOrThrow(Utils.TABLE_COMPANY, null, cv);
      database.setTransactionSuccessful();
    } catch (Exception e) {
      Log.d(Utils.OPERATION_STATUS_ERROR, "Error to add data to table ->" + TABLE_COMPANY);
    } finally {
      database.endTransaction();
      database.close();
    }
  }

  @SuppressLint("DefaultLocale")
  public List<Company> getAllCompanyes() {
    SQLiteDatabase database = getHelper().getWritableDatabase();
    database.beginTransaction();
    List<Company> companyList = new ArrayList<>();
    Company temp;
    String consoleInfo = null;
    try {
      Cursor cursor = database.query(Utils.TABLE_COMPANY, null, null, null, null, null, null);
      if (cursor.moveToFirst()) {
        int id = cursor.getColumnIndex(Utils.TABLE_COMPANY_ID);
        int name = cursor.getColumnIndex(Utils.TABLE_COMPANY_NAME);
        do {
          temp = new Company(cursor.getInt(id),
              cursor.getString(name));
          companyList.add(temp);
          consoleInfo = String.format("%d%s",
              cursor.getInt(id),
              cursor.getString(name));
              Log.d(Utils.LOG_TAG, consoleInfo);
        } while (cursor.moveToNext());
      } else {
        Log.d(Utils.LOG_TAG, Utils.OPERATION_STATUS_DATA_NOT_FOUND);
      }
    } catch (Exception e) {
      Log.d(Utils.OPERATION_STATUS_ERROR,
          "Error to get all data fro table ->" + Utils.TABLE_AUTHOR);
    }
    return companyList;
  }

}
