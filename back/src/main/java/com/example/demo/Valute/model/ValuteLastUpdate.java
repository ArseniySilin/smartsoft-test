package com.example.demo.Valute.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "valute_last_update")
public class ValuteLastUpdate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "last_update")
  private LocalDate lastUpdateDate;

  ValuteLastUpdate() {}

  public ValuteLastUpdate(LocalDate lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  public ValuteLastUpdate(Long id, LocalDate lastUpdateDate) {
    this.id = id;
    this.lastUpdateDate = lastUpdateDate;
  }

  public Long getId() {
    return id;
  }

  public LocalDate getLastUpdateDate() {
    return lastUpdateDate;
  }
}
