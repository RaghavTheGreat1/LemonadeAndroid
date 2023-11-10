package com.rtg1.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.rtg1.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun LemonadeApp(modifier: Modifier = Modifier) {

    var stepNumber by remember {
        mutableStateOf(1)
    }

    var instruction = when (stepNumber) {
        1 -> LemonInstruction(
            instruction = stringResource(R.string.lemon_tree),
            image = R.drawable.lemon_tree
        )

        2 -> LemonInstruction(
            instruction = stringResource(R.string.lemon_squeeze),
            image = R.drawable.lemon_squeeze
        )

        3 -> LemonInstruction(
            instruction = stringResource(R.string.lemon_drink),
            image = R.drawable.lemon_drink
        )

        else -> LemonInstruction(
            instruction = stringResource(R.string.lemon_restart),
            image = R.drawable.lemon_restart
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lemonade", textAlign = TextAlign.Center)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFFFC436))
            )
        }
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = instruction.image),
                modifier = modifier.clickable {
                    if (stepNumber == 4) {
                        stepNumber = 1
                    } else {
                        stepNumber++
                    }
                },
                contentDescription = "Image instructing to ${instruction.instruction}",
            )
            Text(text = instruction.instruction)
        }
    }
}

data class LemonInstruction(val instruction: String, val image: Int) {

}