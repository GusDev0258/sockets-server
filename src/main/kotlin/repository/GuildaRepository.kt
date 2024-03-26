package udesc.eso.repository

import udesc.eso.exception.GuildNotFoundException
import udesc.eso.model.Guilda
import udesc.eso.model.Pessoa

object GuildaRepository : BasicRepositoryProtocol {
    private var guildList = mutableListOf<Guilda>()


    override fun save(fields: MutableMap<String, String>): String {
        val guilda = Guilda(
            nome = fields["nome"].toString(),
            cidade = fields["cidade"].toString(),
            fundos = fields["fundos"].toString().toDouble()
        )
        if (guildList.any { it.nome === guilda.nome }) {
            return "Já existe uma guilda com este nome"
        }
        guildList.add(Guilda(guilda.nome, guilda.cidade, guilda.fundos))
        return "Guilda salva com sucesso"
    }

    override fun getByIdentifier(identifier: String): Guilda {
        val guildFound = guildList.find { it.nome == identifier }
        if (guildFound == null) {
            throw GuildNotFoundException()
        }
        return guildFound
    }

    override fun update(identifier: String, fields: MutableMap<String, String>): Boolean {
        try {
            val guildaFound = getByIdentifier(identifier)
            guildaFound.fundos = fields["fundos"].toString().toDouble()
            return true
        } catch (exception: Exception) {
            exception.printStackTrace()
            return false
        }
    }

    override fun getAll(): List<Guilda> {
        return guildList.toList()
    }

    override fun deleteByIdentifier(identifier: String): String {
        var guildFound = guildList.find { it.nome == identifier }
        if (guildFound == null) {
            throw GuildNotFoundException("Guilda não encontrada")
        }
        guildList.remove(guildFound)
        return "Guilda removida com sucesso"
    }

    fun addMember(guildName: String, member: Pessoa): String {
        val guild = getByIdentifier(guildName)
        val memberAlreadyInGuild = guild.checkIfMemberInGuild(member.cpf)
        if (!memberAlreadyInGuild) {
            guild.addMember(member)
            return "Membro adicionado com sucesso"
        }
        return "Não foi possível adicionar o membro"
    }

    fun listMembers(guildName: String): List<Pessoa> {
        val guild = getByIdentifier(guildName);
        return guild.getAllMembers()
    }

    fun deleteMember(guildName: String, memberCPF: String): String {
        val guild = getByIdentifier(guildName)
        return guild.removeMemberByCpf(memberCPF)
    }

    fun getMember(guildName: String, memberCPF: String): Any {
        val guild = getByIdentifier(guildName)
        try {
            val member = guild.getMemberByCpf(memberCPF)
            return member
        } catch (exception: Exception) {
            exception.printStackTrace()
            return exception.message.toString()
        }
    }

}