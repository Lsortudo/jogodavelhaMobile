package com.example.jogodavelhamobile.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jogodavelhamobile.data.GameState

//USANDO CANVAS por liberdade de criar shapes, linhas e etc... Envolve bastante matemática

@Composable
fun TicTacToeField(

    state: GameState,
    modifier: Modifier = Modifier,
    playerXColor: Color = Color.Green,
    playerOColor: Color = Color.Red,
    onTapInField: (x: Int, y: Int) -> Unit

) {

    Canvas(modifier = modifier) {
        drawField()
    }

}

private fun DrawScope.drawField() {
    //First vertical line
    drawLine(
        color = Color.Black,
        start = Offset(
            x = size.width * (1 / 3f),
            y = 0f
        ),
        end = Offset(
            x = size.width * (1 / 3f),
            y = size.height
        ),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )

}

@Preview(showBackground = true)
@Composable
fun TicTacToeFieldPreview() {

    TicTacToeField(state = GameState(
        field = arrayOf(
            arrayOf('X', null, null),
            arrayOf(null, 'O', 'O'),
            arrayOf(null, null, null),
        ),
    ), onTapInField = { _, _ ->},
    modifier = Modifier.size(300.dp))
    
}