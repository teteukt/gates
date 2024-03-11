package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import entity.NodeSocketState
import entity.NodeState

@Composable
fun Node(
    nodeState: NodeState,
    onPositionSocket: ((Offset) -> Unit)? = null,
    onClickSocket: ((NodeSocketState) -> Unit)?
) {
    Column(modifier = Modifier.offset(nodeState.position.x.dp, nodeState.position.y.dp)) {
        Text(nodeState.name)
        Row(modifier = Modifier.fillMaxWidth()) {
            SocketList(nodeState.getInputs(), onPositionSocket, onClickSocket)
            SocketList(nodeState.getOutputs(), onPositionSocket, onClickSocket)
        }
    }
}

@Composable
private fun <T : NodeSocketState> SocketList(
    inputs: List<T>,
    onPosition: ((Offset) -> Unit)? = null,
    onClickSocket: ((T) -> Unit)? = null
) {
    Column {
        inputs.map { inputState ->
            NodeSocket(inputState, onPosition) {
                onClickSocket?.invoke(inputState)
            }
        }
    }
}