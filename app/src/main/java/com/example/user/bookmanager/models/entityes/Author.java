package com.example.user.bookmanager.models.entityes;

/**
 * Created by user on 07.12.17.
 */

public class Author {
  private int _id;
  private String name;
  private int yearOld;

  private void set_id(int _id) {
    this._id = _id;
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setYearOld(int yearOld) {
    this.yearOld = yearOld;
  }

  private int get_id() {

    return _id;
  }

  private String getName() {
    return name;
  }

  private int getYearOld() {
    return yearOld;
  }

  private Author(String name, int yearOld) {
    this.name = name;
    this.yearOld = yearOld;

  }
}
