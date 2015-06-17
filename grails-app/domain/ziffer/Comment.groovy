package ziffer

class Comment {

    String text
    Date dateCreated
    Date lastUpdated
    int negScore
    int posScore
  
    static constraints = {

        text blank: false, size: 1..2000

    }

    static belongsTo = [ answer : Answer, poster : User ]

    int score(){

        posScore-negScore

    }

}
