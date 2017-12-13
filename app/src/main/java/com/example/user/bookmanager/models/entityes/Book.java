package com.example.user.bookmanager.models.entityes;

/**
 * Created by user on 07.12.17.
 */

public class Book {

  private int _id;
  private String name;
  private int authorId;
  private int year;
  private int companyId;


  public Book(String name) {
    this.name = name;
  }

  public void set_id(int _id) {
    this._id = _id;
  }

  public Book(String name, int year) {
    this.name = name;
    this.year = year;
  }

  public Book(int _id, String name, int authorId, int year, int companyId) {
    this._id = _id;
    this.name = name;
    this.authorId = authorId;
    this.year = year;
    this.companyId = companyId;

  }
  public Book(String name, int authorId, int year, int companyId) {
    this.name = name;
    this.authorId = authorId;
    this.year = year;
    this.companyId = companyId;

  }

  public void setName(String name) {
    this.name = name;
  }

  private void setCompanyId(int companyId) {
    this.companyId = companyId;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  public int getCompanyId() {

    return companyId;
  }

  public long getYear() {
    return year;
  }

  public int getAuthorId() {
    return authorId;
  }




  public int get_id() {

    return _id;
  }

  public String getName() {
    return name;
  }



  @Override
  public String toString() {
    return "Book{" +
        "_id=" + _id +
        "\t, name='" + name + '\'' +
        "\t, authorId=" + authorId +
        "\t, year=" + year +
        "\t, companyId=" + companyId +
        '}';
  }
  public String getBookInfo(){
    return this.toString();
  }
}
