package com.example.demo.parser;

import com.example.demo.Valute.model.Valute;
import com.example.demo.Valute.service.ValuteLastUpdateService;
import com.example.demo.Valute.service.ValuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class Parser {
  @Autowired
  private ValuteService valuteService;

  @Autowired
  ValuteLastUpdateService valuteLastUpdateService;

  private final Valute rubValute = new Valute(
    "R00000",
    0,
    "RUB",
    1,
    "Российский рубль",
    1.0f
  );

  @Value("${cbr.api}")
  private String url;

  private List<Valute> getValutes() {
    List<Valute> valuteList = new ArrayList<>();
    valuteList.add(rubValute);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;

    try {
      documentBuilder = dbf.newDocumentBuilder();
      Document doc = documentBuilder.parse(new URL(url).openStream());
      NodeList nList = doc.getElementsByTagName("Valute");

      for (int i = 0; i < nList.getLength(); i++) {
        Node nNode = nList.item(i);
        Element eElement = (Element) nNode;

        String id = eElement.getAttribute("ID");
        String charCode = eElement.getElementsByTagName("CharCode").item(0).getTextContent();
        String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
        int numCode = 0;
        int nominal = 0;
        float value = 0f;

        try {
          numCode = Integer.parseInt(eElement.getElementsByTagName("NumCode").item(0).getTextContent());
          nominal = Integer.parseInt(eElement.getElementsByTagName("Nominal").item(0).getTextContent());
          value = Float.parseFloat(eElement.getElementsByTagName("Value").item(0).getTextContent()
            .replace(",", "."));
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }

        Valute valute = new Valute(
          id,
          numCode,
          charCode,
          nominal,
          name,
          value
        );
        valuteList.add(valute);
      }
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return valuteList;
  }

  public int update() {
    List<Valute> valutes = this.getValutes();

    if (valutes.size() == 0) {
       return -1;
    }

    valuteService.saveValutes(valutes);
    valuteLastUpdateService.update();

    return 0;
  }
}
