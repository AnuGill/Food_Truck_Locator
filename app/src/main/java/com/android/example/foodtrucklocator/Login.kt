package com.android.example.foodtrucklocator


import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.example.foodtrucklocator.databinding.LoginBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class Login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        val numbersCheckedList = ArrayList<Int>()
        val regPassword = view.findViewById<EditText>(R.id.input_password)
        val logEmailBoxID = view.findViewById<EditText>(R.id.input_email)
        val buttonLoginID = view.findViewById<Button>(R.id.btn_login)
        val buttonToReg = view.findViewById<TextView>(R.id.link_signup)
        buttonToReg.setOnClickListener { view ->view.findNavController().
            navigate(R.id.action_loginButton_to_registerButton)}


        //button clicked
        buttonLoginID.setOnClickListener {
            val Name = logEmailBoxID.text.toString()
            val password = regPassword.text.toString()

            if (Name.isEmpty() || password.isEmpty()) {
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle("Alert")
                alertDialog.setMessage("Please fill all the fields")
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                alertDialog.show()
            } else {
                // Access a Cloud Firestore instance from your Activity
                val db = FirebaseFirestore.getInstance()
                // Create a reference to the users collection
                // val citiesRef = db.collection("users")
                db.collection("users")
                    .whereEqualTo("emailId", Name)
                    .get()
                    .addOnSuccessListener {
                            documents ->

                        if(documents.size()==0){
                            val alertDialog = AlertDialog.Builder(context).create()
                            alertDialog.setTitle("Alert")
                            alertDialog.setMessage("Wrong Username/Password")
                            alertDialog.setButton(
                                AlertDialog.BUTTON_NEUTRAL, "OK",
                                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                            alertDialog.show()
                        }

                        for (document in documents) {
                            Log.d("Error", "DocumentSnapshot Fetched: "+"${document.id} => ${document["password"]}")

                            if("${document["password"]}"==password){
                               
                                view.findNavController().navigate(R.id.action_loginButton_to_mapsActivity)
                            }
                            else {
                                val alertDialog = AlertDialog.Builder(context).create()
                                alertDialog.setTitle("Alert")
                                alertDialog.setMessage("Wrong Username/Password")
                                alertDialog.setButton(
                                    AlertDialog.BUTTON_NEUTRAL, "OK",
                                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                                alertDialog.show()
                            }

                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w("ERROR", "Error getting documents: ", exception)
                        val alertDialog = AlertDialog.Builder(context).create()
                        alertDialog.setTitle("Alert")
                        alertDialog.setMessage("Wrong username/password")
                        alertDialog.setButton(
                            AlertDialog.BUTTON_NEUTRAL, "OK",
                            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                    }
            }

        }

    }
}



