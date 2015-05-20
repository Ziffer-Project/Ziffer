package ziffer

import grails.converters.JSON

class LoginController {

    static defaultAction = "login"

    def login() {

    }

    def doLogin(){
        def user = User.findAllWhere([username: params.username, password: params.password])
        if( user ) {
            session.user = user
            render true as JSON
            //redirect( url: '/#/dashboard')
        }
        else {
            render false as JSON
            //redirect( controller: 'login' )
        }
    }
}
