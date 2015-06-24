package ziffer
import grails.converters.JSON

class SignUpController {

    def index() {}

    def signUp(){
        def newUser = params.username
        def pwd = params.password
        /*def user=new User()//def user = createUser(newUser, pwd)//(params.username, params.password)
        user.username=params.username
        user.password=params.password*/
        def user= createUser(newUser,pwd)
        user.save( failOnError: true, flush: true, insert: true )

        def json = user as JSON
        print user.username
        print user.password
        render json
    }

    def edit(){
        def me=session["username"];
        if(me){
            render: "Its working"
        }else{render: "Not yet"}
    }


    def createUser(usname, pwd){
        def r = new Random()

        def username = usname
    		def fullName = usname
    		def aboutMe = 'Soy un estudiante de Matematicas de pregrado de la UNal'
    		def password = pwd
    		def email = username + "@" + "gmail.com" // powered by Google :v
    		def phone = '1234567'
    		/*Las clases de dominio que manejan puntajes no almacenan ninguna restricción con respecto al máximo
            * o mínimo puntaje posible, quizá cambie.
            */
    		def ansScore = 0
    		def qstScore = 0
    		def zifferCoins = 5
    		def ban = r.nextBoolean()
    		def val = r.nextBoolean()
    		def user = new User(username: username, password: password, banned: ban, validated: val, profile: new Profile(name: fullName, email: email, aboutMe: aboutMe, phone: phone, answerScore: ansScore, questionScore: qstScore, zifferCoins: zifferCoins) )
    		return user
    }
    def recoverPassword(){
        Profile.findAllById(params.id)
    }
}
