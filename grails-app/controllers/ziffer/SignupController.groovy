package ziffer

class SignupController {

    def index() { }

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
