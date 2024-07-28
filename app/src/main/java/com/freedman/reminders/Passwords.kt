package com.freedman.reminders

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.freedman.reminders.databinding.ActivityPasswordsFragmentBinding
import com.freedman.reminders.databinding.PopupBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.zip.Inflater

class Passwords : Fragment() {


    private lateinit var binding: ActivityPasswordsFragmentBinding
    private lateinit var bindingPopup: PopupBinding
    private val sharePref: SharedPreferences by lazy {
        requireActivity().getSharedPreferences(
            SAVE_WHERE,
            Context.MODE_PRIVATE
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityPasswordsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValues()
        clicked()
    }

    override fun onResume() {
        super.onResume()
        displayValues()

    }

    private fun displayValues() {
        binding.editTextWifiPassword.text = sharePref.getString(PASSWORD, null)
        binding.editTextTabletPin.text = sharePref.getString(TABLET_PIN, null)
        binding.editTextBikeLock.text = sharePref.getString(BIKE_LOCK, null)
    }

    private fun clicked() {
        binding.cardViewWifiPassword.setOnClickListener { showEditDialog(PASSWORD) }
        binding.cardViewTabletPin.setOnClickListener { showEditDialog(TABLET_PIN) }
        binding.cardViewBikeLock.setOnClickListener { showEditDialog(BIKE_LOCK) }
    }

    private fun showEditDialog(value: String) {
        bindingPopup = PopupBinding.inflate(requireActivity().layoutInflater)
        bindingPopup.editTextPopupValue.setText(sharePref.getString(value, null))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Set MY Value")
            .setView(bindingPopup.root)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Save") { _, _ ->
                sharePref.edit {
                    putString(value, bindingPopup.editTextPopupValue.text.toString())
                }
                displayValues()
            }

            .show()
    }



    companion object {
        const val PASSWORD = "password"
        const val TABLET_PIN = "tablet"
        const val BIKE_LOCK = "bike"
        const val SAVE_WHERE = "passwords"
    }


}