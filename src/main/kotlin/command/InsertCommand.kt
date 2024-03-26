package udesc.eso.command

import udesc.eso.service.OperationService
import java.io.PrintWriter

class InsertCommand(
    private val operationService: OperationService,
    private val entityOperation: String?,
    private val fieldsOperation: MutableMap<String, String>
) :
    CommandProtocol {
    override fun execute(output: PrintWriter) {
        operationService.executeOperation(operation = "insert", entity = entityOperation, fields = fieldsOperation)
    }
}