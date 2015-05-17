package ziffer

class Question {
    String title
    String description
    Date createdDate
    Date dueDate
    static belongsTo = [ user : User, proffesor: User, category : Category ]
    //TODO mirar relacion entre candidatos con las preguntas!!
    static constraints = {
        title( nullable: false, blank: false )
        description( nullable: false, blank: false )
        category( nullable: false, blank: false )
        dueDate( validator: { return it.after( Date.newInstance() ) }, nullable: true )
        user( nullable: false )
        category( nullable: false )
        proffesor( nullable: true )
    }
}