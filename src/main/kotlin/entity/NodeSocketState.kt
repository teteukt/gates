import java.util.*

sealed class NodeSocketState(
    private val id: UUID,
    private val name: String,
    private val node: NodeState
) {
    class Input(
        id: UUID,
        name: String,
        node: NodeState,
        var connectedFrom: Output? = null
    ) : NodeSocketState(id, name, node)

    class Output(
        id: UUID,
        name: String,
        node: NodeState,
        var connectedTo: Input? = null
    ) : NodeSocketState(id, name, node)
}