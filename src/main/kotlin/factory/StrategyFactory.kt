package udesc.eso.factory

import udesc.eso.strategy.*

class StrategyFactory {
    companion object {
        fun makeStrategy(entity: String?): OperationStrategyProtocol? {
            return when (entity) {
                "Pessoa" -> PessoaStrategy()
                "Guilda" -> GuildaStrategy()
                "Guerreiro" -> GuerreiroStrategy()
                "Mago" -> MagoStrategy()
                else -> null
            }
        }
    }
}