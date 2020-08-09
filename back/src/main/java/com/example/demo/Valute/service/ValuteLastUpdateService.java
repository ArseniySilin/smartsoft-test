package com.example.demo.Valute.service;

import com.example.demo.Valute.model.ValuteLastUpdate;
import com.example.demo.Valute.repository.ValuteLastUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ValuteLastUpdateService {
  @Autowired
  ValuteLastUpdateRepository valuteLastUpdateRepository;

  @Transactional
  public void update() {
    valuteLastUpdateRepository.truncateTable();
    valuteLastUpdateRepository.save(new ValuteLastUpdate(LocalDate.now()));
  }
}
