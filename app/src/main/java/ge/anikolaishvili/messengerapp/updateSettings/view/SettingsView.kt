package ge.anikolaishvili.messengerapp.updateSettings.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ImageButton
import android.widget.ImageView
import ge.anikolaishvili.messengerapp.authentication.view.SignIn
import ge.anikolaishvili.messengerapp.databinding.ProfileSettingsBinding
import ge.anikolaishvili.messengerapp.datamodel.UserModel
import ge.anikolaishvili.messengerapp.updateSettings.presenter.ISettingsPresenter
import ge.anikolaishvili.messengerapp.updateSettings.presenter.SettingsPresenter
import ge.anikolaishvili.messengerapp.R
import java.io.ByteArrayOutputStream
import java.io.IOException


class SettingsView : Fragment(), ISettingsView {

    private val presenter: ISettingsPresenter = SettingsPresenter(this)
    private var myFragment: ProfileSettingsBinding? = null
    private val PICK_IMAGE = 123

    override lateinit var avatar: ImageButton
    lateinit var avatarPath: Uri
    private var bitmap: Bitmap? = null

    private val binding get() = myFragment!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        myFragment = ProfileSettingsBinding.inflate(inflater, container, false)
        val myView = binding.root
        var email: String
        var profession: String

        presenter.getUserData()

        binding.updateSettings.setOnClickListener {
            email = binding.settingsEmail.text.toString()
            profession = binding.settingsProfession.text.toString()
            presenter.updateUserData(email, profession, bitmap)
        }
        binding.settingsAvatar.setOnClickListener {
            selectImage()
        }

        binding.settingsSignOut.setOnClickListener {
            presenter.signOut()
            backToLogin()
        }
        return myView
    }


    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        avatar = view?.findViewById<ImageButton>(R.id.settingsAvatar)!!

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            avatarPath = data?.data!!
            avatar?.setImageURI(data?.data) // handle chosen image
            // ak avtvirto firebaseshi

            try {
                bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, avatarPath)
                Log.d("VIKA", "I SET IMAGE BITMAP")
                avatar?.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun backToLogin() {
        requireActivity().run{
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myFragment = null
    }

    // display fetched data in inputs as placeholders
    override fun displayData(userModel: UserModel) {
        binding.settingsEmail.setText(userModel.email)
        binding.settingsProfession.setText(userModel.profession)
    }

    override fun success() {
        Toast.makeText(context, "Profile data successfully updated!", Toast.LENGTH_LONG).show()
    }

    override fun failed() {
        Toast.makeText(context, "Profile data update failed!", Toast.LENGTH_LONG).show()
    }

}