package com.example.user.bookmanager.models;

import com.example.user.bookmanager.models.entityes.Author;
import com.example.user.bookmanager.models.entityes.Book;
import com.example.user.bookmanager.models.entityes.BookAdvanced;
import com.example.user.bookmanager.models.entityes.Company;
import java.util.List;

/**
 * Created by user on 14.12.17.
 */

public interface Operations {

  List<Author> getAllAuthor();

  List<Book> getAllBooks();

  List<Company> getAllCompanyes();

  BookAdvanced getFullBookById(int book_id);

  Author getBookByAuthor(int author_id);

  Company getBookByCompany(int company_id);

}
