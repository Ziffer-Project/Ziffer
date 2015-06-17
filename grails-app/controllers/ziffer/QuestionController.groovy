package ziffer

import grails.converters.JSON

class QuestionController {

    def index() {
        def json = Question.list() as JSON
        render json
    }

    /*
       * Mapping para creación de pregunta
       * Se recibe el título (title), fecha límite (dueDate), los tags (tags, es un string que se debe separar
       * por comas) y la descripción (description)
       */
    def postUserQuestion() {
        User user = session.user
        def json = [] as JSON
        if( user ){
            Category category = Category.findById(params.categoryId)
            Question userQuestion = new Question( asker: user, category: category, title: params.title, dateCreated: new Date(),
                                                    description: params.description, dueDate: params.dueDate, tags: params.tags )
            if( userQuestion.save( failOnError: true, flush: true, insert: true ) ){
                json.putAt( 'created', true )
            }
            else{
                json.putAt( 'created', false )
            }

        }else{
            json.putAt( 'created', false )
        }
        render json
    }

    def showUserQuestions() {
        User user = session.user
        def json = [] as JSON
        if(user){
            json = user.questions as JSON
        }
        render json
    }

}
