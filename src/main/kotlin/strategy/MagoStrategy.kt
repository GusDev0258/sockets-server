package udesc.eso.strategy

import udesc.eso.repository.BasicRepositoryProtocol
import udesc.eso.repository.MagoRepository

class MagoStrategy : OperationStrategyProtocol {
    private val entity: String = "Mago"
    private val repository: BasicRepositoryProtocol = MagoRepository

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