import ziffer.*
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        createCategories()
        createRdmUsers(6)
	createRdmOffers()
	acceptOffers()
	createAnswers()
	createComments()
	JSON.registerObjectMarshaller(Category){
	    def json = [:]
	    json['id'] = it.id
	    json['name'] = it.name
	    // Por si se necesita en el futuro
	    //json['description'] = it.description
	    return json
	}

    }

    def destroy = {

    }
    

    private void createCategories() {

	//From Wikipedia, the free encyclopedia :P
	/*Será en últimas aquí donde se creen las categorías que existan, a través del constructor
	* también se le puede asignar la imagen del banner que tendrá, por ahora dejé esa imagen nula.
	* Eliminé el id, es redundante, Hibernate lo crea por defecto
	*/
	println "Creando 3 categorías"
        def category = new Category(name: "Algebra", description: "Algebra is the study of mathematical symbols and the rules for manipulating these symbols.")
        assert category.save(failOnError: true, flush: true, insert: true)

        category = new Category(name: "Geometry", description: "Geometry is concerned with questions of shape, size, relative position of figures, and the properties of space. ")
        assert category.save(failOnError: true, flush: true, insert: true)

        category = new Category(name: "Calculus", description: "Calculus is the mathematical study of change.")
        assert category.save(failOnError: true, flush: true, insert: true)

        assert Category.count == 3
    }

    private void createRdmUsers( def number ){


	println "Creando usuarios de prueba"
        def userName = [] as Set
        def user
        def r = new Random()
        (1..number).each{
            def name = createRdmText( 10 )
            while(userName.contains(name)){
                name = createRdmText( 10 )
            }
            userName.add( name )
	    def fullName = createRdmText(10)
	    def aboutMe = createRdmText(30)
            def password = createRdmText(8) // or use 12345
            def email = name + "@" + "gmail.com" // powered by Google :v
            def phone = createRndPhoneNum()
	    /*Las clases de dominio que manejan puntajes no almacenan ninguna restricción con respecto al máximo
	    * o mínimo puntaje posible, quizá cambie.
	    */
            def ansScore = createRndNumber(-100, 100)
            def qstScore = createRndNumber(-100, 100)
            def zifferCoins = createRndNumber(0, 100)
            def ban = r.nextBoolean()
	    def val = r.nextBoolean()
            user = new User(username: name, password: password, banned: ban, validated: val, profile: new Profile(name: fullName, email: email, aboutMe: aboutMe, phone: phone, answerScore: ansScore, questionScore: qstScore, zifferCoins: zifferCoins) )
            createRdmQuestion(r.nextInt(5),user)
            user.save( failOnError: true, flush: true, insert: true )
            println "${user.username} ${user.password}"
        }
    }

    private void createRdmQuestion( def number, def user ){

	println "Creando preguntas de prueba"
        def r = new Random()
        (1..number).each {
	    //Al menos tiene 3 caracteres para pasar la validación de título
            def num = createRndNumber(3,15)
            def title = ""
            (0..num).each {
                title += createRdmText(r.nextInt(10))+" "
            }
            def description = ""
	    //Al menos tiene 10 caracteres para pasar la validación de descripción
            num = createRndNumber(10,30)
            (0..num).each {
                description += createRdmText(r.nextInt(10))+" "
            }
            num = Category.list().size()

	    /*http://docs.oracle.com/javase/7/docs/api/java/util/Random.html#nextInt(int)
	    * por lo tanto num puede contener 0, Hibernate no guarda ningún valor con id 0
            * luego en la siguiente línea se debe sumar 1 para que obtenga el id desde 1 inclusive, 
	    * de lo contrario violaré la restricción de nullable: false al guardar una pregunta con categoría nula.
	    */
            def category = Category.get(createRndNumber(1,num))

	    /*Grails tiene una propiedad llamada autoTimeStamp, si en una clase pongo dos campos
	    * llamados dateCreated y lastUpdated, automáticamente me creará una fecha para el campo
            * de creación y me actualizará la fecha de lastUpdated cada vez que persista el dato, así que
	    * iniDate puede ser innecesario
            */
            def iniDate = new Date()
            iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
            def dueDate = new Date()
            dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
	    def lauDate = new Date()

	    def tags = [] as Set
	    def numTags = createRndNumber(0,5)
	    (1..numTags).each{

		/*No hay restricciones en Question sobre el contenido o la longitud de cada tag
		* pero aquí supongo que, si existe al menos 1, debe ser mayor o igual a 1 carácter y menor o
		* igual a 10 caracteres y no contener espacios, etc...
		*/
		tags.add(createRdmText(createRndNumber(1,10)))

	    }
	    lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
            def q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
            user.addToQuestions(q)
        }
    }

    private createRdmOffers(){

	println "Creando ofertas de prueba"
        def r = new Random()
	def times = Question.list().size()
	(0..times).each{
		
	    //Puede que haya una forma mejor de obtener al azar un objeto de BD...
            def id = createRndNumber(1,times)
	    def question = Question.get(id)
	    def asker = question.getAsker()
	    def numUsers = User.list().size()
	    //Bug: ciclo infinito si solo se crea un usuario en todo el sistema, pero ya fijo sabemos son 6 :B
	    def user = User.get(createRndNumber(1,numUsers))
	    //Quien pregunta no puede ofrecerse a sí mismo una oferta
	    while(user.equals(asker))
	        user = User.get(r.nextInt(numUsers)+1)
	    def zifferCoins = createRndNumber(1,10)			
	    def offer = new Offer(zifferCoins: zifferCoins, offerDate: new Date(), question: question, offerer: user)
	    question.addToOffers(offer)
	    user.addToOffers(offer)
            question.save()
            user.save()

        }

    }

	
    private void acceptOffers(){
	
	println "Aceptando ofertas de prueba"
	def questions = Question.list()
	questions.each{

	    def offers = it.getOffers()
	    if(offers){
		/*¿Si solo hay una oferta por responder una pregunta
		* se acepta automáticamente?
		*/
		def id = createRndNumber(0,offers.size()-1)
		def offer = offers[id]
		offer.setAccepted(true)
		//FIX-ME: Por alguna extraña razón hay que persistir explícitamente offer para que createAnswers() reconozca los objetos creados		
		offer.save()
	    }

	}
		
    }


    private void createAnswers(){

	/*Suponiendo que cada oferta aceptada implique crear una respuesta, pero el diagrama de
	* clases hecho no refuerza dicha necesidad, hay que considerar en qué afecta dicha cosa
	*/
	println "Creando respuestas de prueba"
	def r = new Random()
	def offersAccepted = Offer.findAllWhere(accepted: true)
	offersAccepted.each{

	        def text = createRdmText(100)
	        def posScore = createRndNumber(-100, 100)
                def negScore = createRndNumber(-100, 100)
	        def question = it.getQuestion()
	        def user = it.getOfferer()
	        def answer = new Answer(text: text, posScore: posScore, negScore: negScore, accepted: r.nextBoolean(), question: question, user: user)
	        question.setAnswer(answer)
	        user.addToAnswers(answer)
	        question.save(flush: true, insert: true)
	        user.save(flush: true, insert: true)
	
	}

    }
			 

    private void createComments(){

	println "Creando comentarios de prueba"
	def r = new Random()
	def answers = Answer.list()
	int a = 0
	answers.each{

	    /*Esto quizá refuerce que se deben tener presentes usuarios que preguntan y responden en las
	    * clases de dominio, y no depender tanto de la pregunta, o no acoplarlo tanto, no sé...
	    */
	    /*No está funcionando correctamente :S
	    */
	    def answer = it
	    def asker = it.getQuestion().getAsker()
	    def answerer = it.getAnswerer()
	    def numComments = createRndNumber(0,5)
	    (1..numComments).each{

		def text = createRdmText(20)
		def posScore = createRndNumber(-100, 100)
		def negScore = createRndNumber(-100, 100)
		def commentAsker = r.nextBoolean()
		def comment = new Comment(text: text, negScore: negScore, posScore: posScore, answer: answer, poster: (commentAsker? asker : answerer))
		commentAsker? asker.addToComments(comment) : answerer.addToComments(comment)
		answer.addToReplies(comment)
		commentAsker? asker.save(): answerer.save()
		comment.save(flush: true, insert: true)
	    }

            answer.save(flush: true, insert: true)
	}

    }

    private String createRdmText( def length ){
        def text = ""
        def r = new Random()
        (1..length).each{
            char add = 97 + r.nextInt(26)
            text += add
        }
        text
    }

    private int createRndNumber(def min, def max){
        def r = new Random()
        (min + r.nextInt(max-min+1))
    }

    private String createRndPhoneNum(){
        def r = new Random()
        def num = ""
        (0..9).each {
            num += r.nextInt(10) + ""
        }
        num
    }
}
