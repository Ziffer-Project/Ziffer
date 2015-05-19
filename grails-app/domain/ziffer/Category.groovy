package ziffer

class Category {

    String name
    int id
    static hasMany = [questions: Question]

    static constraints = {
        questions( nullable: true )
    }
}
