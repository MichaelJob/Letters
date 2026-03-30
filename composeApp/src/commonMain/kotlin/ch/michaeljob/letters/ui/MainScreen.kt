package ch.michaeljob.letters.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.michaeljob.letters.LetterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: LetterViewModel) {
    with(viewModel) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            if (viewModel.isNumbers) "Numbers" else "Letters",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                letterSpacing = 4.sp
                            )
                        )
                    },
                    actions = {
                        IconButton(onClick = { viewModel.reset() }) {
                            Icon(
                                imageVector = Icons.Outlined.Restore,
                                contentDescription = "reset picked letters",
                                tint = MaterialTheme.colorScheme.tertiary,
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                    )
                )
            },
            bottomBar = {
                NavigationBar {
                    HistoryBar(history = history)
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Switch(
                    checked = viewModel.isNumbers,
                    enabled = viewModel.history.isEmpty(),
                    colors = SwitchDefaults.colors(
                        checkedBorderColor = MaterialTheme.colorScheme.primary,
                        uncheckedBorderColor = MaterialTheme.colorScheme.primary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    onCheckedChange = { viewModel.setIsNumbers(it) },
                    thumbContent = if (viewModel.isNumbers) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Numbers,
                                contentDescription = "Numbers",
                            )
                        }
                    } else {
                        {
                            Icon(
                                imageVector = Icons.Filled.Abc,
                                contentDescription = "Letters",
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(0.1f))

                if (isNumbers){
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Text("Dice")
                        Checkbox(
                            checked = isDice,
                            onCheckedChange = { isDice = it },
                            enabled = viewModel.history.isEmpty(),
                        )
                    }
                 }
                Spacer(modifier = Modifier.weight(0.1f))
                if (isDice && isNumbers){
                   DiceRoller(
                           currentDice = { viewModel.currentDice },
                           onAnimationStart = { viewModel.rollTheDice() },
                           onAnimationEnd = { viewModel.onDiceSelected()},
                    )
                } else {
                    AlphabetWheel(
                        allLetters = if (viewModel.isNumbers) viewModel.allNumbers.value else viewModel.allLetters.value,
                        remainingLetters = remainingLetters,
                        onLetterSelected = { viewModel.pickRandomLetter(it) }
                    )
                }

                Text(
                    text = if (true) "tap to spin" else "",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.weight(0.2f))

                Box(
                    modifier = Modifier.height(140.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val letter = currentLetter
                    if (letter != "") {
                        Text(
                            text = letter,
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 120.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    } else {
                        Text(
                            text = if (remainingLetters.isEmpty()) "FIN" else "",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryBar(history: List<String>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "picked since:",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(history.reversed()) { letter ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = letter,
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}
