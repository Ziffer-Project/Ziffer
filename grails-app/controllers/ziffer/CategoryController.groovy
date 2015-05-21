package ziffer

import grails.converters.JSON

class JSONCategoryController {

    /*
    * Action de prueba que debería retornar la lista de categorías. El JSON No está
    * estructurado de acuerdo al archivo de ejemplo (Remítase a los comentarios
    * en UrlMappings).
    */

    def index() {
        def json = Category.list() as JSON
        render json
    }

    /*
    * Action de prueba que maneja la 2da URI en UrlMappings (remitirse a éste).
    * Retorna datos de prueba para ver la comunicación con el front-end, por lo tanto
    * no retorna los archivos de la forma requerida.
    */

    def singleCat() {
        if (params.categoryId == "-1") {
            def json = Question.list() as JSON
            render json
        } else {
            def json = Category.findById(params.categoryId) as JSON
            render json
        }
    }

    /*
     * Action de prueba, remítase a UrlMappings para su expliación. Al igual que los
     * otros actions, arroja datos para debug. La línea 'render status: 200' fue incluída
     * porque a pesar de que el servidor recibe la petición de la manera esperada, al
     * front-end es enviado una respuesta de error
     */
    def setOffer() {
        println "Setting offer to question: " + params.questionId + " in category " + params.categoryId + " with an amount of " + params.amount
        render status: 200
    }

}