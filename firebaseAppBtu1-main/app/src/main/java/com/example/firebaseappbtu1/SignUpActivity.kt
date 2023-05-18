import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val database = FirebaseDatabase.getInstance()
        val studentsRef = database.getReference("students")

        signUpButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val personalNumber = personalNumberEditText.text.toString()
            val profilePicture = profilePictureUrlEditText.text.toString()
            val email = emailEditText.text.toString()

            if (personalNumber.length != 13) {

                return@setOnClickListener
            }

            if (!email.contains("@")) {

                return@setOnClickListener
            }

            val student = Student(firstName, lastName, personalNumber, profilePicture, email)
            val studentKey = studentsRef.push().key

            studentKey?.let {
                studentsRef.child(it).setValue(student)
            }

        }
    }
}
