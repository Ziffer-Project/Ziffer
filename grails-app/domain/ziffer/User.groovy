package ziffer

class User {

    String username
    String password
    Date dateCreated
    boolean banned
    boolean validated
    
    static hasOne = [ profile : Profile ]

    static hasMany = [ answers : Answer, questions : Question, offers: Offer, comments : Comment ]

    static constraints = {

        username blank: false, size: 6..20, unique: true
        password blank: false, size: 6..20, password: true
        answers nullable: true
        questions nullable: true
        offers nullable: true
        comments nullable: true

    }
}
