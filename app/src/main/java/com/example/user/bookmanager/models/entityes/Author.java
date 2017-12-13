package com.example.user.bookmanager.models.entityes;

/**
 * Created by user on 07.12.17.
 */

public class Author {

  private int _id;
  private String name;
  private int yearOld;

  public void set_id(int _id) {
    this._id = _id;
  }


  public void setName(String name) {
    this.name = name;
  }

  public void setYearOld(int yearOld) {
    this.yearOld = yearOld;
  }

  public int get_id() {

    return _id;
  }

  public String getName() {
    return name;
  }

  public int getYearOld() {
    return yearOld;
  }

  public Author() {
  }

  public Author(String name, int yearOld) {
    this.name = name;
    this.yearOld = yearOld;
  }

  public Author(int id, String name, int yearOld) {
    this._id = id;
    this.name = name;
    this.yearOld = yearOld;

  }

  @Override
  public String toString() {
    return "Author{" +
        "_id=" + _id +
        "\t, name='" + name + '\'' +
        "\t, yearOld=" + yearOld +
        '}';
  }

  public String getAuthorInfo() {
    return this.toString();
  }
}
