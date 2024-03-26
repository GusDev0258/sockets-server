package udesc.eso.model

class Mago(cpf: String, nome: String, endereco: String, idade: Int, magia: String, elemento: String, chapeu: Boolean) :
    Pessoa(
        cpf = cpf,
        nome = nome,
        endereco = endereco,
        idade = idade
    ) {
    var magia: String = magia
    var elemento: String = elemento
    var chapeu: Boolean = chapeu

    override fun toString(): String {
        super.toString()
        val hasChapeu = if (chapeu) "Tem chapéu" else "Sem chapéu"
        return "$cpf;$nome;$endereco;$idade;$magia;$elemento;${hasChapeu}."
    }
}