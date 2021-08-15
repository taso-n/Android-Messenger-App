package ge.anikolaishvili.messengerapp.updateSettings.model

import android.graphics.Bitmap

interface ISettingsModel {
    fun updateUserData(email: String, profession: String)
    fun getUserData()
    fun signOut()
    fun updateAvatar(avatar: Bitmap?, userName:String)
}