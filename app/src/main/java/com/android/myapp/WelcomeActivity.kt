package com.android.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val menuButton : Button = findViewById(R.id.menuBtn)
        val detailsButton : Button = findViewById(R.id.detailsBtn)
        val signoutButton : Button = findViewById(R.id.signoutBtn)

        menuButton.setOnClickListener{
            menuButton.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fadein))
            startActivity(Intent(this,MainMenuActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }

        detailsButton.setOnClickListener{
            detailsButton.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fadein))
            startActivity(Intent(this,Details::class.java))
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
        }

        signoutButton.setOnClickListener{
            signoutButton.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fadein))
            finish()
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
        }
    }

}
