package ge.anikolaishvili.messengerapp.updateSettings.presenter

import ge.anikolaishvili.messengerapp.datamodel.UserModel
import ge.anikolaishvili.messengerapp.updateSettings.model.SettingsModel
import ge.anikolaishvili.messengerapp.updateSettings.model.ISettingsModel
import ge.anikolaishvili.messengerapp.updateSettings.view.ISettingsView

class SettingsPresenter(private val view: ISettingsView) : ISettingsPresenter {

    private val settingsModel: ISettingsModel = SettingsModel(this)

    override fun updateUserData(email: String, profession: String) {
        settingsModel.updateUserData(email, profession)
    }

    override fun didUpdateUserData(isChanged: Boolean) {
        if (isChanged) view.success() else view.failed()
    }

    override fun getUserData() {
        settingsModel.getUserData()
    }

    override fun didGetUserdata(user: UserModel?) {
        user?.let { view.displayData(it) }
    }

    override fun signOut() {
        settingsModel.signOut()
    }

}