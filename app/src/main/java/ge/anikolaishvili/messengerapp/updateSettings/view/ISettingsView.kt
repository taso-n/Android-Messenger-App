package ge.anikolaishvili.messengerapp.updateSettings.view

import ge.anikolaishvili.messengerapp.datamodel.UserModel

interface ISettingsView {
    fun displayData(userModel: UserModel)
    fun success()
    fun failed()
}