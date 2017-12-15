package com.example.user.bookmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.models.entityes.BookAdvanced;
import java.util.ArrayList;

/**
 * Created by user on 15.12.17.
 */

public class BookAdapter extends BaseAdapter {

  private Context mContext;
  private ArrayList<BookAdvanced> mBooks;
  private LayoutInflater mInflater;

  public BookAdapter(Context context,
      ArrayList<BookAdvanced> books) {
    mContext = context;
    mBooks = books;
    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return mBooks.size();
  }

  @Override
  public Object getItem(int i) {
    return mBooks.get(i);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View view, ViewGroup viewGroup) {
    View tempView = view;
    if (tempView == null) {
      tempView = mInflater.inflate(R.layout.books, viewGroup, false);

    }
    BookAdvanced bookAdvanced = getBook(position);

    ((TextView) tempView.findViewById(R.id.tvBookdId)).
        setText(String.valueOf(bookAdvanced.get_id()));
    ((TextView) tempView.findViewById(R.id.tvBookName))
        .setText(String.valueOf(bookAdvanced.getBookName()));
    ((TextView) tempView.findViewById(R.id.tvBookAuthor))
        .setText(String.valueOf(bookAdvanced.getAuthor().getAuthorInfo()));
    ((TextView) tempView.findViewById(R.id.tvBookCompany))
        .setText(String.valueOf(bookAdvanced.getCompany().getCompanyInfo()));
    ((TextView) tempView.findViewById(R.id.tvBookYear))
        .setText(String.valueOf(bookAdvanced.getBookYear()));

    return tempView;
  }

  BookAdvanced getBook(int position) {
    return (BookAdvanced) getItem(position);
  }
}
