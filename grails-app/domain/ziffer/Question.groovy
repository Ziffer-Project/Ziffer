package ziffer

class Question {
    String title
    String description
    String category
    Date createdDate
    Date dueDate
    static belongsTo = [ user : User ]

    static constraints = {
        title( nullable: false, blank: false )
        description( nullable: false, blank: false )
        category( nullable: false, blank: false )
        createdDate( nullable: false)
        dueDate( validator: {if (dueDate.time <  Calendar.getInstance().getTime().time) return false else return true }, nullable: true )
        user( nullable: false )
    }
}