package ziffer

import grails.converters.JSON

class QuestionController {

    def index() {
        def json = Question.list() as JSON
        render json
    }

    def create() {


	//Pruebas con estos valores, debe moverse a testing luego
	/*def params = "a,b,c,d,e,f"
	def title = "blablabla"
	def description = "blablablbalbalablalba"
	def date = "21/05/2015"
	def tags = params.tokenize(',') as Set
	def asker = User.get(1)
	def category = Category.get(1)*/

	//Obtiene un set de tags separados por ','	
	def tags = params.tags.tokenize(',') as Set

	//Obtener los objetos usuario y categoría para persistirlos con la nueva pregunta
	def asker = User.get(params.user)
	def category = Category.get(params.category)

	//Asume errores
	def validation = false

	//Crea la pregunta con los parámetros enviados más los procesados
	Question question = new Question(title: params.title, description: params.description, dueDate: Date.parse('dd/MM/yyyy', params.date),
			    asker: asker, category: category, tags: tags)
	//Si las validaciones se hicieron
	if(question.validate()){

		//Añadir la pregunta a las listas
		asker.addToQuestions(question)
		category.addToQuestions(question)
		asker.save()
		category.save()
		validation = true
	}

	//Construcción del JSON
	def answer = [:]
	answer["success"] = validation
	render answer as JSON
    }

}
