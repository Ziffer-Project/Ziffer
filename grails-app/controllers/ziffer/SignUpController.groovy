package ziffer
import grails.converters.JSON

class SignUpController {

    def index() {}

    def signUp(){
        def user = new User()//(params.username, params.password)
        user.username=params.username
        user.password=params.password
        user.save()
        def json = user as JSON
        render json
    }

    def edit(){
        def me=session["username"];
        if(me){
            render: "Its working"
        }else{render: "Not yet"}
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
