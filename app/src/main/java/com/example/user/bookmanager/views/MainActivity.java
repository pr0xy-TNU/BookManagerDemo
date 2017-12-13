package com.example.user.bookmanager.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.user.bookmanager.R;
import com.example.user.bookmanager.models.entityes.Author;
import com.example.user.bookmanager.models.entityes.Book;
import com.example.user.bookmanager.models.entityes.BookAdvanced;
import com.example.user.bookmanager.models.entityes.Company;
import com.example.user.bookmanager.services.AuthorService;
import com.example.user.bookmanager.services.BookService;
import com.example.user.bookmanager.services.CompanyService;
import com.example.user.bookmanager.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private List<Book> mBookList;
  private List<Author> mAuthorList;
  private List<Company> mCompanyList;
  private Random mRandom = new Random();
  private AuthorService mAuthorService;
  private CompanyService mCompanyService;
  private BookService mBookService;
  private Button mButtonCompany;
  private Button mButtonAuthor;
  private Button mButtonBook;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mAuthorService = new AuthorService(this);
    mBookService = new BookService(this);
    mCompanyService = new CompanyService(this);




    mButtonCompany = findViewById(R.id.btnShowCompanyes);
    mButtonAuthor = findViewById(R.id.btnShowAuthors);
    mButtonBook = findViewById(R.id.btnShowBooks);


    mButtonCompany.setOnClickListener(this);
    mButtonBook.setOnClickListener(this);
    mButtonAuthor.setOnClickListener(this);


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
          12 + mRandom.nextInt(28) );
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
        BookAdvanced bookAdvanced = mBookService.getFullBook(tempBookListBookList.get(2));
        log(bookAdvanced.getBookAndvencadInfo());
        break;
    }
  }

}
