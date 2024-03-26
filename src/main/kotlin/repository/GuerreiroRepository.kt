package udesc.eso.repository

import udesc.eso.exception.GuerreiroNotFoundException
import udesc.eso.model.Guerreiro

object GuerreiroRepository : BasicRepositoryProtocol {
    private val guerreiroList = mutableListOf<Guerreiro>()

    override fun save(fields: MutableMap<String, String>): String {
        val guerreiro = Guerreiro(
            cpf = fields["cpf"].toString(),
            nome = fields["nome"].toString(),
            endereco = fields["endereco"].toString(),
            idade = fields["idade"].toString().toInt(),
            arma = fields["arma"].toString(),
            escudo = fields["escudo"].toBoolean(),
        )
        if (guerreiroList.any { it.cpf == guerreiro.cpf }) return "Já existe um guerreiro com este cpf"
        guerreiroList.add(guerreiro)
        println("guerreiro salvo")
        return "Guerreiro salvo com sucesso"
    }

    override fun getByIdentifier(identifier: String): Guerreiro {
        println(guerreiroList)
        val guerreiroFound = guerreiroList.find { it.cpf == identifier }
        println(guerreiroFound)
        if (guerreiroFound === null) throw GuerreiroNotFoundException()
        return guerreiroFound
    }

    override fun update(identifier: String, fields: MutableMap<String, String>): Boolean {
        try {
            var guerreiroFound = getByIdentifier(identifier)
            guerreiroFound.nome = fields["nome"].toString()
            guerreiroFound.endereco = fields["endereco"].toString()
            guerreiroFound.idade = fields["idade"].toString().toInt()
            guerreiroFound.arma = fields["arma"].toString()
            guerreiroFound.escudo = fields["escudo"].toBoolean()
            return true
        } catch (exception: Exception) {
            exception.printStackTrace()
            return false
        }
    }

    override fun getAll(): List<Guerreiro> {
        return guerreiroList.toList()
    }

    override fun deleteByIdentifier(identifier: String): String {
        try {
            guerreiroList.remove(this.getByIdentifier(identifier))
            return "Guerreiro deletado com sucesso"
        } catch (exception: Exception) {
            exception.printStackTrace()
            return exception.message.toString()
        }
    }
}