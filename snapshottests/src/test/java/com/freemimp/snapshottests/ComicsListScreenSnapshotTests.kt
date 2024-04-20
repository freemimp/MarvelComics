package com.freemimp.snapshottests

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.freemimp.main.domain.model.Comic
import com.freemimp.main.ui.ComicsContent
import com.freemimp.main.ui.ComicsListState
import org.junit.Rule
import org.junit.Test

class ComicsListScreenSnapshotTests {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        renderingMode = SessionParams.RenderingMode.NORMAL,
        showSystemUi = false,
        maxPercentDifference = 1.0,
    )

    @Test
    fun GivenLoadingStateThenShowLoadingScreen() {
        paparazzi.snapshot {
            ComicsContent(ComicsListState.Loading, {})
        }
    }

    @Test
    fun GivenErrorStateThenShowErrorScreen() {
        paparazzi.snapshot {
            ComicsContent(ComicsListState.Error(message = "Oops, something went wrong"), {})
        }
    }

    @Test
    fun GivenSuccessStateThenShowListScreen() {

        paparazzi.snapshot {
            ComicsContent(ComicsListState.Success(generateComisc()), {})
        }
    }

    private fun generateComisc(): List<Comic> {
        return (1..20).map {
            Comic(
                id = "$it",
                title = "Comic nr $it",
                thumbnailUrl = "",
                fullImageUrl = ""
            )
        }
    }
}
