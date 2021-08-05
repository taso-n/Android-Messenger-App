package ge.anikolaishvili.messengerapp.updateSettings.presenter

import ge.anikolaishvili.messengerapp.datamodel.UserModel

interface ISettingsPresenter {
    fun updateUserData(email: String, profession: String)
    fun didUpdateUserData(isChanged: Boolean)
    fun getUserData()
    fun didGetUserdata(user: UserModel?)
    fun signOut()
}