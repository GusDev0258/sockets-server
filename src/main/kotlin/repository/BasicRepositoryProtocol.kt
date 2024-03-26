package udesc.eso.repository

interface BasicRepositoryProtocol {
    fun save(fields: MutableMap<String, String>): String
    fun update(identifier: String, fields: MutableMap<String, String>): Boolean
    fun getByIdentifier(identifier: String): Any
    fun deleteByIdentifier(identifier: String): Any
    fun getAll(): List<Any>
}