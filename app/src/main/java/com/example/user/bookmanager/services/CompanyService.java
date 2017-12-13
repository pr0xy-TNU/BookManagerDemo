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
      cv.put(Utils.TABLE_COMPANY_NAME, company.getName());
      database.insertOrThrow(TABLE_COMPANY, null, cv);
      database.setTransactionSuccessful();
    } catch (Exception e) {
      Log.i(Utils.LOG_TAG, "Error to add data to table ->" + TABLE_COMPANY);
    } finally {
      database.endTransaction();
      database.close();
    }
  }

  public void addCompany(List<Company> company) {
    for (Company item : company
    ) {
      addCompany(item);
    }
  }


  public List<Company> getAllCompanyes() {
    SQLiteDatabase database = getHelper().getWritableDatabase();
    List<Company> companyList = new ArrayList<>();
    Company temp;
    try {
      Cursor cursor = database.query(Utils.TABLE_COMPANY, null, null, null, null, null, null);
      if (cursor.moveToFirst()) {
        int idColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_ID);
        int nameColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_NAME);
        do {
          int id = cursor.getInt(idColumnIndex);
          String name = cursor.getString(nameColumnIndex);

          temp = new Company(id,
              name);
          companyList.add(temp);
        } while (cursor.moveToNext());
      } else {
        Log.i(Utils.LOG_TAG, Utils.DATABASE_QUERY_ERROR);
      }
    } catch (Exception e) {
      Log.i(Utils.LOG_TAG,
          "Error to get all data fro table ->" + Utils.TABLE_COMPANY);
    }
    return companyList;
  }

}
