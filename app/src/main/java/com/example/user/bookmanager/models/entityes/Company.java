package com.example.user.bookmanager.models.entityes;

/**
 * Created by user on 07.12.17.
 */

public class Company {

  private int _id;
  private String name;

  public int get_id() {
    return _id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Company() {
  }

  public Company(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Company{" +
        "_id=" + _id +
        ", name='" + name + '\'' +
        '}';
  }

  public Company(int id, String name) {

    this.name = name;
    this._id = id;
  }
  public String getCompanyInfo(){
    return this.toString();
  }

  public void set_id(int _id) {
    this._id = _id;
  }
}
