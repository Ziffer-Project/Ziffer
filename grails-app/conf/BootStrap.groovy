import ziffer.*
import grails.converters.JSON

class BootStrap {

	def tagsList = ['Maths', 'Logic', 'Geometry', 'Integrate', 'Graphs', 'Sum', 'Algebra', 'Differential', 'Statistics', 'Physics']

	def init = { servletContext ->
		createCategories()
		createRdmUsers()
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
		JSON.registerObjectMarshaller(Question){
			def json = [:]
			json['id'] = it.id
			json['title'] = it.title
			json['description'] = it.description
			json['dateCreated'] = it.dateCreated.format('dd/MM/yyyy')
			json['lastUpdated'] = it.lastUpdated.format('dd/MM/yyyy')
			json['dueDate'] = it.dueDate.format('dd/MM/yyyy')
			json['negScore'] = it.negScore
			json['posScore'] = it.posScore
			json['tags'] = it.tags
			json['username'] = it.getAsker().username
			json['categoryId'] = it.getCategory().id
			json['offers'] = it.getOffers()
			//json['userScore'] = ¿A cuál campo hace referencia? ¿Los coins? ¿El puntaje de aceptación?
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

		def r = new Random()
		println "Creando usuarios de prueba"

		def username = 'JuanPerez'
		def fullName = 'Juan Perez'
		def aboutMe = 'Soy un estudiante de Matematicas de pregrado de la UNal'
		def password = '123456'// or use 12345
		def email = username + "@" + "gmail.com" // powered by Google :v
		def phone = createRndPhoneNum()
		/*Las clases de dominio que manejan puntajes no almacenan ninguna restricción con respecto al máximo
        * o mínimo puntaje posible, quizá cambie.
        */
		def ansScore = createRndNumber(-100, 100)
		def qstScore = createRndNumber(-100, 100)
		def zifferCoins = createRndNumber(0, 100)
		def ban = r.nextBoolean()
		def val = r.nextBoolean()
		def user = new User(username: username, password: password, banned: ban, validated: val, profile: new Profile(name: fullName, email: email, aboutMe: aboutMe, phone: phone, answerScore: ansScore, questionScore: qstScore, zifferCoins: zifferCoins) )
		createRdmQuestion(0,user)
		user.save( failOnError: true, flush: true, insert: true )
		println "${user.username} ${user.password}"



		username = 'MariaSalameda'
		fullName = 'Maria Salameda'
		aboutMe = 'Soy un estudiante de Fisica de postgrado de la UNal :)'
		password = '123456'// or use 12345
		email = username + "@" + "gmail.com" // powered by Google :v
		phone = createRndPhoneNum()
		/*Las clases de dominio que manejan puntajes no almacenan ninguna restricción con respecto al máximo
        * o mínimo puntaje posible, quizá cambie.
        */
		ansScore = createRndNumber(-100, 100)
		qstScore = createRndNumber(-100, 100)
		zifferCoins = createRndNumber(0, 100)
		ban = r.nextBoolean()
		val = r.nextBoolean()
		user = new User(username: username, password: password, banned: ban, validated: val, profile: new Profile(name: fullName, email: email, aboutMe: aboutMe, phone: phone, answerScore: ansScore, questionScore: qstScore, zifferCoins: zifferCoins) )
		createRdmQuestion(1,user)
		user.save( failOnError: true, flush: true, insert: true )
		println "${user.username} ${user.password}"


		username = 'JuanSequera'
		fullName = 'Juan Sequera'
		aboutMe = 'Soy un estudiante de Ingenieria de pregrado de la UNal 3:)'
		password = '123456'// or use 12345
		email = username + "@" + "gmail.com" // powered by Google :v
		phone = createRndPhoneNum()
		/*Las clases de dominio que manejan puntajes no almacenan ninguna restricción con respecto al máximo
        * o mínimo puntaje posible, quizá cambie.
        */
		ansScore = createRndNumber(-100, 100)
		qstScore = createRndNumber(-100, 100)
		zifferCoins = createRndNumber(0, 100)
		ban = r.nextBoolean()
		val = r.nextBoolean()
		user = new User(username: username, password: password, banned: ban, validated: val, profile: new Profile(name: fullName, email: email, aboutMe: aboutMe, phone: phone, answerScore: ansScore, questionScore: qstScore, zifferCoins: zifferCoins) )
		createRdmQuestion(2,user)
		user.save( failOnError: true, flush: true, insert: true )
		println "${user.username} ${user.password}"



		username = 'DavidMesa'
		fullName = 'David Mesa'
		aboutMe = 'Soy un estudiante de Ingenieria de postgrado de la UNal Rocks!!'
		password = '123456'// or use 12345
		email = username + "@" + "gmail.com" // powered by Google :v
		phone = createRndPhoneNum()
		/*Las clases de dominio que manejan puntajes no almacenan ninguna restricción con respecto al máximo
        * o mínimo puntaje posible, quizá cambie.
        */
		ansScore = createRndNumber(-100, 100)
		qstScore = createRndNumber(-100, 100)
		zifferCoins = createRndNumber(0, 100)
		ban = r.nextBoolean()
		val = r.nextBoolean()
		user = new User(username: username, password: password, banned: ban, validated: val, profile: new Profile(name: fullName, email: email, aboutMe: aboutMe, phone: phone, answerScore: ansScore, questionScore: qstScore, zifferCoins: zifferCoins) )
		createRdmQuestion(3,user)
		user.save( failOnError: true, flush: true, insert: true )
		println "${user.username} ${user.password}"


		username = 'DanielManzanita'
		fullName = 'Daniel Manzanita'
		aboutMe = 'Soy un estudiante de Matematicas de postgrado de la UNal (´)'
		password = '123456'// or use 12345
		email = username + "@" + "gmail.com" // powered by Google :v
		phone = createRndPhoneNum()
		/*Las clases de dominio que manejan puntajes no almacenan ninguna restricción con respecto al máximo
        * o mínimo puntaje posible, quizá cambie.
        */
		ansScore = createRndNumber(-100, 100)
		qstScore = createRndNumber(-100, 100)
		zifferCoins = createRndNumber(0, 100)
		ban = r.nextBoolean()
		val = r.nextBoolean()
		user = new User(username: username, password: password, banned: ban, validated: val, profile: new Profile(name: fullName, email: email, aboutMe: aboutMe, phone: phone, answerScore: ansScore, questionScore: qstScore, zifferCoins: zifferCoins) )
		createRdmQuestion(4,user)
		user.save( failOnError: true, flush: true, insert: true )
		println "${user.username} ${user.password}"



	}


	private void createRdmQuestion( def number, def user ){

//		println "Creando preguntas de prueba"

		/*http://docs.oracle.com/javase/7/docs/api/java/util/Random.html#nextInt(int)
				* por lo tanto num puede contener 0, Hibernate no guarda ningún valor con id 0
					* luego en la siguiente línea se debe sumar 1 para que obtenga el id desde 1 inclusive,
				* de lo contrario violaré la restricción de nullable: false al guardar una pregunta con categoría nula.
				*/


		/*Grails tiene una propiedad llamada autoTimeStamp, si en una clase pongo dos campos
        * llamados dateCreated y lastUpdated, automáticamente me creará una fecha para el campo
            * de creación y me actualizará la fecha de lastUpdated cada vez que persista el dato, así que
        * iniDate puede ser innecesario
        */

		/*No hay restricciones en Question sobre el contenido o la longitud de cada tag
					* pero aquí supongo que, si existe al menos 1, debe ser mayor o igual a 1 carácter y menor o
					* igual a 10 caracteres y no contener espacios, etc...
					*/
		def r = new Random()

		switch(number) {
			case 0:
				def num = createRndNumber(3,7)
				def title = "Is there a relation between boundary value problems and ill-posed problem?"
				def description = "\n" +
						"\n" +
						"This question is related to the links: First defining ill-posed problems:\n" +
						"\n" +
						"http://www.encyclopediaofmath.org/index.php/Ill-posed_problems\n" +
						"\n" +
						"and second the Green's theorem which is related to the boundary value problem.\n" +
						"\n" +
						"http://en.wikipedia.org/wiki/Green%27s_theorem\n" +
						"\n" +
						"It appears the first requires solution of an integral equation and second is an theorem related to integral equation. Therefore, in solving the first can we use the second theorem? However, in the literature both are completely unrelated. My question is there are or can we form any relation between them?"
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				def category = Category.findByName("Algebra")

				def iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				def dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def lauDate = new Date()

				def tags = [] as Set
				def numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)



				num = createRndNumber(3,7)
				title = "Find the minimum total surface area of the cylinder in given circumstances."
				description = "\n" +
						"\n" +
						"    Six solid hemispherical balls have to arranged one upon the other vertically .Find the minimum total surface area of the cylinder in which the hemispherical balls can be arranged, if the radii of each hemispherical ball is 7 cm.\n" +
						"\n" +
						"a.)2056b.)2156c.)1232d.)none of these\n" +
						"\n" +
						"I tried\n" +
						"\n" +
						"h=7×6=42 cm\n" +
						"\n" +
						"r=72 cm\n" +
						"\n" +
						"Total surface area =2π×72×42+2×π×(72)2≈1000.5cm2\n" +
						"\n" +
						"But the book is giving option b.)"
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				category = Category.findByName("Geometry")

				iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				lauDate = new Date()

				tags = [] as Set
				numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)




				num = createRndNumber(3,7)
				title = "solve diferential equation difficulties"
				description = "\n" +
						"\n" +
						"I'm studying math and I've founded this equation: dpdt=0.5p−450. I write it so: p′=0.5p−450. Derivatint the two sides: p′′=0.5p′⇒p′′−0.5p=0 General solution: m2−0.5m=0→m1=0,m2=0.5⇒ym(x)=c1e0.5t+c2te0.5t\n" +
						"\n" +
						"p′(0)=−450, them dym(0)=−450\n" +
						"\n" +
						"In the book, the result is p=900+c⋅e0.5t. How they find it? Thanks!"
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				category = Category.findByName("Calculus")

				iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				lauDate = new Date()

				tags = [] as Set
				numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)

