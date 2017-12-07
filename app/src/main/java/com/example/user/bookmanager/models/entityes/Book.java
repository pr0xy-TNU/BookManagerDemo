package com.example.user.bookmanager.models.entityes;

/**
 * Created by user on 07.12.17.
 */

public class Book {

  private int _id;
  private String name;
  private int companyId;
  private String year;
  private int authorId;

  private int date;

  public Book(String name) {
    this.name = name;
  }

  public void set_id(int _id) {
    this._id = _id;
  }

  public Book(String name, String year) {
    this.name = name;
    this.year = year;
  }

  public void setName(String name) {
    this.name = name;
  }

  private void setCompanyId(int companyId) {
    this.companyId = companyId;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  public int getCompanyId() {

    return companyId;
  }

  public String getYear() {
    return year;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setDate(int date) {
    this.date = date;
  }


  public int get_id() {

    return _id;
  }

  public String getName() {
    return name;
  }


  public int getDate() {
    return date;
  }



}
