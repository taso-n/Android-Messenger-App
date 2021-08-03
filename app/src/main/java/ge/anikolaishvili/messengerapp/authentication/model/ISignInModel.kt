package ge.anikolaishvili.messengerapp.authentication.model

import ge.anikolaishvili.messengerapp.datamodel.UserModel

interface ISignInModel {
    fun initUser(userModel: UserModel, password: String)
    fun isAuthed(): Boolean
    fun checkUser(email: String, password: String)
}