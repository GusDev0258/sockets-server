package udesc.eso.repository

import udesc.eso.exception.PessoaNotFoundException
import udesc.eso.model.Pessoa

object PessoaRepository : BasicRepositoryProtocol {
    private var pessoaList = mutableListOf<Pessoa>()

    override fun save(fields: MutableMap<String, String>): String {
        val pessoa = Pessoa(
            cpf = fields["cpf"].toString(),
            nome = fields["nome"].toString(),
            endereco = fields["endereco"].toString(),
            idade = fields["idade"].toString().toInt()
        )
        if (pessoaList.any { it.cpf == pessoa.cpf }) {
            return "Já existe uma pessoa com este cpf"
        }
        pessoaList.add(pessoa)
        return "Pessoa salva com sucesso"
    }

    override fun getByIdentifier(identifier: String): Pessoa {
        val pessoaFound = pessoaList.find { it.cpf == identifier }
        if (pessoaFound == null) {
            throw PessoaNotFoundException("Pessoa não encontrada")
        }
        return pessoaFound
    }

    override fun update(identifier: String, fields: MutableMap<String, String>): Boolean {
        try {
            var pessoaFound = getByIdentifier(identifier)
            pessoaFound.nome = fields["nome"].toString()
            pessoaFound.endereco = fields["endereco"].toString()
            pessoaFound.idade = fields["idade"].toString().toInt()
            return true
        } catch (exception: Exception) {
            exception.printStackTrace()
            return false
        }
    }

    override fun getAll(): List<Pessoa> {
        return pessoaList.toList()
    }

    override fun deleteByIdentifier(identifier: String): String {
        try {
            pessoaList.remove(getByIdentifier(identifier))
            return "Pessoa removida com sucesso"
        } catch (exception: Exception) {
            exception.printStackTrace()
            return exception.message.toString()
        }
    }
}