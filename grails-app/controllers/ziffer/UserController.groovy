package ziffer

import grails.converters.JSON

class UserController {

    def signUp(){
      def user = new User(username: params.name, password: params.password, banned: params.ban, validated: params.val, profile: new Profile(name: params.fullName, email: params.email, aboutMe: params.aboutMe, avatar: params.avatar,phone: params.phone, answerScore: params.ansScore, questionScore: params.qstScore, zifferCoins: params.zifferCoins) )
      user.save( failOnError: true, flush: true, insert: true )

    }
    def edit(){
      if(params.id && User.exists.(params.id)){
      def user = User.findById(params.id)
      render user as JSON
      }
    }

  def trySave(){
      def nid = 1
      if(nid && User.exists.(nid)){
      newUser = User.findById(nid)
      pritnln newUser.name
      /*newUser.username= name
      newUser.password= password
      newUser.banned= ban
      newUser.validated= val
      newUser.profile= new Profile(name: fullName,
                                  email: email,
                                  aboutMe: aboutMe,
                                  avatar: avatar,
                                  phone: phone,
                                  answerScore: ansScore,
                                  questionScore: qstScore,
                                  zifferCoins: zifferCoins)
      */
      newUser.password = "contraseña"
      newUser.save( failOnError: true, flush: true, insert: true )
      }
    }

  def save(){
      if(params.id && User.exists.(params.id)){
      newUser = User.findById(params.id)
      newUser.username= params.name
      newUser.password= params.password
      newUser.banned= params.ban
      newUser.validated= params.val
      newUser.profile= new Profile(name: params.fullName,
                                  email: params.email,
                                  aboutMe: params.aboutMe,
                                  avatar: params.avatar,
                                  phone: params.phone,
                                  answerScore: params.ansScore,
                                  questionScore: params.qstScore,
                                  zifferCoins: params.zifferCoins)

      newUser.save( failOnError: true, flush: true, insert: true )
      }
    }
}
