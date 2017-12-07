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

  private String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  private Company(String name) {

    this.name = name;
  }
}
