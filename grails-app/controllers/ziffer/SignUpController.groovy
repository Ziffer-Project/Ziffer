package ziffer
import grails.converters.JSON

class SignUpController {

    def index() {}

    def register(){
        def user = new User(params.username, params.password)
        def json = user as JSON
        render json
    }

    def edit(){
        if(session==true){

        }
    }


    def createUser(){

        def user = new User(username: params.name,
                password: params.password,
                banned: params.ban,
                validated: params.val,
                profile: new Profile(name: params.fullName,
                        email: params.email,
                        aboutMe: params.aboutMe,
                        phone: params.phone,
                        answerScore: params.ansScore,
                        questionScore: params.qstScore,
                        zifferCoins: params.zifferCoins)
        )

    }
}
