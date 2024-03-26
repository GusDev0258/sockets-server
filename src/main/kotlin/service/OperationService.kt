package udesc.eso.service

import udesc.eso.factory.StrategyFactory
import udesc.eso.strategy.GuildaStrategy
import udesc.eso.strategy.OperationStrategyProtocol

class OperationService {
    fun executeOperation(entity: String?, operation: String, fields: MutableMap<String, String>): Any {
        val strategy = StrategyFactory.makeStrategy(entity) ?: return "$entity não encontrada"

        return when (operation.lowercase()) {
            "insert" -> strategy.insert(fields)
            "get" -> strategy.getOperation(fields)
            "update" -> strategy.update(fields)
            "delete" -> strategy.delete(fields)
            "list" -> strategy.list()
            else -> checkIfItIsGuild(entity, strategy, operation, fields)
        }
    }

    private fun checkIfItIsGuild(
        entity: String?,
        strategy: OperationStrategyProtocol,
        operation: String,
        fields: MutableMap<String, String>
    ): Any {
        if (entity.equals("Guilda")) {
            return handleGuildOperation(strategy as? GuildaStrategy, operation, fields)
        }
        return "Operação não suportada ainda"
    }

    private fun handleGuildOperation(
        strategy: GuildaStrategy?,
        operation: String,
        fields: MutableMap<String, String>
    ): Any {
        if (strategy == null) return "Estratégia para Guilda não encontrada"

        return when (operation.lowercase()) {
            "addmember" -> strategy.addGuildMember(fields)
            "deletemember" -> strategy.removeGuildMember(fields)
            "getmember" -> strategy.getGuildMember(fields)
            "listmember" -> strategy.listGuildMembers(fields)
            else -> Unit
        }
    }
}