package com.example.user.bookmanager.adapters.cursor;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.models.dao.CompanyDAO;
import com.example.user.bookmanager.models.entityes.Company;
import com.example.user.bookmanager.utils.Utils;

/**
 * Created by user on 19.12.17.
 */

public class CompanyCursorAdaptor extends CursorAdapter {
  private Cursor mCursor;
  private CompanyDAO mCompanyDAO;

  public CompanyCursorAdaptor(Context context, Cursor c) {
    super(context, c);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    return LayoutInflater.from(context).inflate(R.layout.company, parent, false);
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    TextView tvCompanyId = view.findViewById(R.id.tvCompanyId);
    TextView tvCompanyName = view.findViewById(R.id.tvCompanyName);

    int companyIdColumnIndex = cursor.getColumnIndex("_id");
    int companyNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_NAME);

    tvCompanyId.setText(String.valueOf(cursor.getInt(companyIdColumnIndex)));
    tvCompanyName.setText(String.valueOf(cursor.getString(companyNameColumnIndex)));
  }

}
