package ziffer

import grails.converters.JSON

class QuestionController {

    def index() {
        def json = Question.list() as JSON
        render json
    }

    def create() {

	def tags = params.tags.tokenize(',') as Set
	def asker = User.get(params.asker)
	def category = Category.get(params.category)
	Question question = new Question(title: params.title, description: params.description, dueDate: new Date.parse('dd/MM/yyyy', params.date),
			    asker: asker, category: category, tags: tags)
	if(question.save()){
		asker.addToQuestions(question)
		category.addToQuestions(question)
		asker.save()
		category.save()
		return true
	}
	return false
    }

}
