package udesc.eso.command

import udesc.eso.service.OperationService
import java.io.PrintWriter

class GetMemberCommand(
    private val operationService: OperationService,
    private val entityOperation: String?,
    private val fieldsOperation: MutableMap<String, String>
) : CommandProtocol {
    override fun execute(output: PrintWriter) {
        val operationResponse = operationService.executeOperation(
            entity = entityOperation,
            operation = "getmember",
            fields = fieldsOperation
        )
        output.println(operationResponse)
    }
}
