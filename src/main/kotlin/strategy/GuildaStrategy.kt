package udesc.eso.strategy

import udesc.eso.model.Pessoa
import udesc.eso.repository.*

class GuildaStrategy : OperationStrategyProtocol {
    private val entity: String = "Guilda"
    private val repository: GuildaRepository = GuildaRepository
    override fun insert(fields: MutableMap<String, String>) {
        repository.save(fields)
    }

    override fun getOperation(fields: MutableMap<String, String>): Any {
        return this.entityGet(entity, fields["nome"].toString(), repository)
    }

    override fun update(fields: MutableMap<String, String>): String {
        return this.entityUpdate(entity, fields["nome"].toString(), repository, fields)
    }

    override fun delete(fields: MutableMap<String, String>): String {
        return this.entityDelete(entity, fields["nome"].toString(), repository)
    }

    override fun list(): String {
        return this.entityListing(repository.getAll())
    }

    fun addGuildMember(fields: MutableMap<String, String>): String {
        try {
            val member = guildHelper(fields["cpf"].toString())
            repository.addMember(fields["nome"].toString(), member)
            return "Membro adicionado com sucesso"
        } catch (exception: Exception) {
            exception.printStackTrace()
            return "Não foi possível adicionar o membro"
        }
    }

    fun removeGuildMember(fields: MutableMap<String, String>): String {
        try {
            return repository.deleteMember(fields["nome"].toString(), fields["cpf"].toString())
        } catch (exception: Exception) {
            exception.printStackTrace()
            return exception.message.toString()
        }
    }

    fun getGuildMember(fields: MutableMap<String, String>): Any {
        try {
            return repository.getMember(fields["nome"].toString(), fields["cpf"].toString())
        } catch (exception: Exception) {
            exception.printStackTrace()
            return exception.message.toString()
        }
    }

    fun listGuildMembers(fields: MutableMap<String, String>): Any {
        try {
            val list = repository.listMembers(fields["nome"].toString())
            return entityListing(list)
        } catch (exception: Exception) {
            exception.printStackTrace()
            return exception.message.toString()
        }
    }

    private fun guildHelper(memberCpf: String): Pessoa {
        var member: Pessoa
        try {
            member = MagoRepository.getByIdentifier(memberCpf)
            return member
        } catch (exception: Exception) {
            exception.printStackTrace()
            member = GuerreiroRepository.getByIdentifier(memberCpf)
            return member
        }
    }
}