package com.demo.application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

class Language {
  private String lang;
  private Double confidence;

  public String getLang() {
    return lang;
  }
  public void setLang(String lang) {
    this.lang = lang;
  }
  public Double getConfidence() {
    return confidence;
  }
  public void setConfidence(Double confidence) {
    this.confidence = confidence;
  }
}

class Sentiment {
  private String polarity;
  private Double polarityConfidence;

  public String getPolarity() {
    return polarity;
  }
  public void setPolarity(String polarity) {
    this.polarity = polarity;
  }

  public Double getPolarityConfidence() {
    return polarityConfidence;
  }
  public void setPolarityConfidence(Double confidence) {
    this.polarityConfidence = confidence;
  }
}

public class SentimentAPIDemo {
  private static final String APPLICATION_ID = "3cd51ea0";
  private static final String APPLICATION_KEY = "c571ff5006dc6e3adc9bcf84d8972aaa";

  public static void main(String[] args) {
    String text = "John is a very bad football player!";
    Language lang = getLanguage(text);
    System.out.printf("Language: %s (%f)\n",
        lang.getLang(), lang.getConfidence());
    Sentiment sent = getSentiment(text);
    String texttoSummarize="Ms. Mulrooney’s first days at the treatment center were a gut punch. \"This morning I carried a baby to the tent morgue,\" "
    		+ "she wrote in a Facebook message. The infant had appeared to be doing well. "
    		+ "\"It was a shock and a testament to how rapidly situations can change. "
    		+ "She cared for a 13-year-old girl whose intravenous site would not stop bleeding. "
    		+ "I cleaned her up, and put her in a pair of still-tagged jeans, \"Ms. Mulrooney wrote. \"She half smiled "
    		+ "and took some medicines I asked her to. I won't forget her smile. Nor her soft moans as her body was fading away.\""
    		+ " The nurse added, “I’m sorry for being graphic.\"";
    getSummary(texttoSummarize);
    //System.out.printf("Sentiment: %s (%f)\n", sent.getPolarity(), sent.getPolarityConfidence());
  }

  public static Language getLanguage(String text) {
    final Map<String, String> parameters;
    parameters = new HashMap<String, String>();
    parameters.put("text", text);
    Document doc = callAPI("language", parameters);
    Language language = new Language();
    NodeList nodeList = doc.getElementsByTagName("lang");
    Node langNode = nodeList.item(0);
    nodeList = doc.getElementsByTagName("confidence");
    Node confNode = nodeList.item(0);
    language.setLang(langNode.getTextContent());
    language.setConfidence(Double.parseDouble(confNode.getTextContent()));

    return language;
  }
  
  public static void getSummary(String text) {
	    final Map<String, String> parameters;
	    parameters = new HashMap<String, String>();
	    parameters.put("text", text);
	    parameters.put("title", "TestTitle");
	    Document doc = callAPI("summarize", parameters);
	    System.out.println(doc.toString());
	    NodeList nodeList = doc.getElementsByTagName("text");
	    System.out.println(nodeList.getLength());
	    Node polarityNode = nodeList.item(0);
	    nodeList=doc.getElementsByTagName("sentences");
	    System.out.println(polarityNode.toString());
	  }

  public static Sentiment getSentiment(String text) {
    final Map<String, String> parameters;
    parameters = new HashMap<String, String>();
    parameters.put("text", text);
    Document doc = callAPI("sentiment", parameters);
    Sentiment sentiment = new Sentiment();
    NodeList nodeList = doc.getElementsByTagName("polarity");
    Node polarityNode = nodeList.item(0);
    nodeList = doc.getElementsByTagName("polarity_confidence");
    Node confNode = nodeList.item(0);
    sentiment.setPolarity(polarityNode.getTextContent());
    sentiment.setPolarityConfidence(Double.parseDouble(confNode.getTextContent()));

    return sentiment;
  }

  public static Document callAPI(String endpoint, Map<String, String> parameters) {
    URL url;
    HttpURLConnection connection = null;

    try {
      String queryString = "";
      StringBuilder sb = new StringBuilder();
      for (Map.Entry<String, String> e: parameters.entrySet()) {
        if (sb.length() > 0) { sb.append('&'); }
        sb.append(URLEncoder.encode(e.getKey(), "UTF-8")).append('=')
          .append(URLEncoder.encode(e.getValue(), "UTF-8"));
      }
      queryString = sb.toString();
      url = new URL("https://api.aylien.com/api/v1/" + endpoint);
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty(
          "Content-Type", "application/x-www-form-urlencoded");
      connection.setRequestProperty(
          "Content-Length", Integer.toString(queryString.getBytes().length));
      connection.setRequestProperty("Accept", "text/xml");
      connection.setRequestProperty(
          "X-AYLIEN-TextAPI-Application-ID", APPLICATION_ID);
      connection.setRequestProperty(
          "X-AYLIEN-TextAPI-Application-Key", APPLICATION_KEY);
      connection.setDoInput(true);
      connection.setDoOutput(true);

      DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
      dos.writeBytes(queryString);
      dos.flush();
      dos.close();

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      InputSource xis = new InputSource(connection.getInputStream());

      return builder.parse(xis);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
    }
  }
}