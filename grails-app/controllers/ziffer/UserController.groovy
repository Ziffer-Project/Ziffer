package ziffer
import grails.converters.JSON

class UserController {

    def index() {}

    def signUp(){

    }

    def edit(){
        if(params.profileId>0){
            def json = Profile.findById(1) as JSON
            render json
        }
    }

    def save(){

    }
}
