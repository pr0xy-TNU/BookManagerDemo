package com.example.user.bookmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.models.entityes.Company;
import java.util.ArrayList;

/**
 * Created by user on 15.12.17.
 */

public class CompanyAdapter extends BaseAdapter {

  private Context mContext;
  private ArrayList<Company> mCompanies;
  private LayoutInflater mInflater;

  public CompanyAdapter(Context context,
      ArrayList<Company> companies) {
    mContext = context;
    mCompanies = companies;
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return mCompanies.size();
  }

  @Override
  public Object getItem(int i) {
    return mCompanies.get(i);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    View tempView = view;
    if (tempView == null) {
      tempView = mInflater.inflate(R.layout.company, parent, false);
    }

    Company currentCompany = getCompany(position);
    ((TextView) tempView.findViewById(R.id.tvCompanyId)).setText(String.valueOf(currentCompany.get_id()));
    ((TextView) tempView.findViewById(R.id.tvCompanyName)).setText(String.valueOf(currentCompany.getName()));

    return tempView;
  }

  public Company getCompany(int position) {
    return (Company) getItem(position);
  }
}
