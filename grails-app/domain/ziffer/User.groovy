package ziffer

class User {
    String name
    String username
    String password
    String email
    String phone
    int answerScore
    int questionScore
    int zifferCoins
    boolean ban
    static hasMany = [ questions : Question, collaborations: Question ]
    //TODO mirar relacion entre candidatos con las preguntas!!
    static constraints = {
        name( nullable: false, blank: false )
        username( unique: true, nullable: false, blank: false )
        password( nullable: false, blank: false )
        email( email: true, nullable: false, blank: false, unique: true )
        phone( nullable: true, matches: "[0-9]+" )
        questions( nullable: true )
        collaborations( nullable: true )
    }
}
