package com.example.user.bookmanager.models.entityes;

/**
 * Created by user on 07.12.17.
 */

public class Book {

  private int _id;
  private String name;
  private Author author;
  private int date;

  private Book(String name) {
    this.name = name;
  }

  private void set_id(int _id) {
    this._id = _id;
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setAuthor(Author author) {
    this.author = author;
  }

  private void setDate(int date) {
    this.date = date;
  }

  private void setCompany(Company company) {
    mCompany = company;
  }

  private int get_id() {

    return _id;
  }

  private String getName() {
    return name;
  }

  private Author getAuthor() {
    return author;
  }

  private int getDate() {
    return date;
  }

  private Company getCompany() {
    return mCompany;
  }

  private Book(String name, Author author,
      Company company) {

    this.name = name;
    this.author = author;
    mCompany = company;
  }

  private Company mCompany;

}
