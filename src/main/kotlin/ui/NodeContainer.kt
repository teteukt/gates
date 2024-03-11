package ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import entity.InputNode
import entity.NodeSocketState
import entity.NodeState
import generateUuid

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NodeContainer() {
    var lastOutputSelected by remember { mutableStateOf<NodeSocketState.Output?>(null) }
    var nodes by remember { mutableStateOf(emptyList<NodeState>()) }

    fun handleOutputClick(output: NodeSocketState.Output) {
        lastOutputSelected = output
    }

    fun handleInputClick(input: NodeSocketState.Input) {
        val immutableLastOutputSelected = lastOutputSelected
        if(immutableLastOutputSelected != null) {
            input.connectedFrom = lastOutputSelected
            immutableLastOutputSelected.connectedTo = input
            lastOutputSelected = null
        }
    }

    fun handleSocketClick(socketState: NodeSocketState) {
        when (socketState) {
            is NodeSocketState.Input -> handleInputClick(socketState)
            is NodeSocketState.Output -> handleOutputClick(socketState)
        }
    }

    fun handleClick(position: Offset) {
        val inputNode = InputNode(generateUuid())
        inputNode.setPosition(position)
        nodes = nodes + listOf(inputNode)
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(color = Color.Black, start = Offset.Zero, end = Offset.Zero)
    }

    Box(modifier = Modifier.fillMaxSize().onPointerEvent(PointerEventType.Press) {
        it.changes.getOrNull(0)?.let {
            handleClick(it.position / 2F)
        }
    }) {
        nodes.map {
            Node(it) {
                handleSocketClick(it)
            }
        }
    }
}