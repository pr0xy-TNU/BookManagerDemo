package com.example.user.bookmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.models.entityes.Author;
import java.util.ArrayList;

/**
 * Created by user on 15.12.17.
 */

public class AuthorAdapter extends BaseAdapter {

  private Context mContext;
  private ArrayList<Author> mAuthorArrayList;
  private LayoutInflater mInflater;

  public AuthorAdapter(Context context,
      ArrayList<Author> authorArrayList) {
    mContext = context;
    mAuthorArrayList = authorArrayList;
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return mAuthorArrayList.size();
  }

  @Override
  public Object getItem(int i) {
    return mAuthorArrayList.get(i);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    View tempView = view;
    if (tempView == null) {
      tempView = mInflater.inflate(R.layout.authors, parent, false);
    }
    Author currentAuthor = getAuthor(position);
    ((TextView) tempView.findViewById(R.id.tvAuthorId))
        .setText(String.valueOf(currentAuthor.get_id()));
    ((TextView) tempView.findViewById(R.id.tvAuthorName))
        .setText(String.valueOf(currentAuthor.getName()));
    ((TextView) tempView.findViewById(R.id.tvAuthorYearOld))
        .setText(String.valueOf(currentAuthor.getYearOld()));

    return tempView;
  }

  public Author getAuthor(int position) {
    return (Author) getItem(position);
  }
}
