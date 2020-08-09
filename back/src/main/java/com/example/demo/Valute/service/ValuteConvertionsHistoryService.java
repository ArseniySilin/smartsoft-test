package com.example.demo.Valute.service;

import com.example.demo.Valute.model.Valute;
import com.example.demo.Valute.model.ValuteConvertionsHistory;
import com.example.demo.Valute.repository.ValuteConvertionsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ValuteConvertionsHistoryService {
  @Autowired
  ValuteConvertionsHistoryRepository valuteConvertionsHistoryRepository;

  public void addConvertion(Valute src, Valute dst, float srcSum, float dstSum)
    throws NumberFormatException {

    ValuteConvertionsHistory valuteConvertionsHistory = new ValuteConvertionsHistory(
      src.getCharCode(),
      dst.getCharCode(),
      srcSum,
      dstSum,
      new Date()
    );
    valuteConvertionsHistoryRepository.save(valuteConvertionsHistory);
  }

  public List<ValuteConvertionsHistory> getHistory(String srcValute, String dstValute, Date date) {
    List<ValuteConvertionsHistory> historyList = valuteConvertionsHistoryRepository.getConvertionsHistory(srcValute, dstValute, date);

    return historyList;
  }
}
