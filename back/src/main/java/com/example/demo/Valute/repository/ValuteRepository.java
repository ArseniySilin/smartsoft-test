package com.example.demo.Valute.repository;

import com.example.demo.Valute.model.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValuteRepository extends JpaRepository<Valute, String> {
  @Modifying
  @Query(
    value = "TRUNCATE TABLE valute",
    nativeQuery = true
  )
  void truncateValuteTable();

  @Query("SELECT v FROM Valute v WHERE v.charCode IN :charCodes")
  List<Valute> findByValuteCharCodes(@Param("charCodes") List<String> charCodes);
}
