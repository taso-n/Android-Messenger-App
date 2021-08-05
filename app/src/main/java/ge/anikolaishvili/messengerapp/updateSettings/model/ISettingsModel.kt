package ge.anikolaishvili.messengerapp.updateSettings.model

interface ISettingsModel {
    fun updateUserData(email: String, profession: String)
    fun getUserData()
    fun signOut()
}