import ziffer.Category
import ziffer.Question
import ziffer.User

class BootStrap {

    def init = { servletContext ->
        createTestData()
        createRdmUsers(6)
    }

    def destroy = {
    }

    private void createTestData() {
        def category = new Category(name: "Algebra", id: 1)
        assert category.save(failOnError: true, flush: true, insert: true)

        category = new Category(name: "Geometry", id: 2)
        assert category.save(failOnError: true, flush: true, insert: true)

        category = new Category(name: "Calculus", id: 3)
        assert category.save(failOnError: true, flush: true, insert: true)

        assert Category.count == 3
    }

    private void createRdmUsers( def number ){
        def userName = [] as Set
        def user
        def r = new Random()
        (1..number).each{
            def name = createRdmText( 5 )
            while(userName.contains(name)){
                name = createRdmText( 5 )
            }
            userName.add( name )
            def password = createRdmText(8) // or use 12345
            def email = name + "@" + "gmail.com" // powered by Google :v
            def phone = createRndPhoneNum()
            def ansScore = createRndNumber(-100, 100)
            def qstScore = createRndNumber(-100, 100)
            def zifferCoins = createRndNumber(0, 100)
            def ban = r.nextBoolean()
            user = new User( answerScore: ansScore, name: name, ban: ban, email: email, password: password, phone: phone, questionScore: qstScore, username: name, zifferCoins: zifferCoins )
            createRdmQuestion(r.nextInt(5),user)
            user.save( failOnError: true, flush: true, insert: true )
        }
    }

    private void createRdmQuestion( def number, def user ){
        def r = new Random()
        (1..number).each {
            def num = r.nextInt(5)
            def title = "";
            (0..num).each {
                title += createRdmText(r.nextInt(10))+" "
            }
            def description = ""
            num = r.nextInt(20)
            (0..num).each {
                description += createRdmText(r.nextInt(10))+" "
            }
            num = r.nextInt(Category.list().size())
            def category = Category.list().get(num)
            def iniDate = new Date()
            iniDate.setTime( iniDate.time - 5*r.nextInt(1000000000) - 5 * 1000000000L )
            def dueDate = new Date()
            dueDate.setTime( Date.newInstance().time + 5*r.nextInt(1000000000) + 5*1000000000L )
            def q = new Question(category: category, createdDate: iniDate, description: description, dueDate: dueDate, title: title, user: user )
            user.addToQuestions(q)
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