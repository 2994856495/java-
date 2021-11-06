package exercise2.java02.observer;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer {
	 private String name;
	  
	  public void setname(String name) {
		  this.name=name;
	  }
	  
	  public String getname() {
		  return name;
	  }
	  
	  Reader(String name) {
	    this.name = name;
	  }

	@Override
	public void update(Observable arg0, Object arg1) {
		NewsPaper paper=(NewsPaper) arg0;
		String aname=(String) arg1;
		System.out.println(paper.getName() + "新增一篇文章"+aname);
	}

}
