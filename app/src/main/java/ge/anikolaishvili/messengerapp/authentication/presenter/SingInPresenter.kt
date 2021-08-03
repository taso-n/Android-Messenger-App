package ge.anikolaishvili.messengerapp.authentication.presenter

import ge.anikolaishvili.messengerapp.authentication.model.ISignInModel
import ge.anikolaishvili.messengerapp.authentication.model.SingInModel
import ge.anikolaishvili.messengerapp.authentication.view.IAuthView
import ge.anikolaishvili.messengerapp.datamodel.UserModel


class SingInPresenter(private val view: IAuthView) :
    ISignInPresenter {
        private val model: ISignInModel = SingInModel(this)

        override fun initUser(userModel: UserModel, password: String) {
            model.initUser(userModel, password)
        }
        override fun checkUser(email: String, password: String) {
            model.checkUser(email, password)
        }
        override fun didCheckUser(isValid: Boolean) {
            if(isValid) view.success() else view.failed()
        }
        override fun didCreateUser(isCreated: Boolean) {
            didCheckUser(isCreated)
        }
        override fun isAuthed(): Boolean {
            return model.isAuthed()
        }
}