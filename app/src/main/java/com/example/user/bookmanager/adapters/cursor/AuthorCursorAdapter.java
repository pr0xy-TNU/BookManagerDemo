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

public class AuthorCursorAdapter extends CursorAdapter {


  private Cursor mCursor;

  public AuthorCursorAdapter(Context context, Cursor c) {
    super(context, c);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    return LayoutInflater.from(context).inflate(R.layout.authors, parent, false);
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    TextView tvAuthorId = view.findViewById(R.id.tvAuthorId);
    TextView tvAuthorName = view.findViewById(R.id.tvAuthorName);
    TextView tvAuthorYearOld = view.findViewById(R.id.tvAuthorYearOld);

    int authorIdColumnIndex = cursor.getColumnIndex("_id");
    int authorNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_NAME);
    int authorYearOldColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_YEAR);

    tvAuthorId.setText(String.valueOf(cursor.getInt(authorIdColumnIndex)));
    tvAuthorName.setText(String.valueOf(cursor.getString(authorNameColumnIndex)));
    tvAuthorYearOld.setText(String.valueOf(cursor.getInt(authorYearOldColumnIndex)));


  }
}
