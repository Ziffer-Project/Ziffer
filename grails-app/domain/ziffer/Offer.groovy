package ziffer

class Offer {

    int zifferCoins
    Date offerDate
    boolean accepted

    static belongsTo = [ question: Question, offerer: User ]

}
