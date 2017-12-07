package com.example.user.bookmanager.models.entityes;

/**
 * Created by user on 07.12.17.
 */

public class Company {

  private int _id;
  private String name;

  private int get_id() {
    return _id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Company(int id, String name) {

    this.name = name;
    this._id = id;
  }
}
