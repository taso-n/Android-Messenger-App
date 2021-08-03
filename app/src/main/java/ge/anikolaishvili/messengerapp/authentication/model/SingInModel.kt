package ge.anikolaishvili.messengerapp.authentication.model

import ge.anikolaishvili.messengerapp.authentication.presenter.ISignInPresenter
import ge.anikolaishvili.messengerapp.datamodel.UserModel
import java.util.concurrent.Executor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SingInModel(private val presenter: ISignInPresenter) : ISignInModel, Executor {

    private val users = FirebaseDatabase.getInstance().getReference("users")
    private var auth: FirebaseAuth = Firebase.auth


    // register user with given params
    override fun initUser(userModel: UserModel, password: String) {
        userModel.email?.let { email ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        auth.currentUser?.uid?.let {
                            users.child(it).setValue(userModel).addOnSuccessListener {
                                presenter.didCreateUser(true)
                            }.addOnFailureListener {
                                presenter.didCreateUser(false)
                            }
                        }
                    } else {
                        presenter.didCreateUser(false)
                    }
                }
        }

    }

    // check if user exists using firebase auth
    override fun checkUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    presenter.didCheckUser(true)
                } else {
                    presenter.didCheckUser(false)
                }
            }
    }

    // check is user is logged in
    override fun isAuthed(): Boolean {
        return auth.currentUser != null
    }

    override fun execute(command: Runnable?) {
        command?.run()
    }

}
