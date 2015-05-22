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
}
