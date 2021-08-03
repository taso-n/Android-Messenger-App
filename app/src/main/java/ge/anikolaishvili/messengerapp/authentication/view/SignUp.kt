package ge.anikolaishvili.messengerapp.authentication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ge.anikolaishvili.messengerapp.databinding.SignUpBinding
import ge.anikolaishvili.messengerapp.datamodel.UserModel
import ge.anikolaishvili.messengerapp.MainActivity
import ge.anikolaishvili.messengerapp.authentication.presenter.ISignInPresenter
import ge.anikolaishvili.messengerapp.authentication.presenter.SingInPresenter

class  SignUp : AppCompatActivity(), IAuthView {
    private lateinit var binding: SignUpBinding
    private val presenter: ISignInPresenter = SingInPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        testWorking()
        binding.signUpUp.setOnClickListener {
            val email = binding.signUpEmail.text.toString()
            val profession = binding.signUpProfession.text.toString()
            val pass = binding.signUpPass.text.toString()

            val user = UserModel(
                email = email,
                profession = profession
            )

            presenter.initUser(user, pass)
        }

    }

//    private fun testWorking() {
//        print("function  working")
//    }

    // go to home page on successful sign up
    override fun success() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    // notify user in case of failed sign up
    override fun failed() {
        Toast.makeText(this, "Registration failed!!", Toast.LENGTH_LONG).show()
    }
}