class UrlMappings {

	static mappings = {
        // fetchData: GET
        // mkData: POST
        // mdData: PUT
        // delData: DELETE

        /*
        * Los path de las URI fueron probados. En lo posible no los modifiquen y de ser necesario
        * notifíquenme para hacer los cambios en la vista, el resto lo pueden cambiar a conveniencia
        * para que devuelvan los resultados esperados. Remítanse a la clase CategoryController para
        * que revisen cómo probé las peticiones.
        */

        /*
        *  URI para recobrar la lista de categorías, debe retornar un JSON igual
        *  al del archivo 'web-app/dashboard-view/tempData/categories.json'.
        */
        "/dashboard/fetchData/categories" {
            controller = "Category"
        }

        /*
        *  URI para recobrar la lista de preguntas asociadas a cierta categoría. Debe
        *  retornar un JSON igual al del archivo 'web-app/dashboard-view/tempData/doubts.json'
        *  con el añadido de un campo 'categoryId' el cual contiene la ID de la categoría
        *  a la cual el pregunta pertenece y los campos de fechas deben ser un string con
        *  formato 'dd/MM/AAAA' y no como se muestran en el archivo actualmente.
        *
        *  Hay casos especiales que se deben notar:
        *  a) Cuando se entra al dashboard, la lista inicial de preguntas serán las más recientes.
        *     Este caso ${categoryId} será denotado con un ID de '-1'.
        *  b) Cuando se pulsa sobre alguna categoría en el menú desplegable, se realiza un petición
        *     donde se envía el ID de la categoría y ${questionId} tendrá el valor 'doubts', denotando
        *     que se requieren las preguntas de esa categoría.
        *  c) Cuando se haga click sobre el botón 'Peek' se hará un petición solicitando la descripción
        *     o detalles de una pregunta en específica. Se enviará la ID de la pregunta y de la categoría
        *     a la cual pertenece. El JSON de respuesta en este caso debe ser igual al del archivo
        *     'web-app/dashboard-view/tempData/categories/11.json'.
        */
        "/dashboard/fetchData/questions/${categoryId}/${questionId}?" {
            controller = "Category"
            action = "singleCat"
        }

        /*
        * URI a la cual se le envía una petición PUT para guardar la oferta hecha por un usuario
        * a una pregunta. Se envía como parámetros [categoryId, questionId, amount], donde el primer
        * parámetro es la ID de la categoría a la cual la pregunta en cuestión pertenece, el segundo es la
        * ID de la pregunta y el tercero es la cantidad que se ingresó.
        *
        * Nota: en la vista al hacer click sobre el botón de oferta (verde), la ventana desplegable no se
        * cierra hasta que se ingrese un número >= 0, acepta varias ocurrencias de 0 (e.g. '000000'). Cuando
        * se ingresa un número válido, la ventana se cierra pero la petición es enviada (revisar la consola),
        * una retroalimentación al usuario será implementada más adelante.
        */
        "/dashboard/mdData/setOffer" {
            controller = "Category"
            action = "setOffer"
        }

        /*
        * Mapping para creación de pregunta
        * Se recibe el título (title), fecha límite (dueDate), los tags (tags, es un string que se debe separar
        * por comas) y la descripción (description)
        */
        "/dashboard/mkData/createQuestion" {
            controller = "Question"
            action = "postUserQuestion"
        }

        // Mapping para signin/login
        "/signin/mkData/signinRequest" {
            controller = "Login"
            action = 'doLogin'
        }

        // Mapping para signout/logout
        "/signout/mkData/signoutRequest" {
            controller = "Login"
            action = "doLogout"
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
