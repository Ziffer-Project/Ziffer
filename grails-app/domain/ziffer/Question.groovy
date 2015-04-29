package ziffer

class Question {
    String title
    String description
    String category
    Date createdDate
    Date dueDate
    static belongsTo = [ user : User ]

    static constraints = {
        createdDate = new Date()
        title( nullable: false, blank: false )
        description( nullable: false, blank: false )
        category( nullable: false, blank: false )
        dueDate( validator: { return dueDate.time >= Calendar.getInstance().getTime().time}, nullable: true )
        user( nullable: false )
    }
}