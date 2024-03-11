import java.util.UUID

class NodeState(id: UUID, name: String) {
    var id: UUID = id
        private set

    var name: String = name
        private set

    var socketStates: List<NodeSocketState> = emptyList()
        private set

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
}