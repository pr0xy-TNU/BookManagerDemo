package com.example.user.bookmanager.utils;

/**
 * Created by user on 07.12.17.
 */

public interface Utils {


  /**
   * Logs problems
   *
   */
  String LOG_TAG = "LOG";
  String OPERATION_STATUS_SUCCESSFULLY = "SUCCSESSFULLY";
  String OPERATION_STATUS_ERROR = "ERROR";
  String OPERATION_STATUS_DATA_NOT_ADD = "Data" + OPERATION_STATUS_SUCCESSFULLY + " added!";
  String OPERATION_STATUS_DATA_NOT_FOUND = "Sorry, but data, which you search was not found or some problem with database";
  String DATABASE_QUERY_ERROR = "Database dont consists data or wrong query";
  String DATABASE_NO_SUCH_RECORD = "Sorry, no such record in data base";
  /**
   *
   *  DataBase info
   */

  String DB_NAME = "books_db";

  int DB_VERSION = 1;
  /**
   * Author table columns
   */
  String TABLE_AUTHOR = "authors";
  String TABLE_AUTHOR_ID = "author_id";
  String TABLE_AUTHOR_NAME = "author_name";

  String TABLE_AUTHOR_YEAR = "author_year";
  /**
   * Books table columns
   */
  String TABLE_BOOK = "books";
  String TABLE_BOOK_ID = "book_id";
  String TABLE_BOOK_NAME = "book_name";
  String TABLE_BOOK_YEAR = "book_year";
  String TABLE_BOOK_FK_AUTHOR = "author_id";

  String TABLE_BOOK_FK_COMPANY = "company_id";
  /**
   * Company table columns
   */
  String TABLE_COMPANY = "companyes";
  String TABLE_COMPANY_ID = "company_id";

  String TABLE_COMPANY_NAME = "company_name";
}
