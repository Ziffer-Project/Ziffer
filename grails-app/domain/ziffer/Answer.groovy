package ziffer

class Answer {

    String text
    Date dateCreated
    Date lastUpdated
    int posScore
    int negScore
    boolean accepted
    byte[] attachedFile
    
    static belongsTo = [ question : Question, answerer : User ]

    static hasMany = [ replies : Comment ]

    static constraints = {

        text blank: false
	replies nullable: true
	attachedFile maxSize: 10*1024*1024, nullable: true

    }

    int score(){

	posScore-negScore

    }

}
