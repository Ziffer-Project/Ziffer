package ziffer

import grails.converters.JSON

class CategoryController {

    def index() {

        def json = Category.list() as JSON
        render json
    }

}