package udesc.eso.command

import udesc.eso.service.OperationService
import java.io.PrintWriter

class GetCommand(
    private val operationService: OperationService,
    private val entityOperation: String?,
    private val fieldsOperation: MutableMap<String, String>
) : CommandProtocol {
    override fun execute(output: PrintWriter) {
        val operationResponse =
            operationService.executeOperation(operation = "get", entity = entityOperation, fields = fieldsOperation)
        output.println(operationResponse)
    }
}