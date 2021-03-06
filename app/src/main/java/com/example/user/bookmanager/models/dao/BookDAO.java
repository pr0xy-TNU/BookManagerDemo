package com.example.user.bookmanager.models.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.user.bookmanager.models.entityes.Author;
import com.example.user.bookmanager.models.entityes.Book;
import com.example.user.bookmanager.models.entityes.BookAdvanced;
import com.example.user.bookmanager.models.entityes.Company;
import com.example.user.bookmanager.utils.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 07.12.17.
 */

public class BookDAO extends BaseDAO {


  public BookDAO(Context context) {
    super(context);
  }


  /**
   * Adding book to database
   */
  public void addBook(Book book) {

    SQLiteDatabase dataBase = getHelper().getWritableDatabase();
    dataBase.beginTransaction();
    try {
      ContentValues cv = new ContentValues();
      cv.put(Utils.TABLE_BOOK_NAME, book.getName());
      cv.put(Utils.TABLE_BOOK_YEAR, book.getYear());
      cv.put(Utils.TABLE_BOOK_FK_AUTHOR, book.getAuthorId());
      cv.put(Utils.TABLE_BOOK_FK_COMPANY, book.getCompanyId());

      dataBase.insertOrThrow(Utils.TABLE_BOOK, null, cv);
      Log.i(Utils.LOG_TAG, "addBook: Book was added");
      dataBase.setTransactionSuccessful();
    } catch (Exception e) {
      Log.i(Utils.OPERATION_STATUS_ERROR, "Error to add data to table ->" + Utils.TABLE_BOOK);
    } finally {
      dataBase.endTransaction();
      dataBase.close();
    }

  }

  public void addBooks(List<Book> bookList) {
    for (Book item : bookList
        ) {
      addBook(item);
    }
  }

