package ziffer

import grails.converters.JSON

class QuestionController {

    def index() {
        def json = Question.list() as JSON
        render json
    }

}
