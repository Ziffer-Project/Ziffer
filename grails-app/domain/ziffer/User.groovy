package ziffer

class User {
    String name
    String username
    String password
    String email
    String phone
    int answerScore = 0
    int questionScore = 0
    boolean ban = true

    static constraints = {
        name( nullable: false, blank: false )
        username( unique: true, nullable: false, blank: false )
        password( nullable: false, blank: false )
        email( email: true, nullable: false, blank: false )
        phone( nullable: true, matches: "[0-9]+" )
    }
}
