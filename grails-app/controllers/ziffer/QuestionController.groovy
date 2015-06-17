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
            def description = request.JSON['description']
            def userQuestion = new Question( asker: User.findById(user.id), category: category, title: params.title, dateCreated: new Date(),
                                                    description: description, dueDate: dueDate, tags: params.tags )
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
        def user = User.findById( session.user.id )
        def json
        if(user){
            json = user.getQuestions() as JSON
            render json
        }else{
            render ([] as JSON)
        }

    }

}
