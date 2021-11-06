package exercise2.java02.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class NewsPaper extends Observable{
	private String name;
	private List<String> articles;
	
	public  NewsPaper(String name) {
	articles = new ArrayList<String>();
	this.name = name;
	}
	
	//add the article to the articles list and make an announcement.
	  void addArticle(String article) { 
		  super.setChanged();
		  articles.add(article);
		  super.notifyObservers(article);
	  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getArticles() {
		return articles;
	}

	public void setArticles(List<String> articles) {
		this.articles = articles;
	}
	  

}
