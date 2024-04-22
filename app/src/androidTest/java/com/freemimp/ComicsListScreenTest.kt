package com.freemimp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@MediumTest
class ComicsListScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun givenStateIsSuccessThenShowListOfComics() {
        (1..5).forEach {
            composeTestRule.onNodeWithTag("${LIST_ITEM_TAG}_$it").assertIsDisplayed()
        }
    }

    @Test
    fun givenComicsListDisplayedWhenClickingOnItemThenDetailsImageIsShown() {
        composeTestRule.onNodeWithTag("${LIST_ITEM_TAG}_4").performClick()

        composeTestRule.onNodeWithTag(IMAGE_ITEM_TAG).assertIsDisplayed()
    }
}

private const val LIST_ITEM_TAG = "listItemTag"
private const val IMAGE_ITEM_TAG = "imageItemTag"
