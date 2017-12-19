package com.example.user.bookmanager.adapters.cursor;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.utils.Utils;

/**
 * Created by user on 19.12.17.
 */

public class BookCursorAdapter extends CursorAdapter {

  public BookCursorAdapter(Context context, Cursor c) {
    super(context, c);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    return LayoutInflater.from(context).inflate(R.layout.books, parent, false);
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    TextView tvBookId = view.findViewById(R.id.tvBookdId);
    TextView tvBookName = view.findViewById(R.id.tvBookName);
    TextView tvBookAuthor = view.findViewById(R.id.tvBookAuthor);
    TextView tvBookCompany = view.findViewById(R.id.tvBookCompany);
    TextView tvBookYear = view.findViewById(R.id.tvBookYear);

    int bookIdColumnIndex = cursor.getColumnIndex("_id");
    int bookNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_NAME);
    int bookYearColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_YEAR);
    int companyNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_NAME);
    int authorNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_NAME);

    int bookId = cursor.getInt(bookIdColumnIndex);
    String bookName = cursor.getString(bookNameColumnIndex);
    int bookYear = cursor.getInt(bookYearColumnIndex);

    String companyName = cursor.getString(companyNameColumnIndex);
    String authorName = cursor.getString(authorNameColumnIndex);

    tvBookId.setText(clear(bookId));
    tvBookName.setText(clear(bookName));
    tvBookCompany.setText(clear(companyName));
    tvBookAuthor.setText(clear(authorName));
    tvBookYear.setText(clear(bookYear));
  }

  public String clear(Object o){
    return String.valueOf(o);
  }
}
