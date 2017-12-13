package com.example.user.bookmanager.models.entityes;

/**
 * Created by user on 12.12.17.
 */

public class BookAdvanced {

  private int _id;
  private String mBookName;
  private Author mAuthor;
  private Company mCompany;
  private int bookYear;


  public BookAdvanced(int _id, String bookName,
      Author author, Company company, int bookYear) {
    this._id = _id;
    mBookName = bookName;
    mAuthor = author;
    mCompany = company;
    this.bookYear = bookYear;
  }

  public BookAdvanced() {
  }

  public int get_id() {
    return _id;
  }

  public String getBookName() {
    return mBookName;
  }

  public Author getAuthor() {
    return mAuthor;
  }

  public Company getCompany() {
    return mCompany;
  }

  public void set_id(int _id) {
    this._id = _id;
  }

  public void setBookName(String bookName) {
    mBookName = bookName;
  }

  public void setAuthor(Author author) {
    mAuthor = author;
  }

  public void setCompany(Company company) {
    mCompany = company;
  }

  public void setBookYear(int bookYear) {
    this.bookYear = bookYear;
  }

  public int getBookYear() {
    return bookYear;
  }

  @Override
  public String toString() {
    return "BookAdvanced{" +
        "_id=" + _id +
        ", Book name='" + mBookName + '\'' +
        ",\n Author=" + mAuthor.getAuthorInfo() +
        ", \nCompany=" + mCompany +
        "\n, bookYear='" + bookYear + '\'' +
        '}';
  }

  public String getBookAndvencadInfo() {
    return this.toString();
  }
}
