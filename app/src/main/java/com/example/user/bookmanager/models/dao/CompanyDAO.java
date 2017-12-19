package com.example.user.bookmanager.models.dao;

import static com.example.user.bookmanager.utils.Utils.TABLE_COMPANY;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import com.example.user.bookmanager.models.entityes.Company;
import com.example.user.bookmanager.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 07.12.17.
 */

public class CompanyDAO extends BaseDAO {

  private SQLiteDatabase dataBase;

  public CompanyDAO(Context context) {
    super(context);
  }

  public void addCompany(Company company) {
    dataBase = getHelper().getWritableDatabase();
    dataBase.beginTransaction();
    try {
      ContentValues cv = new ContentValues();
      cv.put(Utils.TABLE_COMPANY_NAME, company.getName());
      dataBase.insertOrThrow(TABLE_COMPANY, null, cv);
      dataBase.setTransactionSuccessful();
    } catch (Exception e) {
      Log.i(Utils.LOG_TAG, "Error to add data to table ->" + TABLE_COMPANY);
    } finally {
      dataBase.endTransaction();
      dataBase.close();
    }
  }

  public void addCompany(List<Company> company) {
    for (Company item : company
        ) {
      addCompany(item);
    }
  }


  public ArrayList<Company> getAllCompanyes() {
    dataBase = getHelper().getWritableDatabase();
    ArrayList<Company> companyList = new ArrayList<>();
    Company temp;
    try {
      Cursor cursor = dataBase.query(Utils.TABLE_COMPANY, null, null, null, null, null, null);
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

  public Company getCompanyById(int company_id) {
    Company tempCompany = new Company();
    dataBase = getHelper().getReadableDatabase();
    @SuppressLint("DefaultLocale") String sqlQuery = String
        .format("select * from %s where %s.%s = %d", Utils.TABLE_COMPANY, Utils.TABLE_COMPANY,
            Utils.TABLE_COMPANY_ID, company_id);
    Cursor cursor = dataBase.rawQuery(sqlQuery, null);
    if (cursor.moveToNext()) {
      int companyIdIndexColumn = cursor.getColumnIndex(Utils.TABLE_COMPANY_ID);
      int companyNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_NAME);
      tempCompany.set_id(cursor.getInt(companyIdIndexColumn));
      tempCompany.setName(cursor.getString(companyNameColumnIndex));
    } else {
      tempCompany = null;
      Log.i(Utils.LOG_TAG, "getAuthorById: " + Utils.DATABASE_NO_SUCH_RECORD);
    }
    return tempCompany;
  }


  public Cursor getCompanyCursor() {
    String query = String
        .format("select %s as _id, %s from %s", Utils.TABLE_COMPANY_ID, Utils.TABLE_COMPANY_NAME,
            TABLE_COMPANY);
    return getHelper().getReadableDatabase().rawQuery(query, null);
  }

  @SuppressLint("Recycle")
  public Cursor fetchingByCompanyName(String userRequest) {
    Cursor tempCursor = null;

    if (userRequest == null || userRequest.length() == 0) {
      tempCursor = getCompanyCursor();
    } else {
      String[] requiredColumns = new String[]{
          Utils.TABLE_COMPANY_ID + " as _id",
          Utils.TABLE_COMPANY_NAME,
      };
      tempCursor = getHelper()
          .getReadableDatabase()
          .query(true,
              Utils.TABLE_COMPANY,
              requiredColumns,
              Utils.TABLE_COMPANY_NAME + " like '%" + userRequest + "%'",
              null,
              null,
              null,
              null,
              null
          );
      if (tempCursor != null) {
        tempCursor.moveToFirst();
      }
    }
    return tempCursor;
  }
}
