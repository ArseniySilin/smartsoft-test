package com.example.demo.Valute.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "valute_conversions_history")
public class ValuteConvertionsHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "src_valute")
  private String srcValute;

  @Column(name = "dst_valute")
  private String dstValute;

  @Column(name = "src_summ")
  private float srcValue;

  @Column(name = "dst_summ")
  private float dstValue;

  @JsonFormat(pattern="dd.MM.yyyy", timezone = "GMT+3")
  @Temporal(TemporalType.DATE)
  @Column(name = "date")
  private Date date;

  public ValuteConvertionsHistory() {}

  public ValuteConvertionsHistory(String srcValute, String dstValute, float srcValue, float dstValue, Date date) {
    this.srcValute = srcValute;
    this.dstValute = dstValute;
    this.srcValue = srcValue;
    this.dstValue = dstValue;
    this.date = date;
  }

  public ValuteConvertionsHistory(Long id, String srcValute, String dstValute, float srcValue, float dstValue, Date date) {
    this.id = id;
    this.srcValute = srcValute;
    this.dstValute = dstValute;
    this.srcValue = srcValue;
    this.dstValue = dstValue;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public String getSrcValute() {
    return srcValute;
  }

  public String getDstValute() {
    return dstValute;
  }

  public float getSrcValue() {
    return srcValue;
  }

  public float getDstValue() {
    return dstValue;
  }

  public Date getDate() {
    return date;
  }
}
