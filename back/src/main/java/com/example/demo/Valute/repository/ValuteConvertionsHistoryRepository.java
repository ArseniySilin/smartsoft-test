package com.example.demo.Valute.repository;

import com.example.demo.Valute.model.ValuteConvertionsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ValuteConvertionsHistoryRepository extends JpaRepository<ValuteConvertionsHistory, Long> {
  @Query(value = "SELECT h FROM ValuteConvertionsHistory h WHERE h.srcValute = :srcValute AND h.dstValute = :dstValute AND h.date = :date")
  List<ValuteConvertionsHistory> getConvertionsHistory(
    @Param("srcValute") String srcValute,
    @Param("dstValute") String dstValute,
    @Param("date") Date date
  );
}
