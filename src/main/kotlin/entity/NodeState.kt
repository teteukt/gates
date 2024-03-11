package entity

import androidx.compose.ui.geometry.Offset
import generateUuid
import java.util.UUID

abstract class NodeState(id: UUID, name: String) {

    var position: Offset = Offset.Zero
        private set
    var id: UUID = id
        private set

    var name: String = name
        private set

    var socketStates: List<NodeSocketState> = emptyList()
        private set

    fun setPosition(offset: Offset) {
        position = offset
    }

    fun addInput(name: String): NodeSocketState.Input {
        val instance = NodeSocketState.Input(id = generateUuid(), name = name, node = this)
        socketStates = socketStates + listOf(instance)
        return instance
    }

    fun addOutput(name: String): NodeSocketState.Output {
        val instance = NodeSocketState.Output(id = generateUuid(), name = name, node = this)
        socketStates = socketStates + listOf(instance)
        return instance
    }

    fun getInputs(): List<NodeSocketState.Input> = socketStates.filterIsInstance<NodeSocketState.Input>()
    fun getOutputs(): List<NodeSocketState.Output> = socketStates.filterIsInstance<NodeSocketState.Output>()
}