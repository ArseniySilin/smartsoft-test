package com.example.demo.Valute.service;

import com.example.demo.Valute.model.Valute;
import com.example.demo.Valute.model.ValuteLastUpdate;
import com.example.demo.Valute.repository.ValuteRepository;
import com.example.demo.Valute.repository.ValuteLastUpdateRepository;
import com.example.demo.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValuteService {
  @Autowired
  ValuteRepository valuteRepository;

  @Autowired
  ValuteLastUpdateRepository valuteLastUpdateRepository;

  @Autowired
  ValuteConvertionsHistoryService valuteConvertionsHistoryService;

  @Autowired
  Parser parser;

  @Transactional
  public void truncateValuteTable() {
    valuteRepository.truncateValuteTable();
  }

  public void updateValuteTable(List<Valute> valuteList) {
    valuteRepository.saveAll(valuteList);
  }

  public List<Valute> getAll() {
    return valuteRepository.findAll();
  }

  @Transactional
  public void saveValutes(List<Valute> valuteList) {
    this.truncateValuteTable();
    this.updateValuteTable(valuteList);
  }

  public boolean shouldUpdateValutes() {
    try {
      ValuteLastUpdate v = valuteLastUpdateRepository.getLastUpdateByCurrentDate();
      if (v == null) return true;
    } catch (EntityNotFoundException e) {
      return false;
    }

    return false;
  }

  public float convert(String fromId, String toId, String value) throws NumberFormatException {
    if (shouldUpdateValutes()) {
      parser.update();
    }

    Float valueToConvert = Float.parseFloat(value);
    List<Valute> valutes = valuteRepository.findByValuteCharCodes(Arrays.asList(fromId, toId));

    if (valutes.size() != 2) {
      return 0f;
    }

    Map<String, Valute> valutesMap = new HashMap<>();
    valutes.forEach(v -> valutesMap.put(v.getCharCode(), v));
    Valute fromValute = valutesMap.get(fromId);
    Valute toValute = valutesMap.get(toId);

    int fromNominal = fromValute.getNominal();
    float fromValue = fromValute.getValue();
    int toNominal = toValute.getNominal();
    float toValue = toValute.getValue();

    float from = fromValue / (float) fromNominal;
    float to =   toValue / (float) toNominal;
    float result = (from * valueToConvert) / to;

    valuteConvertionsHistoryService.addConvertion(fromValute, toValute, valueToConvert, result);

    return result;
  }
}
