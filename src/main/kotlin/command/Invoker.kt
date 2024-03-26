package udesc.eso.command

import java.io.PrintWriter

class Invoker {
    fun executeCommand(command: CommandProtocol, output: PrintWriter) {
        command.execute(output)
    }
}