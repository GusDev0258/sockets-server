package udesc.eso.strategy

import udesc.eso.repository.BasicRepositoryProtocol

interface OperationStrategyProtocol {
    fun insert(fields: MutableMap<String, String>)
    fun getOperation(fields: MutableMap<String, String>): Any
    fun update(fields: MutableMap<String, String>): String
    fun delete(fields: MutableMap<String, String>): String
    fun list(): String

    fun entityListing(entityList: List<Any>): String {
        if (entityList.isEmpty()) return "0"
        val result = StringBuilder("${entityList.size}\n")
        entityList.forEach { result.append(it.toString()).append("\n") }
        return result.toString()
    }

    fun entityDelete(entity: String, identifier: String, entityRepository: BasicRepositoryProtocol): String {

        if (entityRepository.getAll().isEmpty()) return "Sem ${entity}s cadastrados"
        try {
            entityRepository.deleteByIdentifier(identifier)
            return "$entity removida com sucesso"
        } catch (exception: Exception) {
            return "${entity} não encontrado"
        }
    }

    fun entityUpdate(
        entity: String,
        identifier: String,
        entityRepository: BasicRepositoryProtocol,
        updatedFields: MutableMap<String, String>
    ): String {
        var hasUpdated = entityRepository.update(identifier, updatedFields)
        if (hasUpdated) {
            return "${entity} atualizado com sucesso"
        } else {
            return "${entity} não encontrado"
        }
    }

    fun entityGet(entity: String, identifier: String, entityRepository: BasicRepositoryProtocol): Any {
        if (entityRepository.getAll().isEmpty()) return "Sem ${entity}s cadastradas"
        try {
            return entityRepository.getByIdentifier(identifier)
        } catch (exception: Exception) {
            println(exception.message)
            return "${entity} não encontrada"
        }
    }
}