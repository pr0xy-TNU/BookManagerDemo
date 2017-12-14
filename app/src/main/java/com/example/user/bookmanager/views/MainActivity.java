package com.example.user.bookmanager.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.models.entityes.Author;
import com.example.user.bookmanager.models.entityes.Book;
import com.example.user.bookmanager.models.entityes.BookAdvanced;
import com.example.user.bookmanager.models.entityes.Company;

import com.example.user.bookmanager.models.dao.AuthorDAO;
import com.example.user.bookmanager.models.dao.BookDAO;
import com.example.user.bookmanager.models.dao.CompanyDAO;
import com.example.user.bookmanager.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private List<Book> mBookList;
  private List<Author> mAuthorList;
  private List<Company> mCompanyList;
  private Random mRandom = new Random();
  private AuthorDAO mAuthorService;
  private CompanyDAO mCompanyService;
  private BookDAO mBookService;
  private Button mButtonCompany;
  private Button mButtonAuthor;
  private Button mButtonBook;
  private Button mButtonFind;
  private EditText mEtFindFiled;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mAuthorService = new AuthorDAO(this);
    mBookService = new BookDAO(this);
    mCompanyService = new CompanyDAO(this);

    mButtonCompany = findViewById(R.id.btnShowCompanyes);
    mButtonAuthor = findViewById(R.id.btnShowAuthors);
    mButtonBook = findViewById(R.id.btnShowBooks);
    mEtFindFiled = findViewById(R.id.tvFindField);
    mButtonFind = findViewById(R.id.btnFind);

    mButtonCompany.setOnClickListener(this);
    mButtonBook.setOnClickListener(this);
    mButtonAuthor.setOnClickListener(this);
    mButtonFind.setOnClickListener(this);


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
        List<Company> tempCompanyList = mCompanyService.getAllCompanyes();
        for (Company item : tempCompanyList) {
          log(item.getCompanyInfo());
        }
        break;

      case R.id.btnShowAuthors:
        List<Author> tempAuthorList = mAuthorService.getAllAuthors();
        for (Author item : tempAuthorList) {
          log(item.getAuthorInfo());
        }
        break;

      case R.id.btnShowBooks:
        List<Book> tempBookListBookList = mBookService.getAllBooks();
        for (Book item : tempBookListBookList) {
          log(item.getBookInfo());
        }
        break;

      case R.id.btnFind:
        int bookId = Integer.parseInt(mEtFindFiled.getText().toString());
        BookAdvanced bookAdvanced = mBookService.getFullBookById(bookId);
        if (bookAdvanced == null) {
          log(Utils.DATABASE_NO_SUCH_RECORD);
        } else {
          log(bookAdvanced.getBookAndvencadInfo());
        }
        break;
    }


  }

}
