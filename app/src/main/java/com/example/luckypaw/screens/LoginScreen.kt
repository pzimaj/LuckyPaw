package com.example.luckypaw.screens

import android.text.TextUtils
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

@Composable
fun Login(navController: NavController) {
    Column(
        modifier = Modifier.padding(45.dp),
        verticalArrangement  = Arrangement.Center
    ) {
        Text(
            text = "Sign in",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Here, you can sign in to your account, if you already have one!",
        )

        Spacer(modifier = Modifier.height(20.dp))

        val email = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

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

                          TextUtils.isEmpty(password.value.text.trim { it <= ' ' }) -> {
                              Toast.makeText(
                                  context,
                                  "Please enter your desired password",
                                  Toast.LENGTH_SHORT
                              ).show()
                          }

                          else -> {
                              val email: String = email.value.text.trim { it <= ' ' }
                              val pass: String = password.value.text.trim { it <= ' ' }

                              FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                                  .addOnCompleteListener(
                                      OnCompleteListener<AuthResult> {
                                          if(it.isSuccessful) {
                                              Toast.makeText(
                                                  context,
                                                  "You have been logged in successfully!",
                                                  Toast.LENGTH_SHORT
                                              ).show()

                                              navController.navigate(LuckyPaw.BottomTab.name) {
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
            Text(text = "Sign in")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            contentAlignment = Alignment.BottomCenter,
        ) {
            ClickableText(
                text = buildAnnotatedString {
                    append("If you don't have an account, you can")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold
                        ),
                    ) {
                        append(" register here!")
                    }
                }, onClick = {
                    navController.navigate(LuckyPaw.Register.name)
                }
            )
        }
    }
}