package ge.anikolaishvili.messengerapp.updateSettings.presenter

import android.graphics.Bitmap
import ge.anikolaishvili.messengerapp.datamodel.UserModel

interface ISettingsPresenter {
    fun updateUserData(email: String, profession: String, avatar: Bitmap?)
    fun didUpdateUserData(isChanged: Boolean)
    fun getUserData()
    fun didGetUserdata(user: UserModel?)
    fun signOut()
    fun loadAvatar(byteArray: ByteArray)
}