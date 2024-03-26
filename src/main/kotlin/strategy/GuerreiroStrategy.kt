package udesc.eso.strategy

import udesc.eso.repository.BasicRepositoryProtocol
import udesc.eso.repository.GuerreiroRepository

class GuerreiroStrategy : OperationStrategyProtocol {
    private val entity: String = "Guerreiro"
    private val repository: BasicRepositoryProtocol = GuerreiroRepository

    override fun insert(fields: MutableMap<String, String>) {
        repository.save(fields)
    }

    override fun getOperation(fields: MutableMap<String, String>): Any {
        return this.entityGet(entity, fields["cpf"].toString(), repository)
    }

    override fun update(fields: MutableMap<String, String>): String {
        return this.entityUpdate(entity, fields["cpf"].toString(), repository, fields)
    }

    override fun delete(fields: MutableMap<String, String>): String {
        return this.entityDelete(entity, fields["cpf"].toString(), repository)
    }

    override fun list(): String {
        return this.entityListing(repository.getAll())
    }

}