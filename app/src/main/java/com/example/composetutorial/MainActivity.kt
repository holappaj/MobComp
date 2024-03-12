package com.example.composetutorial

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.animation.doOnEnd
import com.example.composetutorial.database.UserRepository
import com.example.composetutorial.database.UserRoomDatabase
import com.example.composetutorial.database.UserViewModel
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainActivity : ComponentActivity() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var sensorListener: SensorListener
    private val CHANNEL_ID = "test_channel_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applicationScope = CoroutineScope(SupervisorJob())
        val database by lazy { UserRoomDatabase.getDatabase(this, applicationScope) }
        val repository by lazy { UserRepository(database.userDao())}
        val viewModel = UserViewModel(database.userDao())

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation.
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 1200L

            // Call SplashScreenView.remove at the end of your custom animation.
            slideUp.doOnEnd { splashScreenView.remove() }

            // Run your animation.
            slideUp.start()
        }

        setContent {
            ComposeTutorialTheme {
                notificationManager = NotificationManager(this)
                notificationManager.createNotificationChannel(CHANNEL_ID)
                sensorListener = SensorListener(this)
                sensorListener.startSensorListener()
                MyAppNavHost(viewModel=viewModel)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        notificationManager.createDefaultNotification(CHANNEL_ID)
    }

}