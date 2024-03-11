package entity

import java.util.UUID

class AndNode(id: UUID) : NodeState(id, "And") {
    init {
        addInput("A")
        addInput("B")
        addOutput("C")
    }
}