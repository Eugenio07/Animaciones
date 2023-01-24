package com.example.animaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PassTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val ctx = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Login()
        }
    }

    @Test
    fun onlyUserFilledWontShowLoginButton(): Unit = with(composeTestRule) {
        onNodeWithText("User").performTextInput("user")

        onNodeWithText("LOGIN").assertDoesNotExist()
    }

    @Test
    fun userAndPassFilledShowsLoginButton(): Unit = with(composeTestRule) {
        onNodeWithText("User").performTextInput("user")
        onNodeWithText("Pass").performTextInput("pass")

        onNodeWithText("LOGIN").assertExists()
    }
    @Test
    fun revealIconShowsPassword(): Unit = with(composeTestRule) {

        onNodeWithTag(PASS_TEXT_FIELD_TEST_TAG).performTextInput("pass")
        onNodeWithTag(PASS_TEXT_FIELD_TEST_TAG).assertTextContains("••••")

        onNodeWithTag(PASS_REVEAL_ICON_TEST_TAG).performClick()

        onNodeWithTag(PASS_TEXT_FIELD_TEST_TAG).assertTextContains("pass")
        onNodeWithTag(PASS_REVEAL_ICON_TEST_TAG).assertExists()
    }
}