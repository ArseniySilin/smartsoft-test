package com.example.demo.Valute.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "valute")
public class Valute {
  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "num_code")
  private int numCode;

  @Column(name = "char_code")
  private String charCode;

  @Column(name = "nominal")
  private int nominal;

  @Column(name = "name")
  private String name;

  @Column(name = "value")
  private float value;

  public Valute() {}

  public Valute(String id, int numCode, String charCode, int nominal, String name, float value) {
    this.id = id;
    this.numCode = numCode;
    this.charCode = charCode;
    this.nominal = nominal;
    this.name = name;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public int getNumCode() {
    return numCode;
  }

  public String getCharCode() {
    return charCode;
  }

  public int getNominal() {
    return nominal;
  }

  public String getName() {
    return name;
  }

  public float getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Valute{" +
      "id='" + id + '\'' +
      ", numCode=" + numCode +
      ", charCode='" + charCode + '\'' +
      ", nominal=" + nominal +
      ", name='" + name + '\'' +
      ", value=" + value +
      '}';
  }
}
