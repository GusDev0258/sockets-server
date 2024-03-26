package udesc.eso

import udesc.eso.command.*
import udesc.eso.service.MessageMapperService
import udesc.eso.service.OperationService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

fun main() {
    val serverPort = 3030
    val serverSocket = ServerSocket(serverPort)
    val operationService = OperationService()
    val invoker = Invoker()

    println("Servidor iniciado na porta: $serverPort")

    while (true) {
        val clientSocket = serverSocket.accept()
        println("Cliente conectado: ${clientSocket.inetAddress.hostAddress}")

        val input = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
        val output = PrintWriter(clientSocket.getOutputStream(), true)

        val clientMessage = input.readLine()
        val messageMapper = MessageMapperService(clientMessage)
        val messageFields = messageMapper.fields
        val messageEntity = messageMapper.entity
        val command = when (messageMapper.operation?.uppercase()) {
            "INSERT" -> InsertCommand(operationService, messageEntity, messageFields)
            "UPDATE" -> UpdateCommand(operationService, messageEntity, messageFields)
            "GET" -> GetCommand(operationService, messageEntity, messageFields)
            "DELETE" -> DeleteCommand(operationService, messageEntity, messageFields)
            "LIST" -> ListCommand(operationService, messageEntity, messageFields)
            "ADDMEMBER" -> AddMemberCommand(operationService, messageEntity, messageFields)
            "GETMEMBER" -> GetMemberCommand(operationService, messageEntity, messageFields)
            "DELETEMEMBER" -> DeleteMemberCommand(operationService, messageEntity, messageFields)
            "LISTMEMBER" -> ListMemberCommand(operationService, messageEntity, messageFields)
            else -> null
        }

        command?.let {
            invoker.executeCommand(it, output)
        } ?: output.println("Operação inválida")

        println("Mensagem do cliente: $clientMessage")
        clientSocket.close()
    }
}