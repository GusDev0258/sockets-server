package udesc.eso.service

class MessageMapperService(message: String) {
    var operation: String? = null
    var entity: String? = null
    val fields: MutableMap<String, String> = mutableMapOf()

    init {
        parseMessage(message)
    }

    private fun parseMessage(message: String) {
        val messageParts = message.split(";").map {it.trim()}
        if( messageParts.size >= 2) {
            operation = messageParts[0]
            entity = messageParts[1]
            for( x in 2 until messageParts.size ) {
                val fieldParts = messageParts[x].split("=")
                if(fieldParts.size == 2) {
                    fields[fieldParts[0]] = fieldParts[1]
                }
            }
        }
    }
}