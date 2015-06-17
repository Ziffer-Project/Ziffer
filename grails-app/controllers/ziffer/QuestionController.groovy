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
        def user = session.user
        def jsonT = ['created' : true] as JSON
        def jsonF = ['created' : false] as JSON

        if( user ){
            def category = Category.findById(params.categoryId)
            def lon = Long.parseLong( params.dueDate )
            def dueDate = new Date( lon )
            def userQuestion = new Question( asker: user, category: category, title: params.title, dateCreated: new Date(),
                                                    description: params.description, dueDate: dueDate, tags: params.tags )
            if( userQuestion.save( failOnError: true, flush: true, insert: true ) ){
                render jsonT
            }
            else{
                render jsonF
            }

        }else{
            render jsonF
        }
    }

    def showUserQuestions() {
        def user = session.user
        def json = [] as JSON
        if(user){
            json = user.questions as JSON
        }
        render json
    }

}
