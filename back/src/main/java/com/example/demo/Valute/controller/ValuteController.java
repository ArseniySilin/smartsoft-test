package com.example.demo.Valute.controller;

import com.example.demo.Valute.model.Valute;
import com.example.demo.Valute.model.ValuteConvertionsHistory;
import com.example.demo.Valute.service.ValuteConvertionsHistoryService;
import com.example.demo.Valute.service.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

@RestController
public class ValuteController {
  @Autowired
  ValuteService valuteService;

  @Autowired
  ValuteConvertionsHistoryService valuteConvertionsHistoryService;

  @GetMapping(value = "/valute/all")
  public ResponseEntity<List<Valute>> getAll() {
    List<Valute> valuteAll = valuteService.getAll();
    return ResponseEntity.ok(valuteAll);
  }

  @GetMapping(value = "/valute/convert")
  public ResponseEntity<Float> convert(
    @RequestParam(value = "fromId") String fromId,
    @RequestParam(value = "toId") String toId,
    @RequestParam(value = "value") String value
  ) {
    Float converted = valuteService.convert(fromId, toId, value);
    return ResponseEntity.ok(converted);
  }

  @GetMapping(value = "/valute/history")
  public ResponseEntity<List<ValuteConvertionsHistory>> getHistory(
    @RequestParam(value = "srcValute") String srcValute,
    @RequestParam(value = "dstValute") String dstValute,
    @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date
  ) {
    List<ValuteConvertionsHistory> history = valuteConvertionsHistoryService.getHistory(srcValute, dstValute, date);

    return ResponseEntity.ok(history);
  }
}
