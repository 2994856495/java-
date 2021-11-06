package exercise2.java02.newscafe;

class Newscafe {
  public static void main(String[] args) {
    Reader jill = new Reader("Jill");
    Reader jack = new Reader("Jack");
    Newspaper times = new Newspaper("The Times");
    Newspaper guardian = new Newspaper("The Guardian");

    jill.subscribe(times);
    times.addArticle("Stormy weather!");
    times.announce();

    jill.subscribe(guardian);
    jack.subscribe(guardian);
    guardian.addArticle("Bad news!");
    guardian.announce();

    guardian.addArticle("Good news!");
    guardian.announce();

    times.addArticle("Sunny weather!");
    times.announce();
  

} }
