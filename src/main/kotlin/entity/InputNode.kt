package entity

import java.util.UUID

class InputNode(id: UUID): NodeState(id, "Input") {
    init {
        addOutput("A")
    }
}