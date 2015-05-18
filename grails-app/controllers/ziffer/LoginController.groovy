package ziffer

class LoginController {

    static defaultAction = "login"

    def login() {

    }

    def doLogin(){
        def user = User.findAllWhere([username: params.username, password: params.password])
        if( user ) {
            redirect( url: '/#/dashboard')
        }
        else {
            redirect( controller: 'login' )
        }
    }
}
