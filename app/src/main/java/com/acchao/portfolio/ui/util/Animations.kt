package com.acchao.portfolio.ui.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * My attempt at solving for the lack of View.Invisible problem. While we could animate the alpha,
 * perhaps the user wants to include specific transition animations.
 * Currently doesn't handle clickable objects in the hidden view; May want to consider a measured
 * layout approach for a later date.
 */
@Composable
fun ColumnScope.AnimatedInvisibility(
    visible: Boolean,
    enter: EnterTransition = fadeIn() + expandVertically(),
    exit: ExitTransition = fadeOut() + shrinkVertically(),
    label: String = "AnimatedInvisibility",
    modifier: Modifier = Modifier,
    content: @Composable (modifier: Modifier) -> Unit
) {
    Box(modifier) {
        this@AnimatedInvisibility.AnimatedVisibility(
            visible = visible,
            enter = enter,
            exit = exit,
            label = label
        ) {
            content(modifier = Modifier)
        }
        // Note(Andrew): doesn't work well if you have clickable objects underneath
        content(modifier = Modifier.graphicsLayer { alpha = 0f })
    }
}
