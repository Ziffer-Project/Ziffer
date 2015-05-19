package ziffer

class Category {

    String name
    String description
    byte[] banner

    static hasMany = [ questions: Question ]

    static constraints = {

        name blank: false
	description blank: false
	//Asumo que la imagen que aparecerá en el dashboard proviene de aquí
	banner maxSize: 10*1024*1024
	
    }
}
