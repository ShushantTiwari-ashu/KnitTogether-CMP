import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dev.shushant.knit_together.MainView
import dev.shushant.knit_together.di.initKoin
import dev.shushant.utils.dimens.DeviceConfiguration
import moe.tlaster.precompose.PreComposeWindow
import java.awt.Dimension
import java.awt.Toolkit

private val koin = initKoin().koin

fun main() = application {
    val configuration = Toolkit.getDefaultToolkit().screenSize
    PreComposeWindow(
        onCloseRequest = ::exitApplication,
        title = ("KnitTogether"),
        state = WindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            size = getPreferredWindowSize(1000, 1000)
        ), icon = painterResource("appicon.ico")
    ) {
        MainView(
            DeviceConfiguration(
                screenWidthDp = configuration.width,
                screenHeight = configuration.height
            )
        )
    }
}


fun getPreferredWindowSize(desiredWidth: Int, desiredHeight: Int): DpSize {
    val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize
    val preferredWidth: Int = (screenSize.width * 0.8f).toInt()
    val preferredHeight: Int = (screenSize.height * 0.8f).toInt()
    val width: Int = if (desiredWidth < preferredWidth) desiredWidth else preferredWidth
    val height: Int = if (desiredHeight < preferredHeight) desiredHeight else preferredHeight
    return DpSize(width.dp, height.dp)
}