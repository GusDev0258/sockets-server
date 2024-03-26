package udesc.eso.command

import java.io.PrintWriter

interface CommandProtocol {
    fun execute(output: PrintWriter)
}