package exercise2.java02.newscafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Newspaper {
  private String name;
  private List<String> articles;
  private List<Reader> readers;

  Newspaper(String name) {
    articles = new ArrayList<String>();
    readers = new ArrayList<Reader>();
    this.name = name;
  }


  void addArticle(String article) {
    articles.add(article);
  }

  String getArticle(int issue) {
   return articles.get(issue);
  }


  int getIssue() {  return articles.size(); }



  void register(Reader reader) {
    readers.add(reader);
  }


  void unregister(Reader reader) {
    readers.remove(reader);
  }

  void announce() {

    Iterator<Reader> iterator = readers.iterator();
    while (iterator.hasNext()){
      Reader reader = iterator.next();
      System.out.println(reader.getName()+" read: "+this.name+": "+this.getArticle(this.getIssue()-1));
    }


  }
 }
