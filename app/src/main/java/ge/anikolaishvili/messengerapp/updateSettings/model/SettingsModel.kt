package ge.anikolaishvili.messengerapp.updateSettings.model

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import ge.anikolaishvili.messengerapp.datamodel.UserModel
import ge.anikolaishvili.messengerapp.updateSettings.presenter.ISettingsPresenter
import java.io.ByteArrayOutputStream

class SettingsModel(private val presenter: ISettingsPresenter) : ISettingsModel {

    private val database = Firebase.database
    private val users = database.getReference("users")
    private val user = Firebase.auth.currentUser

    override fun updateUserData(email: String, profession: String) {
        user?.updateEmail(email)?.addOnCompleteListener {
            // email updated
            if (it.isSuccessful) {
                handleUpdateTaskSuccess(email, profession)
            } else {
                presenter.didUpdateUserData(false)
            }
        }
    }

    private fun handleUpdateTaskSuccess(email: String, profession: String) {
        if (user != null) {
            users.child(user.uid).updateChildren(toMap(email, profession))
                .addOnCompleteListener {
                if (it.isSuccessful) {
                    presenter.didUpdateUserData(true)
                } else {
                    presenter.didUpdateUserData(false)
                }
            }
        }
    }

    private fun toMap(email: String, profession: String): Map<String, String> {
        return mapOf(
            "email" to email,
            "profession" to profession)
    }

    override fun getUserData() {
        if (user != null) {
            users.child(user.uid).get().addOnSuccessListener {
                val fetchedUser = it.getValue<UserModel>()
                presenter.didGetUserdata(fetchedUser)
            }
        }
    }

    override fun signOut() {
        Firebase.auth.signOut()
    }

    override fun updateAvatar(avatar: Bitmap?, userName: String) {
        if (avatar != null) {
            val baos = ByteArrayOutputStream()
            avatar.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            val data: ByteArray = baos.toByteArray()
            val storage = FirebaseStorage.getInstance();
            val storageRef = storage.getReferenceFromUrl("gs://chat-app-261b9.appspot.com/")
            val imagesRef = storageRef.child("${userName}.jpg")
            val uploadTask = imagesRef.putBytes(data)

            uploadTask.addOnFailureListener {
                Log.e("VIKA","IMAGE FAILED")
            }.addOnSuccessListener { taskSnapshot ->
                Log.e("VIKA","IMAGE SUCC")
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                //            val downloadUrl: Uri = taskSnapshot.getDownloadUrl()
                // Do what you want
                // presenter.updateSuccessful()
            }
        }
    }
}