  /**
   * Return all books from database
   */
  public ArrayList<Book> getAllBooks() {
    SQLiteDatabase db = getHelper().getReadableDatabase();
    ArrayList<Book> bookList = new ArrayList<>();
    try {
      Cursor cursor = db.query(Utils.TABLE_BOOK, null, null, null, null, null, null);
      if (cursor.moveToFirst()) {

        int idColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_ID);
        int nameColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_NAME);
        int authorIdColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_FK_AUTHOR);
        int companyIdColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_FK_COMPANY);
        int yearColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_YEAR);

        Book tempBook;
        do {
          int id = cursor.getInt(idColumnIndex);
          String name = cursor.getString(nameColumnIndex);
          int author_id = cursor.getInt(authorIdColumnIndex);
          int company_id = cursor.getInt(companyIdColumnIndex);
          int year = cursor.getInt(yearColumnIndex);
          tempBook = new Book(id, name, author_id, year, company_id);
          bookList.add(tempBook);
          /**
           * In this place must be magic with sql join, witch can give author and company by id
           */
        } while (cursor.moveToNext());
      } else {
        Log.i(Utils.LOG_TAG, "getAllBooks: " + Utils.DATABASE_QUERY_ERROR);
      }
    } catch (Exception e) {
      Log.i(Utils.LOG_TAG, "getAllBooks: cannot get data from " + Utils.TABLE_BOOK + "database");
    }
    return bookList;
  }

  /**
   * Return book with full author and company attributes
   */
  public ArrayList<BookAdvanced> getAllFullBooks() {
    BookAdvanced tempBook;
    Author tempAuthor;
    Company tempCompany;
    ArrayList<BookAdvanced> bookList = new ArrayList<>();
    SQLiteDatabase db = getHelper().getReadableDatabase();

    @SuppressLint("DefaultLocale") String query = String.format(
        "select " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s  "
            + "\nfrom %s \n"
            + "inner join %s on %s.%s = %s.%s\n"
            + "inner join %s on %s.%s = %s.%s\n",
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_ID,
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_NAME,
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_YEAR,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_NAME,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_ID,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_NAME,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_ID,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_YEAR,
        Utils.TABLE_BOOK,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_ID, Utils.TABLE_BOOK,
        Utils.TABLE_BOOK_FK_AUTHOR,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_ID, Utils.TABLE_BOOK,
        Utils.TABLE_BOOK_FK_COMPANY
    );

    Cursor cursor = db.rawQuery(query, null);
    if (cursor.moveToFirst()) {
      int bookIdColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_ID);
      int bookNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_NAME);
      int bookYearColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_YEAR);

      int companyIdColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_ID);
      int companyNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_NAME);

      int authorIdColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_ID);
      int authorNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_NAME);
      int authorYearColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_YEAR);

      do {

        int bookId = cursor.getInt(bookIdColumnIndex);
        String bookName = cursor.getString(bookNameColumnIndex);
        int bookYear = cursor.getInt(bookYearColumnIndex);

        int companyId = cursor.getInt(companyIdColumnIndex);
        String companyName = cursor.getString(companyNameColumnIndex);

        int authorId = cursor.getInt(authorIdColumnIndex);
        String authorName = cursor.getString(authorNameColumnIndex);
        int authorYearOld = cursor.getInt(authorYearColumnIndex);

        tempAuthor = new Author(authorId, authorName, authorYearOld);
        tempCompany = new Company(companyId, companyName);
        tempBook = new BookAdvanced(bookId, bookName, tempAuthor, tempCompany, bookYear);
        bookList.add(tempBook);

      } while (cursor.moveToNext());
    } else {
      Log.i(Utils.LOG_TAG, "getFullBook: There is no matches in book...");
    }
    return bookList;
  }

  public BookAdvanced getFullBookById(int book_id) {

    BookAdvanced tempBook = new BookAdvanced();
    Author tempAuthor;
    Company tempCompany;

    SQLiteDatabase db = getHelper().getReadableDatabase();
    @SuppressLint("DefaultLocale") String query = String.format(
        "select " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s "
            + "\nfrom %s \n"
            + "inner join %s on %s.%s = %s.%s\n"
            + "inner join %s on %s.%s = %s.%s\n"
            + "where %s.%s = %d",
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_ID, Utils.TABLE_BOOK, Utils.TABLE_BOOK_NAME,
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_YEAR,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_NAME,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_ID,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_NAME,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_ID,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_YEAR,
        Utils.TABLE_BOOK,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_ID, Utils.TABLE_BOOK,
        Utils.TABLE_BOOK_FK_AUTHOR,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_ID, Utils.TABLE_BOOK,
        Utils.TABLE_BOOK_FK_COMPANY,
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_ID, book_id
    );

    Cursor cursor = db.rawQuery(query, null);
    if (cursor.moveToFirst()) {
      int bookIdColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_ID);
      int bookNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_NAME);
      int bookYearColumnIndex = cursor.getColumnIndex(Utils.TABLE_BOOK_YEAR);

      int companyIdColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_ID);
      int companyNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_COMPANY_NAME);

      int authorIdColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_ID);
      int authorNameColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_NAME);
      int authorYearColumnIndex = cursor.getColumnIndex(Utils.TABLE_AUTHOR_YEAR);

      do {

        int bookId = cursor.getInt(bookIdColumnIndex);
        String bookName = cursor.getString(bookNameColumnIndex);
        int bookYear = cursor.getInt(bookYearColumnIndex);

        int companyId = cursor.getInt(companyIdColumnIndex);
        String companyName = cursor.getString(companyNameColumnIndex);

        int authorId = cursor.getInt(authorIdColumnIndex);
        String authorName = cursor.getString(authorNameColumnIndex);
        int authorYearOld = cursor.getInt(authorYearColumnIndex);

        tempAuthor = new Author(authorId, authorName, authorYearOld);
        tempCompany = new Company(companyId, companyName);
        //tempBook = new BookAdvanced(bookId, bookName, tempAuthor, tempCompany, bookYear);

        tempBook.set_id(bookId);
        tempBook.setBookName(bookName);
        tempBook.setAuthor(tempAuthor);
        tempBook.setCompany(tempCompany);
        tempBook.setBookYear(bookYear);

      } while (cursor.moveToNext());
    } else {
      Log.i(Utils.LOG_TAG, "getFullBook: There is no matches in book...");
      tempBook = null;
    }
    return tempBook;
  }

  public Cursor getBookCursorAdapter() {
    @SuppressLint("DefaultLocale") String query = String.format(
        "select " +
            "\n%s.%s as _id, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s, " +
            "\n%s.%s  "
            + "\nfrom %s \n"
            + "inner join %s on %s.%s = %s.%s\n"
            + "inner join %s on %s.%s = %s.%s\n",
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_ID,
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_NAME,
        Utils.TABLE_BOOK, Utils.TABLE_BOOK_YEAR,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_NAME,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_ID,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_NAME,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_ID,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_YEAR,
        Utils.TABLE_BOOK,
        Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_ID, Utils.TABLE_BOOK,
        Utils.TABLE_BOOK_FK_AUTHOR,
        Utils.TABLE_COMPANY, Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_ID, Utils.TABLE_BOOK,
        Utils.TABLE_BOOK_FK_COMPANY
    );

    return getHelper().getReadableDatabase().rawQuery(query, null);
  }


  public Cursor fetchingByBookName(String userRequest) {
    Cursor cursor = null;
    if (userRequest == null || userRequest.length() == 0) {
      cursor = getBookCursorAdapter();
    } else {
      String query = String.format(
          "select " +
              "\n%s.%s as _id, " +
              "\n%s.%s, " +
              "\n%s.%s, " +
              "\n%s.%s, " +
              "\n%s.%s  "
              + "\nfrom %s \n"
              + "inner join %s on %s.%s = %s.%s\n"
              + "inner join %s on %s.%s = %s.%s\n"
          ,
          Utils.TABLE_BOOK, Utils.TABLE_BOOK_ID,
          Utils.TABLE_BOOK, Utils.TABLE_BOOK_NAME,
          Utils.TABLE_BOOK, Utils.TABLE_BOOK_YEAR,
          Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_NAME,
          Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_NAME,
          Utils.TABLE_BOOK,
          Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR, Utils.TABLE_AUTHOR_ID, Utils.TABLE_BOOK,
          Utils.TABLE_BOOK_FK_AUTHOR,
          Utils.TABLE_COMPANY, Utils.TABLE_COMPANY, Utils.TABLE_COMPANY_ID, Utils.TABLE_BOOK,
          Utils.TABLE_BOOK_FK_COMPANY
      );
      query += "\nwhere " + Utils.TABLE_BOOK_NAME + " like '%" + userRequest + "%'";
      cursor = getHelper().getReadableDatabase().rawQuery(query, null);
    }
    if (cursor != null) {
      cursor.moveToFirst();
    }
    return cursor;
  }
}
