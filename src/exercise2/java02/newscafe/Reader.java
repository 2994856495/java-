package exercise2.java02.newscafe;

class Reader {
  private String name;
  
  Reader(String name) {
    this.name = name;
  }


  String readArticle(Newspaper newspaper) {

      return newspaper.getArticle(newspaper.getIssue());
  }

    public String getName() {
        return name;
    }

    //subscribe the newspaper
  void subscribe(Newspaper newspaper) {
      newspaper.register(this);
} }
