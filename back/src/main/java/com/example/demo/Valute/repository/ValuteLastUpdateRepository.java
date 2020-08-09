package com.example.demo.Valute.repository;

import com.example.demo.Valute.model.ValuteLastUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ValuteLastUpdateRepository extends JpaRepository<ValuteLastUpdate, Long> {
  @Modifying
  @Query(
    value = "TRUNCATE TABLE valute_last_update",
    nativeQuery = true
  )
  void truncateTable();

  @Query(value = "SELECT v FROM ValuteLastUpdate v WHERE v.lastUpdateDate = current_date")
  ValuteLastUpdate getLastUpdateByCurrentDate();
}