				break

			case 1:
				def num = createRndNumber(3,7)
				def title = "System of linear equations: and a small perturbation"
				def description = "\n" +
						"\n" +
						"If Ax=b and Ax′=b′ where x′ and b′ are x and b with a small perturbation, the following inequality will always hold:\n" +
						"\n" +
						"(∥x−x′∥//∥x∥)≤∥∥A−1∥∥⋅∥A∥⋅(∥b−b′∥/∥b∥).\n" +
						"\n" +
						"My question is, when is this inequality an equality? (assuming b≠b′)"
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				def category = Category.findByName("Algebra")

				def iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				def dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def lauDate = new Date()

				def tags = [] as Set
				def numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)



				num = createRndNumber(3,7)
				title = "what is the volume of cylinder if"
				description = "The total surface area of a cylinder is 80π cm2 and the difference between the height and the radius is 2 cm. What is the volume of that cylinder? I have tried to find the height with the help of area but I can't."
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				category = Category.findByName("Geometry")

				iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				lauDate = new Date()

				tags = [] as Set
				numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)




				num = createRndNumber(3,7)
				title = "Applicability of tests of convergence for series with non-negative terms"
				description = "We know that there are many criteria of convergence for series with non-negative terms (for example, ratio test (with limit), root test (with limit), integral, comparison, and asymptotic comparison, etc). Could you clarify if these tests yield corret results if the series has non-negative terms only for n>ν, for some ν∈N?"
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				category = Category.findByName("Calculus")

				iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				lauDate = new Date()

				tags = [] as Set
				numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)
				break


			case 2:
				def num = createRndNumber(3,7)
				def title = "Find a symmetric matrix B that makes ABC symmetric, A,C known"
				def description = "\n" +
						"\n" +
						"I have two known matricies A∈Rnxm, C∈Rmxn with m>n.\n" +
						"\n" +
						"I'm trying to find a B∈Rmxm that is symmetric and makes ABC Symmetric.\n" +
						"B≠0\n" +
						"\n" +
						"Right so,\n" +
						"\n" +
						"B′=B\n" +
						"\n" +
						"ABC=C′B′A′=C′BA′\n" +
						"\n" +
						"I'm lost as to how to continue, some form of matrix decomposition?"
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				def category = Category.findByName("Algebra")

				def iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				def dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def lauDate = new Date()

				def tags = [] as Set
				def numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)



				num = createRndNumber(3,7)
				title = "BMO1 2006/07 Question 2 Geometry Problem"
				description = "2. In the convex quadrilateral ABCD, points M,N lie on the side AB such that AM=MN=NB, and points P,Q lie on the side CD such that CP=PQ=QD. Prove that Area of AMCP= Area of MNPQ=13 Area of ABCD.\n" +
						"\n" +
						"I know how to prove that AMCP=13 Area of ABCD, but not sure about the rest. Would it suffice to say that the other section is just a third of a square that has been stretched or does this need to be proven? Thanks in advance to anyone who can prove or come up with any hints."
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				category = Category.findByName("Geometry")

				iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				lauDate = new Date()

				tags = [] as Set
				numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)
				break



			case 3:
				def num = createRndNumber(3,7)
				def title = "Total area for a natural nested set of convex polygons."
				def description = "Suppose we have a convex polygon P0 with n given vertices, and we want to \"nest\" polygons Pj for j>0 by taking the midpoints between edges of Pj−1 as the vertices. For a regular polygon the total area of all the polygons will form a geometric series that is pretty easy to solve for (at least in terms of trig functions) in terms of the area A of the original polygon P0. However, what if P0 is not regular? If we know the area of P0, is there a formula for the sum of areas of all the nested polygons, in terms of n and the original area? Or do we need more information, like the vertices of P0? If we need to know the vertices, are there formulas in terms of the vertices at least for some small values of n, like n=3,4?"
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				def category = Category.findByName("Geometry")

				def iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				def dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def lauDate = new Date()

				def tags = [] as Set
				def numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)



				num = createRndNumber(3,7)
				title = "How to solve geometry question on internal tangency"
				description = "Let Γ1 be a circle with centre at the Point O and radius R. Two other circles Γ2 and Γ3 with centres O2 and O3 respectively are internally tangent to Γ1 and meet each other at Points A and B. Find the sum of the radii of Γ2 and Γ3, given that angle OAB=90∘."
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				category = Category.findByName("Geometry")

				iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				lauDate = new Date()

				tags = [] as Set
				numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)
				break

			case 4:
				def num = createRndNumber(3,7)
				def title = "Literature Reference for transformations through vector spaces"
				def description = "\n" +
						"\n" +
						"I am trying to understand the transformations through vector spaces:\n" +
						"\n" +
						"Problem 1. Let's say we have orthonormal basis B={v1,v2,…,vn} spanning the vector space V and basis B1={u1,u2,…,un} spanning the vector space U and suppose we have a vector x in space of V. Our objective is to transform vector x to vector space U.\n" +
						"\n" +
						"Problem 2. This problem extends the above scenario but now we state the problem as: Let's say we have some matrix A is specific for vector space V making maybe some rotation, and our aim is to use matrix A in vector space of U, for this we need to transform the matrix A itself to space of U.\n" +
						"\n" +
						"Can anyone provide explanations or otherwise reference literature where I can get more knowledge of matrix transformations through SVD?"
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				def category = Category.findByName("Algebra")

				def iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				def dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def lauDate = new Date()

				def tags = [] as Set
				def numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				def q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)



				num = createRndNumber(3,7)
				title = "Calculate the (variational) derivative of the following equation;"
				description = "\n" +
						"\n" +
						"Consider E[u]=∫10(u′(x))2+(u(x))2−2f(x)u(x)dx.\n" +
						"\n" +
						"Calculate the variational derivation for a function v; in other words, calculate ddϵE[u+ϵv] at ϵ=0.\n" +
						"\n" +
						"*ddϵ is the derivative with respect to ϵ.\n" +
						"\n" +
						"My work:\n" +
						"\n" +
						"ddϵE[u+ϵx]=ddϵ∫10(u+ϵv)′(x)2+(u+ϵv)(x)2−2f(x)(u+ϵv)(x)dx . I moved the derivative sign inside the integral and split the integral up, like so: ∫10ddϵ(u+ϵv)′(x)2dx+∫10ddϵ(u+ϵv)(x)2−2∫10ddϵf(x)(u+ϵv)(x)dx. Here is where I get stuck; I'm not sure how to take the derivatives and simplify."
				//Al menos tiene 10 caracteres para pasar la validación de descripción
				num = Category.list().size()
				category = Category.findByName("Calculus")

				iniDate = new Date()
				iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
				dueDate = new Date()
				dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				lauDate = new Date()

				tags = [] as Set
				numTags = createRndNumber(0,5)
				(1..numTags).each{
					tags.add( tagsList.get( r.nextInt( tagsList.size() ) ) )
				}
				lauDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
				q = new Question(title: title, description: description, dateCreated: iniDate, lastUpdated: lauDate, dueDate: dueDate, category: category, asker: user, tags: tags )
				user.addToQuestions(q)
				break

		}
	}

	private createRdmOffers(){

		//println "Creando ofertas de prueba"
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

		//println "Aceptando ofertas de prueba"
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
		//println "Creando respuestas de prueba"
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

		//println "Creando comentarios de prueba"
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
