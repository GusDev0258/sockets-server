package udesc.eso.repository

import udesc.eso.exception.MagoNotFoundException
import udesc.eso.model.Mago

object MagoRepository : BasicRepositoryProtocol {
    private val magoList = mutableListOf<Mago>()

    override fun save(fields: MutableMap<String, String>): String {
        val mago = Mago(
            cpf = fields["cpf"].toString(),
            nome = fields["nome"].toString(),
            endereco = fields["endereco"].toString(),
            idade = fields["idade"].toString().toInt(),
            elemento = fields["elemento"].toString(),
            magia = fields["magia"].toString(),
            chapeu = fields["chapeu"].toBoolean()
        )
        if (magoList.any { it.cpf == mago.cpf }) {
            return "Já existe um mago com este cpf";
        }
        magoList.add(mago);
        return "Mago salvo com sucesso"
    }

    override fun getByIdentifier(identifier: String): Mago {
        val magoFound = magoList.find { it.cpf == identifier }
        if (magoFound == null) {
            throw MagoNotFoundException("Mago não encontrado")
        }
        return magoFound
    }

    override fun update(identifier: String, fields: MutableMap<String, String>): Boolean {
        try {
            var magoFound = getByIdentifier(identifier)
            magoFound.nome = fields["nome"].toString()
            magoFound.endereco = fields["endereco"].toString()
            magoFound.idade = fields["idade"].toString().toInt()
            magoFound.magia = fields["magia"].toString()
            magoFound.elemento = fields["elemento"].toString()
            magoFound.chapeu = fields["chapeu"].toBoolean()
            return true
        } catch (exception: Exception) {
            exception.printStackTrace()
            return false
        }
    }

    override fun getAll(): List<Mago> {
        return magoList.toList()
    }

    override fun deleteByIdentifier(identifier: String): String {
        try {
            magoList.remove(getByIdentifier(identifier))
            return "Mago deletado com sucesso"
        } catch (exception: Exception) {
            exception.printStackTrace()
            return exception.message.toString()
        }
    }
}