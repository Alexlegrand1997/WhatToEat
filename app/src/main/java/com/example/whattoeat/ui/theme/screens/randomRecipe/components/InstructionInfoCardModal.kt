package com.example.whattoeat.ui.theme.screens.randomRecipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.whattoeat.R
import com.example.whattoeat.models.Step


@Composable
fun InstructionInfoCardModal(
    onDismissRequest: () -> Unit, steps: List<Step>, currentRecipeInfo: MutableState<Boolean>
) {
    Dialog(onDismissRequest = { onDismissRequest() }

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            shape = RoundedCornerShape(2.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                horizontalArrangement = Arrangement.Center,
            ) {
                if (steps.isNotEmpty()) {
                    LazyColumn(
                        Modifier.fillMaxHeight(0.9f),
                    ) {
                        items(steps) { step ->
                            Row(
                                Modifier.padding(top = 8.dp, end = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    Modifier.fillMaxWidth(0.08f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(step.number.toString())
                                }
                                StepInstructionCard(step)
                            }
                        }
                    }
                } else {
                    Text(stringResource(R.string.no_instruction))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End),
                horizontalArrangement = Arrangement.Center,

                ) {
                OutlinedButton(
                    onClick = { onDismissRequest(); currentRecipeInfo.value = false },
                    modifier = Modifier.padding(8.dp),
                ) {

                    Text(
                        stringResource(R.string.close), style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }


            }
        }
    }

}

@Composable
fun StepInstructionCard(step: Step) {
    Card(
        Modifier
            .fillMaxWidth(0.95f)
            .padding(4.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Text(text = step.step, Modifier.padding(4.dp))
    }
}
