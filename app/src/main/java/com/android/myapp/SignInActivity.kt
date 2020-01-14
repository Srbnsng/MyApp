package com.android.myapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.myapp.auth.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val signInButton : Button = findViewById(R.id.sign_in_button)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val intent = Intent(this,WelcomeActivity::class.java)
        viewModel.loginResult.observe(this, Observer {
            val loginResult = it ?: return@Observer
            if(loginResult is com.android.myapp.core.Result.Success){
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            }
            else if(loginResult is com.android.myapp.core.Result.Error){
                Toast.makeText(
                    applicationContext,
                    "Invalid credentials !",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        signInButton.setOnClickListener {
            signInButton.startAnimation(AnimationUtils.loadAnimation(this,R.anim.sample_anim))
            if(valid())
                viewModel.login(username.text.toString(), password.text.toString())
            username.setText("")
            password.setText("")
        }
    }

    private fun valid() : Boolean {

        val email : String  = username.text.toString()
        val passwordT : String = password.text.toString()

        if(!(isEmailValid(email))) {
            Toast.makeText(applicationContext, "Invalid email address !", Toast.LENGTH_SHORT).show()
            return false
        }
        if(passwordT.trim().isEmpty()){
            Toast.makeText(
                applicationContext,
                "Password field is empty !",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun isEmailValid(email: CharSequence): Boolean {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}
