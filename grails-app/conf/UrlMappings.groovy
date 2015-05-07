class UrlMappings {

	static mappings = {
        "/dashboard/categories.json" {
            controller = "Category"
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
