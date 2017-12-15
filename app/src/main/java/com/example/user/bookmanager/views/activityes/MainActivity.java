package com.example.user.bookmanager.views.activityes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.adapters.AuthorAdapter;
import com.example.user.bookmanager.adapters.BookAdapter;
import com.example.user.bookmanager.adapters.CompanyAdapter;
import com.example.user.bookmanager.models.dao.AuthorDAO;
import com.example.user.bookmanager.models.dao.BookDAO;
import com.example.user.bookmanager.models.dao.CompanyDAO;
import com.example.user.bookmanager.models.entityes.Author;
import com.example.user.bookmanager.models.entityes.Book;
import com.example.user.bookmanager.models.entityes.BookAdvanced;
import com.example.user.bookmanager.models.entityes.Company;
import com.example.user.bookmanager.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private List<Book> mBookList;
  private List<Author> mAuthorList;
  private List<Company> mCompanyList;

  private Random mRandom = new Random();

  private AuthorDAO mAuthorDAO;
  private CompanyDAO mCompanyDAO;
  private BookDAO mBookDAO;

  private Button mButtonCompany;
  private Button mButtonAuthor;
  private Button mButtonBook;
  private Button mButtonFind;

  private EditText mEtFindFiled;


  private ListView mListView;

  private FragmentManager mFragmentManager;

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
    mEtFindFiled = findViewById(R.id.tvFindField);
    mButtonFind = findViewById(R.id.btnFind);
    mListView = findViewById(R.id.lvMainActivity);

    mButtonCompany.setOnClickListener(this);
    mButtonBook.setOnClickListener(this);
    mButtonAuthor.setOnClickListener(this);
    mButtonFind.setOnClickListener(this);
    mFragmentManager = getFragmentManager();


  }

  public void log(String massage) {
    Log.i(Utils.LOG_TAG, massage);
  }

  public void initData() {
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
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnShowCompanyes:
        mListView.setVisibility(View.VISIBLE);
        ArrayList<Company> tempCompanyList = mCompanyDAO.getAllCompanyes();
        CompanyAdapter companyAdapter = new CompanyAdapter(this, tempCompanyList);
        mListView.setAdapter(companyAdapter);
        break;

      case R.id.btnShowAuthors:
        mListView.setVisibility(View.VISIBLE);

        ArrayList<Author> tempAuthorList = mAuthorDAO.getAllAuthors();
        AuthorAdapter authorAdapter = new AuthorAdapter(this, tempAuthorList);
        mListView.setAdapter(authorAdapter);
        break;

      case R.id.btnShowBooks:
        mListView.setVisibility(View.VISIBLE);
        ArrayList<BookAdvanced> tempBookListBookList = mBookDAO.getAllFullBooks();
        for (BookAdvanced item : tempBookListBookList) {
          log(item.getBookAndvencadInfo());
        }
        BookAdapter adapter = new BookAdapter(this, mBookDAO.getAllFullBooks());
        mListView.setAdapter(adapter);

        break;

      case R.id.btnFind:
        if (TextUtils.isEmpty(mEtFindFiled.getText())) {
          Toast.makeText(getApplicationContext(), "Цифру вводить будем???", Toast.LENGTH_SHORT)
              .show();
        } else {
          int bookId = Integer.parseInt(mEtFindFiled.getText().toString());
          mEtFindFiled.setText("");
          BookAdvanced bookAdvanced = mBookDAO.getFullBookById(bookId);
          if (bookAdvanced == null) {
            log(Utils.DATABASE_NO_SUCH_RECORD);
          } else {
            log(bookAdvanced.getBookAndvencadInfo());

          }
          Fragment response = mFragmentManager.findFragmentById(R.id.fBookResponse);
          mListView.setVisibility(View.INVISIBLE);
          ((TextView) response.getView().findViewById(R.id.tvResponseBook))
              .setText(bookAdvanced != null ? bookAdvanced.getBookAndvencadInfo() : null);
        }

        break;
    }
  }
}
