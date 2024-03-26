package udesc.eso.model

open class Pessoa(val cpf: String, var nome: String, var endereco: String, var idade: Int) {
    override fun toString(): String {
        return "${cpf};${nome};${endereco}."
    }
}
