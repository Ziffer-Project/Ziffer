import ziffer.Category

class BootStrap {

    def init = { servletContext ->
        createTestData()
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

        category = new Category(name: "My doubts", id: -2)
        assert category.save(failOnError: true, flush: true, insert: true)

        category = new Category(name: "Contributions", id: -1)
        assert category.save(failOnError: true, flush: true, insert: true)

        assert Category.count == 5
    }
}
