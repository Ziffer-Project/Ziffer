package ziffer

class User {
    String name
    String username
    String password
    String email
    String phone
    int answerScore = 0
    int questionScore = 0
    int zifferCoins = 5
    boolean ban = true
    static hasMany = [ questions : Question ]

    static constraints = {
        name( nullable: false, blank: false )
        username( unique: true, nullable: false, blank: false )
        password( nullable: false, blank: false )
        email( email: true, nullable: false, blank: false, unique: true )
        phone( nullable: true, matches: "[0-9]+" )
    }
}
