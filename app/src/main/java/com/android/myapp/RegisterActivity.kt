package com.android.myapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        configureSignUpButton()
    }

    private fun configureSignUpButton() {

        val signUpButton : Button = findViewById(R.id.sign_up_button)
        signUpButton.setOnClickListener { register() ; finish()}
    }
    private fun register(){

        val name : EditText = findViewById(R.id.reg_name_text)
        val email : EditText = findViewById(R.id.reg_email_text)
        val password : EditText = findViewById(R.id.reg_password_text)
        val confirm : EditText = findViewById(R.id.reg_confirm_text)
        val telephone : EditText = findViewById(R.id.reg_tel_text)
        val errorMsg = ""

        if(name.text.toString().trim().isEmpty()) {
            errorMsg.plus("Invalid name! \n")
            //Toast.makeText(applicationContext, "Invalid name !", Toast.LENGTH_SHORT).show()
        }

        if(!isEmailValid(email.text.toString())) {
            errorMsg.plus("Invalid email address ! \n")
            //Toast.makeText(applicationContext, "Invalid email address !", Toast.LENGTH_SHORT).show()
        }
        if(password.text.toString().trim().isEmpty()){
            errorMsg.plus("Invalid password ! \n")
        }
        else{
            if(confirm.text.toString().trim().isEmpty() or (password.text == confirm.text))
                errorMsg.plus("Confirm password doesn't match! \n")
        }
        if(telephone.text.toString().trim().isEmpty() or (telephone.text.toString().length < 10 )){
            errorMsg.plus("Invalid phone number!")
        }

        if(errorMsg.isEmpty()){
            sendEmail(email.text.toString())
            //create account
            //redirect to Login Page
        }
        else{
            Toast.makeText(applicationContext, errorMsg, Toast.LENGTH_SHORT).show()

        }
    }

    private fun isEmailValid(email: CharSequence): Boolean {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendEmail(email : String ) {
        val subject = "Welcome to Gourmanje' !"
        val text = "Thank you for creating an account on our application," +
                " now you will be kept in touch with" +
                "all new offers and events! "

        Log.i("Send email", "")
        val TO = arrayOf(email)
        //val CC = arrayOf("serbysinge@gmail.com")
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, text)
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            finish()
            //Log.i(".", "")
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this@RegisterActivity,
                "There is no email client installed.", Toast.LENGTH_SHORT
            ).show()
        }
    }
}
