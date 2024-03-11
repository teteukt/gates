package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entity.NodeSocketState

@Composable
fun NodeSocket(nodeSocketState: NodeSocketState, onClick: ((NodeSocketState) -> Unit)?) {
    when (nodeSocketState) {
        is NodeSocketState.Input -> Input(nodeSocketState, onClick)
        is NodeSocketState.Output -> Output(nodeSocketState, onClick)
    }
}

@Composable
private fun Output(output: NodeSocketState.Output, onClick: ((NodeSocketState.Output) -> Unit)?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = output.name)
        Socket {
            onClick?.invoke(output)
        }
    }
}

@Composable
private fun Input(input: NodeSocketState.Input, onClick: ((NodeSocketState.Input) -> Unit)?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Socket {
            onClick?.invoke(input)
        }
        Text(text = input.name)
    }
}

@Composable
private fun Socket(onClick: (() -> Unit)? = null) {
    Box(modifier = Modifier.size(8.dp).background(color = MaterialTheme.colors.primary, shape = CircleShape).clickable {
        onClick?.invoke()
    })
}