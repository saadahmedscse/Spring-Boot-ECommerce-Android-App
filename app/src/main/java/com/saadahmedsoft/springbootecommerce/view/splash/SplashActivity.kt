package com.saadahmedsoft.springbootecommerce.view.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.saadahmedsoft.springbootecommerce.R
import com.saadahmedsoft.springbootecommerce.utils.SessionManager
import com.saadahmedsoft.springbootecommerce.view.auth.AuthActivity
import com.saadahmedsoft.springbootecommerce.view.dashboard.DashboardActivity
import com.saadahmedsoft.base.helper.delay
import com.saadahmedsoft.shortintent.ShortIntent

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_splash)

        delay(2000) {
            ShortIntent.getInstance(this)
                .addDestination(
                    if (SessionManager.getInstance(this).bearerToken != null) DashboardActivity::class.java
                    else AuthActivity::class.java
                )
                .finish(this)
        }
    }
}