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
            def json = new Response( access: true ) as JSON
            render json
            //redirect( url: '/#/dashboard')
        }
        else {
            def json = new Response( access: false ) as JSON
            render json
            //redirect( controller: 'login' )
        }
    }

    def doLogout(){
        session.user = null
    }
}

class Response {
    Boolean access
}
