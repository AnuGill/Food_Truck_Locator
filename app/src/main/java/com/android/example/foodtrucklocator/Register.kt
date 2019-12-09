package com.android.example.foodtrucklocator


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
//import com.android.example.foodtrucklocator.databinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import com.google.firebase.firestore.DocumentReference
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.Constraints.TAG
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import java.util.regex.Pattern
import android.widget.EditText as EditText1
import java.util.HashMap as HashMap1


/**
 * A simple [Fragment] subclass.
 */
class Register : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.register, container, false)
    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }




    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        val numbersCheckedList = ArrayList<Int>()
        val regNameBoxID = view.findViewById<EditText1>(R.id.regNameInput)
        val regZipCodeBoxID = view.findViewById<EditText1>(R.id.regZipCodeInput)
        val regEmailBoxID = view.findViewById<EditText1>(R.id.regEmailInput)
        val regPassBoxID = view.findViewById<EditText1>(R.id.regPassInput)
        val regrePassBoxID = view.findViewById<EditText1>(R.id.regrePassInput)
        val regMoboxID = view.findViewById<EditText1>(R.id.regmobInput)
        val buttonRegID = view.findViewById<Button>(R.id.regBtnSignup)
        val regLinkLogin = view.findViewById<TextView>(R.id.regLinkLogin)
        val randomNumber = Math.random()
        regLinkLogin.setOnClickListener { view ->view.findNavController().navigate(R.id.action_registerButton_to_loginButton)}



        val noteDataMap = HashMap<String, Any>()

        //button clicked
        buttonRegID.setOnClickListener {
            val fullName = regNameBoxID.text.toString()
            val zipCode = regZipCodeBoxID.text.toString()
            val emailId = regEmailBoxID.text.toString()
            val password = regPassBoxID.text.toString()
            val rePassword = regrePassBoxID.text.toString()
            val mobileNum = regMoboxID.text.toString()
            if(!isValidEmail(emailId)){
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle("Alert")
                alertDialog.setMessage("Please give a valid emailID")
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                alertDialog.show()

            }
            else if(!isValidZipCode(zipCode)){
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle("Alert")
                alertDialog.setMessage("Please give a valid zipcode of 5 digits")
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                alertDialog.show()

            }

            else if(!isValidPhoneNumber(mobileNum)){
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle("Alert")
                alertDialog.setMessage("Please give a valid phone number")
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                alertDialog.show()

            }
            else if(!isValidPasswordr(password,rePassword)){
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle("Alert")
                alertDialog.setMessage("Please fill the correct password")
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                alertDialog.show()

            }
            else if (fullName.isEmpty()) {
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle("Alert")
                alertDialog.setMessage("Please fill the name")
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                alertDialog.show()
            } else {
                // DatabaseReference ref = FirebaseFirestore.getReference().child("Authenticated-Users");
                noteDataMap.put("Full Name", fullName)
                noteDataMap.put("Zipcode", zipCode)
                noteDataMap.put("emailId", emailId)
                noteDataMap.put("password", password)
                noteDataMap.put("MobileNumber", mobileNum)
                // Access a Cloud Firestore instance from your Activity
                val db = FirebaseFirestore.getInstance()
                // noteDocRef.set( noteDataMap)
                //db.collection("users").whereEqualTo("email", emailId).add

                db.collection("users")
                    .add( noteDataMap)
                /* .addOnSuccessListener { documentReference ->
                     Log.d(
                         TAG,
                         "DocumentSnapshot written with ID: " + documentReference.id
                     )
                 }*/
                // .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle(fullName+ " is registered");
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

                alertDialog.show()
            }

        }


    }
    fun isValidEmail(target:CharSequence): Boolean {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    fun isValidZipCode(target:CharSequence): Boolean {
        return (!TextUtils.isEmpty(target) && target.length == 5);
    }
    fun isValidPhoneNumber(target:CharSequence): Boolean {
        return (!TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches());
    }

    fun isValidPasswordr(password:CharSequence,confirmPass:CharSequence): Boolean {
        return (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPass) && password.equals(confirmPass));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters

    }


}
