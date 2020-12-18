package com.kotlinapp.gebeya.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.Toast
import com.kotlinapp.gebeya.R
import com.kotlinapp.gebeya.model.CallProgressDialog
import com.kotlinapp.sendmail.GMailSender
import kotlinx.android.synthetic.main.activity_email_forgot.*

class EmailForgotActivity : AppCompatActivity() {

    lateinit var progress: CallProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_forgot)

        progress = CallProgressDialog()

        button_agree2.setOnClickListener {
            progress.showProgress(this@EmailForgotActivity,
                R.style.AppCompatAlertDialogStyle, "Sending email",
                "Please wait...")
            var email = editText_email2.text.toString()
            if (TextUtils.isEmpty(email)) {
                progress.dismissProgress()
                editText_email2.setError("Please fill this field !")
            } else {
                sendMailToUser(email)
            }
        }

        setToolbar()
    }

    private fun sendMailToUser(email: String) {
        // Send email setup new password to user
        var textBody = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title>Index 2</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<h2>Hi, User</h2>\n" +
                "\t<p>We have received a password change request from your account. Click on the link below to set a new password.</p>\n" +
                "\t<a href=\"https://newwgenn.com/user/formChangePassword.php?email="+email+"\">" +
                "Setup a new password</a>\n" +
                "</body>\n" +
                "</html>"

        var sender = GMailSender("GebeyaKotlin@gmail.com", "@Gebeya1231")
        sender.sendMail("[Gebeya App] Mail setup new password", textBody,"GebeyaKotlin@gmail.com",
            email)
        if (sender != null) {
            // When success
            val handler = Handler()
            handler.postDelayed(Runnable {
                progress.dismissProgress()
                Toast.makeText(this@EmailForgotActivity, "Mail has been sent.", Toast.LENGTH_SHORT).show()
            }, 3000)
        }
    }

    private fun setToolbar() {
        setSupportActionBar(toolBar_email_forgot)
        toolBar_email_forgot.setNavigationIcon(R.drawable.ic_arrow_back)
        toolBar_email_forgot.setNavigationOnClickListener {
            finish()
        }
    }
}
