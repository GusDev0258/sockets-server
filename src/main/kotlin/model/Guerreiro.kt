package udesc.eso.model

class Guerreiro(cpf: String, nome: String, endereco: String, idade: Int, arma: String, escudo: Boolean) :
    Pessoa(cpf, nome, endereco, idade) {
    var arma = arma
    var escudo = escudo

    override fun toString(): String {
        super.toString()
        val hasEscudo = if (escudo) "Tem escudo" else "Sem escudo"
        return "$cpf;$nome;$endereco;$idade;$arma;${hasEscudo}."
    }
}