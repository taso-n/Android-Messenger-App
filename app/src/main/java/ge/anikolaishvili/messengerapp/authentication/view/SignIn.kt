package ge.anikolaishvili.messengerapp.authentication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ge.anikolaishvili.messengerapp.databinding.SignInBinding
import ge.anikolaishvili.messengerapp.MainActivity
import ge.anikolaishvili.messengerapp.authentication.presenter.ISignInPresenter
import ge.anikolaishvili.messengerapp.authentication.presenter.SingInPresenter

class SignIn : AppCompatActivity(), IAuthView {

    private val presenter: ISignInPresenter = SingInPresenter(this)
    private lateinit var binding: SignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (presenter.isAuthed()) {
            success()
        }
        binding.signIn.setOnClickListener {
            val email = binding.signInEmail.text.toString()
            val pass = binding.signInPass.text.toString()

            presenter.checkUser(email, pass)
        }

        binding.signInUp.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
    }

    // go to home page on successful login
    override fun success() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    // notify user in case of failed authorisation
    override fun failed() {
        Toast.makeText(this, "Couldn't log you in", Toast.LENGTH_LONG).show()
    }

}