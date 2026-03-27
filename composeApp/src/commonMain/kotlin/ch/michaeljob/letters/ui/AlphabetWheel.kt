package ch.michaeljob.letters.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlin.math.PI

@Composable
fun AlphabetWheel(
    allLetters: List<String> = ('A'..'Z').map { it.toString() },
    remainingLetters: List<String>,
    onLetterSelected: () -> Unit,
    isSpinning: Boolean,
    setSpinning: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val colorScheme = MaterialTheme.colorScheme
    val textMeasurer = rememberTextMeasurer()
    val anglePerLetter = 360f / allLetters.size

    Box(
        modifier = modifier
            .size(300.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (!isSpinning && remainingLetters.isNotEmpty()) {
                    setSpinning(true)
                    val targetLetter = remainingLetters.first()
                    val targetIndex = allLetters.indexOf(targetLetter)
                    
                    val currentRotation = rotation.value
                    val extraSpins = 5
                    val targetAngle = (extraSpins * 360f) - (targetIndex * anglePerLetter) - (currentRotation % 360f)
                    
                    scope.launch {
                        rotation.animateTo(
                            targetValue = currentRotation + targetAngle,
                            animationSpec = tween(
                                durationMillis = 3000,
                                easing = LinearOutSlowInEasing
                            )
                        )
                        setSpinning(false)
                        onLetterSelected()
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.minDimension / 2
            
            drawCircle(
                color = colorScheme.secondaryContainer,
                radius = radius,
                center = center
            )

            rotate(rotation.value, pivot = center) {
                allLetters.forEachIndexed { index, letter ->
                    val angle = index * anglePerLetter
                    val angleRad = (angle - 90.0) * (PI / 180.0)
                    
                    val x = center.x + (radius * 0.8f) * cos(angleRad).toFloat()
                    val y = center.y + (radius * 0.8f) * sin(angleRad).toFloat()
                    
                    rotate(degrees = angle, pivot = Offset(x, y)) {
                        val textLayoutResult = textMeasurer.measure(
                            text = letter,
                            style = TextStyle(
                                color = if (remainingLetters.contains(letter)) {
                                    colorScheme.onSecondaryContainer
                                } else {
                                    colorScheme.onPrimary
                                },
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                        
                        drawText(
                            textLayoutResult = textLayoutResult,
                            topLeft = Offset(
                                x - textLayoutResult.size.width / 2,
                                y - textLayoutResult.size.height / 2
                            )
                        )
                    }
                }
            }

            drawPath(
                color = colorScheme.primary,
                path = Path().apply {
                    moveTo(center.x, center.y - radius + 30)
                    lineTo(center.x - 20, center.y - radius)
                    lineTo(center.x + 20 , center.y - radius)
                }
            )
        }
        
        if (!isSpinning) {
            Text(
                text = if (remainingLetters.isEmpty()) "" else "tap to spin",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
