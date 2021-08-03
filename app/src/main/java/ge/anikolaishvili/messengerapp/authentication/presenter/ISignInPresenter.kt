package ge.anikolaishvili.messengerapp.authentication.presenter

import ge.anikolaishvili.messengerapp.datamodel.UserModel

interface ISignInPresenter {

    fun initUser(userModel: UserModel, password: String)
    fun checkUser(email: String, password: String)
    fun didCheckUser(isValid: Boolean)
    fun didCreateUser(isCreated: Boolean)
    fun isAuthed(): Boolean
}