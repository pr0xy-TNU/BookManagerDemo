package com.example.user.bookmanager.models;

import com.example.user.bookmanager.models.dao.AuthorDAO;
import com.example.user.bookmanager.models.dao.BookDAO;
import com.example.user.bookmanager.models.dao.CompanyDAO;
import com.example.user.bookmanager.models.entityes.Author;
import com.example.user.bookmanager.models.entityes.Book;
import com.example.user.bookmanager.models.entityes.BookAdvanced;
import com.example.user.bookmanager.models.entityes.Company;
import com.example.user.bookmanager.mvp.MVP;
import java.util.List;

/**
 * Created by user on 14.12.17.
 */

public class BaseModel implements Operations {

  private AuthorDAO mAuthorDAO;
  private BookDAO mBookDAO;
  private CompanyDAO mCompanyDAO;
  private MVP.RequiredPresenterOps mPresenter;



  private BaseModel() {

  }

  @Override
  public List<Author> getAllAuthor() {

    return null;
  }

  @Override
  public List<Book> getAllBooks() {
    return null;
  }

  @Override
  public List<Company> getAllCompanyes() {
    return null;
  }

  @Override
  public BookAdvanced getFullBookById(int book_id) {
    return null;
  }

  @Override
  public Author getBookByAuthor(int author_id) {
    return null;
  }

  @Override
  public Company getBookByCompany(int company_id) {
    return null;
  }
}
