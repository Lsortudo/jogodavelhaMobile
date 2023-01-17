package com.example.jogodavelhamobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jogodavelhamobile.presentation.TicTacToeField
import com.example.jogodavelhamobile.presentation.TicTacToeViewModel
import com.example.jogodavelhamobile.ui.theme.JogodavelhaMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JogodavelhaMobileTheme {
                val viewModel = hiltViewModel<TicTacToeViewModel>()
                val state by viewModel.state.collectAsState()
                val isConnecting by viewModel.isConnecting.collectAsState()
                val showConnectionError by viewModel.showConnectionError.collectAsState()

                if (showConnectionError) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Couldn't connect to the server my bestu friendu",
                        color = MaterialTheme.colors.error)
                    }
                    return@JogodavelhaMobileTheme
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column (
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .align(Alignment.TopCenter)
                    ) {
                        if(!state.connectedPlayers.contains('X')) {
                            Text(
                                text= "Waiting for player X",
                                fontSize = 32.sp
                            )
                        } else if (!state.connectedPlayers.contains('O')) {
                            Text(
                                text= "Waiting for player O",
                                fontSize = 32.sp
                            )
                        }
                    }
                    TicTacToeField(state = state, onTapInField = viewModel::finishTurn, modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(16.dp))
                }



            }
        }
    }
}