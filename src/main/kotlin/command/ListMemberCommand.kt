package udesc.eso.command

import udesc.eso.service.OperationService
import java.io.PrintWriter

class ListMemberCommand(
    private val operationService: OperationService,
    private val entityOperation: String?,
    private val fieldsOperation: MutableMap<String, String>
) : CommandProtocol {
    override fun execute(output: PrintWriter) {
        val operationResponse = operationService.executeOperation(
            entity = entityOperation,
            operation = "listmember",
            fields = fieldsOperation
        )
        output.println(operationResponse)
    }
}
