package ge.anikolaishvili.messengerapp.updateSettings.view

import android.widget.ImageButton
import android.widget.ImageView
import ge.anikolaishvili.messengerapp.datamodel.UserModel

interface ISettingsView {
    var avatar: ImageButton
    fun displayData(userModel: UserModel)
    fun success()
    fun failed()
}