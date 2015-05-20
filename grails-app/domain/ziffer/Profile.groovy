package ziffer

class Profile {

    String name
    String email
    String aboutMe
    byte[] avatar
    String phone
    int answerScore
    int questionScore
    int zifferCoins

    static belongsTo  = [ profile : User ]

    static constraints = {

        name nullable: true, maxSize: 100
        email email: true, blank: false
	aboutMe nullable: true, maxSize: 1000
	//¿5 Mb es un buen tamaño máximo?
	avatar nullable: true, maxSize: 5*1024*1024
        phone nullable: true
        answerScore nullable: true
        questionScore: nullable: true
	zifferCoins nullable: true

    }

    static mapping = {

	answerScore defaultValue: 0
        questionScore defaultValue: 0
	zifferCoins defaultValue: 10

    }
}
