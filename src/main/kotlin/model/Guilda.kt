package udesc.eso.model

import udesc.eso.exception.MemberNotFoundException

data class Guilda(val nome: String, val cidade: String, var fundos: Double) {

    private val members = mutableListOf<Pessoa>()
    fun addMember(pessoa: Pessoa) {
        members.add(pessoa)
    }

    fun getMemberByCpf(cpf: String): Pessoa {
        var memberFound = members.find { it.cpf == cpf }
        if (memberFound !== null) {
            return memberFound
        } else throw MemberNotFoundException("Membro não encontrado")
    }

    fun checkIfMemberInGuild(cpf: String): Boolean {
        val member = members.find { it.cpf == cpf }
        if (member === null) {
            return false
        }
        return true
    }

    fun getAllMembers(): List<Pessoa> {
        return members.toList()
    }

    fun removeMemberByCpf(cpf: String): String {
        try {
            val member = getMemberByCpf(cpf)
            members.remove(member)
            return "Membro removido com sucesso"
        } catch (exception: Exception) {
            exception.printStackTrace()
            return exception.message.toString()
        }
    }

    override fun toString(): String {
        return "${nome};${cidade};${fundos}."
    }

}
