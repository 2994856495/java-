package exercise2.java02.observer;



public class Observe {

	public static void main(String[] args) {
		Reader jill = new Reader("Jill");
	    Reader jack = new Reader("Jack");
	    NewsPaper times = new NewsPaper("The Times");
	    NewsPaper guardian = new NewsPaper("The Guardian");
	    times.addObserver(jill);
	    times.addArticle("Stormy weather!");
	    

	}

}
