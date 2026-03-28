package ch.michaeljob.letters.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ch.michaeljob.letters.Dice
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun Dice(
    onDiceSelected: (Dice) -> Unit,
    isSpinning: Boolean,
    setSpinning: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    currentDice: Dice,
) {
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .size(300.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (!isSpinning) {
                    setSpinning(true)
                    val nextDice = Dice.entries.random()
                    val currentRotation = rotation.value
                    val extraSpins = Random(42).nextInt(2, 8)
                    val targetAngle = extraSpins * 360f

                    scope.launch {
                        onDiceSelected(nextDice)
                        rotation.animateTo(
                            targetValue = currentRotation + targetAngle,
                            animationSpec = tween(
                                durationMillis = 3000,
                                easing = LinearOutSlowInEasing
                            )
                        )
                        setSpinning(false)
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Cube(rotation, currentDice)
    }
}

@Composable
fun Cube(rotation: Animatable<Float, AnimationVector1D>, currentDice: Dice) {
    Box(modifier = Modifier.graphicsLayer { rotationZ = rotation.value }) {
        Image(
            painterResource(currentDice.drawable),
            contentDescription = currentDice.value.toString(),
        )

    }
}



