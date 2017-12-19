package com.example.user.bookmanager.views.activityes;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.adapters.cursor.AuthorCursorAdapter;
import com.example.user.bookmanager.adapters.cursor.BookCursorAdapter;
import com.example.user.bookmanager.adapters.cursor.CompanyCursorAdaptor;
import com.example.user.bookmanager.models.dao.AuthorDAO;
import com.example.user.bookmanager.models.dao.BookDAO;
import com.example.user.bookmanager.models.dao.CompanyDAO;
import com.example.user.bookmanager.models.entityes.Author;
import com.example.user.bookmanager.utils.Utils;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {


  private AuthorDAO mAuthorDAO;
  private CompanyDAO mCompanyDAO;
  private BookDAO mBookDAO;

  private Button mButtonCompany;
  private Button mButtonAuthor;
  private Button mButtonBook;

  private EditText mEtUserFilter;
  private ListView mListView;

  private FragmentManager mFragmentManager;
  private FragmentTransaction mFragmentTransaction;
  private BaseAdapter templateAdaptor;
  private ArrayAdapter<String> arrayAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mAuthorDAO = new AuthorDAO(this);
    mBookDAO = new BookDAO(this);
    mCompanyDAO = new CompanyDAO(this);

    mButtonCompany = findViewById(R.id.btnShowCompanyes);
    mButtonAuthor = findViewById(R.id.btnShowAuthors);
    mButtonBook = findViewById(R.id.btnShowBooks);
    mEtUserFilter = findViewById(R.id.tvUserFilter);
    mListView = findViewById(R.id.lvMainActivity);

    mButtonCompany.setOnClickListener(this);
    mButtonBook.setOnClickListener(this);
    mButtonAuthor.setOnClickListener(this);
    mFragmentManager = getFragmentManager();


  }

  public void log(String massage) {
    Log.i(Utils.LOG_TAG, massage);
  }

 /* public void initData() {
    Author tempAuthor;
    Book tempBook;
    Company tempCompany;
    mBookList = new ArrayList<>();
    mAuthorList = new ArrayList<>();
    mCompanyList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      int tempCompanyId, tempAuthorId;
      tempAuthorId = i;
      tempCompanyId = i;
      tempCompany = new Company(i, "Company" + i * mRandom.nextInt(100));
      tempAuthor = new Author(tempAuthorId, "Author", i * mRandom.nextInt(100));

      tempBook = new Book("Book" + mRandom.nextInt(10), mRandom.nextInt(50), mRandom.nextInt(2000),
          12 + mRandom.nextInt(28));
      mBookList.add(tempBook);
      mAuthorList.add(tempAuthor);
      mCompanyList.add(tempCompany);
    }
  }*/

  @RequiresApi(api = VERSION_CODES.N)
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnShowCompanyes:
        CompanyCursorAdaptor companyCursorAdaptor = new CompanyCursorAdaptor(this,
            mCompanyDAO.getCompanyCursor());

        mListView.setAdapter(companyCursorAdaptor);

        /**
         * life search for Companies
         */
        mEtUserFilter.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            companyCursorAdaptor.getFilter().filter(s.toString());
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
        });
        companyCursorAdaptor.setFilterQueryProvider(
            constraint -> mCompanyDAO.fetchingByCompanyName(constraint.toString()));

        break;

      case R.id.btnShowAuthors:
        mEtUserFilter.setText("");
        AuthorCursorAdapter authorCursorAdapter = new AuthorCursorAdapter(this,
            mAuthorDAO.getAuthorCursor());
        mListView.setAdapter(authorCursorAdapter);
        mEtUserFilter.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            authorCursorAdapter.getFilter().filter(s.toString());
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
        });
        authorCursorAdapter.setFilterQueryProvider(
            constraint -> mAuthorDAO.fetchingByAuthorName(constraint.toString()));

        break;

      case R.id.btnShowBooks:
        mEtUserFilter.setText("");
        BookCursorAdapter bookCursorAdapter = new BookCursorAdapter(this,
            mBookDAO.getBookCursorAdapter());
        mListView.setAdapter(bookCursorAdapter);
        mEtUserFilter.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
            bookCursorAdapter.getFilter().filter(s.toString());
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
        });
        bookCursorAdapter.setFilterQueryProvider(
            constraint -> mBookDAO.fetchingByBookName(constraint.toString()));

        break;

    }
  }
  public void show(ArrayList<?> list){

  }
}
