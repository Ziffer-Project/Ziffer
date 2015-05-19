package ziffer

class Question {

    String title
    String description
    Date dateCreated
    Date lastUpdated
    Date dueDate
    int negScore
    int posScore
    byte[] attachedFile
    //¿keywords?

    static belongsTo = [ asker : User, category : Category ]
    
    static hasOne = [ answer : Answer ]

    static hasMany = [ offers : Offer ]

    static constraints = {

        title blank: false, size: 3..100
        description blank: false, size: 10..10000
        dueDate validator: { return it.after(new Date()) }
	answer nullable: true
	offers nullable: true
	//¿10 Mb estará bien?
	attachedFile maxSize: 10*1024*1024, nullable: true

    }

    static mapping = {

	negScore defaultValue: 0
	posScore defaultValue: 0

    }

    int score(){

	posScore-negScore

    }
}
