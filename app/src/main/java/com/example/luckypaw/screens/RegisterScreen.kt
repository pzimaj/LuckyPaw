package com.example.luckypaw.screens

import android.content.ContentValues.TAG
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.luckypaw.LuckyPaw
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable
fun Register(navController: NavController) {

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    Column(
        modifier = Modifier.padding(45.dp),
        verticalArrangement  = Arrangement.Center
    ) {
        Text(
            text = "Sign up",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Here, you can create a new account, if you already don't have one!",
        )

        Spacer(modifier = Modifier.height(20.dp))

        val email = remember { mutableStateOf(TextFieldValue())}
        val password = remember { mutableStateOf(TextFieldValue())}
        val confirmPassword = remember { mutableStateOf(TextFieldValue())}
        val name = remember { mutableStateOf(TextFieldValue())}
        val surname = remember { mutableStateOf(TextFieldValue())}

        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            label = {
                Text(text = "Name")
            },
            placeholder = {
                Text(text = "Enter your name here")
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colors.primaryVariant
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = surname.value,
            onValueChange = {
                surname.value = it
            },
            label = {
                Text(text = "Surname")
            },
            placeholder = {
                Text(text = "Enter your surname here")
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colors.primaryVariant
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = {
                Text(text = "E-mail")
            },
            placeholder = {
                Text(text = "Enter your e-mail here")
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colors.primaryVariant
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            label = {
                Text(text = "Password")
            },
            placeholder = {
                Text(text = "Enter your desired password here")
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colors.primaryVariant
            ),
            visualTransformation =  PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = confirmPassword.value,
            onValueChange = {
                confirmPassword.value = it
            },
            label = {
                Text(text = "Confirm password")
            },
            placeholder = {
                Text(text = "Re-enter password")
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = MaterialTheme.colors.primaryVariant
            ),
            visualTransformation =  PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Spacer(modifier = Modifier.height(10.dp))

        val context = LocalContext.current

        Button(
            onClick = {
                   when {
                       TextUtils.isEmpty(email.value.text.trim { it <= ' ' }) -> {
                           Toast.makeText(
                               context,
                               "Please enter your email information",
                               Toast.LENGTH_SHORT
                           ).show()
                       }

                       TextUtils.isEmpty(name.value.text.trim { it <= ' ' }) -> {
                           Toast.makeText(
                               context,
                               "Please enter your name",
                               Toast.LENGTH_SHORT
                           ).show()
                       }

                       TextUtils.isEmpty(surname.value.text.trim { it <= ' ' }) -> {
                           Toast.makeText(
                               context,
                               "Please enter your surname",
                               Toast.LENGTH_SHORT
                           ).show()
                       }

                       TextUtils.isEmpty(password.value.text.trim { it <= ' ' }) -> {
                           Toast.makeText(
                               context,
                               "Please enter your desired password",
                               Toast.LENGTH_SHORT
                           ).show()
                       }

                       TextUtils.isEmpty(confirmPassword.value.text.trim { it <= ' ' }) -> {
                           Toast.makeText(
                               context,
                               "Please enter your desired password again",
                               Toast.LENGTH_SHORT
                           ).show()
                       }

//                       TextUtils.equals(
//                           confirmPassword.value.text.trim { it <= ' ' },
//                           password.value.text.trim { it <= ' ' }
//                       ) -> {
//                           Toast.makeText(
//                               context,
//                               "Passwords do not match, please try again",
//                               Toast.LENGTH_SHORT
//                           ).show()
//                       }

                       else -> {
                           val email: String = email.value.text.trim { it <= ' ' }
                           val pass: String = password.value.text.trim { it <= ' ' }
                           val name: String = name.value.text.trim { it <= ' ' }
                           val surname: String = surname.value.text.trim { it <= ' ' }

                           FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                               .addOnCompleteListener(
                                   OnCompleteListener<AuthResult> {
                                       if(it.isSuccessful) {
                                           val user = Firebase.auth.currentUser

                                           val data = hashMapOf(
                                               "email" to email,
                                               "name" to name,
                                               "surname" to surname,
                                               "dog" to emptyList<String>()
                                           )

                                           if (user != null) {
                                               db.collection("users").document(user.uid)
                                                   .set(data)
                                                   .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                                                   .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                                           }

                                           Toast.makeText(
                                               context,
                                               "You have been signed up successfully!",
                                               Toast.LENGTH_SHORT
                                           ).show()

                                           navController.navigate(LuckyPaw.BottomTab.name){
                                               popUpTo(navController.graph.id) {
                                                   inclusive = true
                                               }
                                           }
                                       } else {
                                           Toast.makeText(
                                               context,
                                               it.exception!!.message.toString(),
                                               Toast.LENGTH_SHORT
                                           ).show()
                                       }
                                   }
                               )
                       }
                   }
            },
        ) {
            Text(text = "Sign up")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            contentAlignment = Alignment.BottomCenter,
        ) {
            ClickableText(
                text = buildAnnotatedString {
                    append("If you already have an account, you can")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold
                        ),
                    ) {
                        append(" login here!")
                    }
                }, onClick = {
                    navController.navigate(LuckyPaw.Login.name)
                }
            )
        }
    }
}
