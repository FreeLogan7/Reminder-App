package com.freedman.reminders

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.freedman.reminders.databinding.ActivityGeneralFragmentBinding
import com.freedman.reminders.databinding.PopupBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class General : Fragment() {

    private lateinit var binding: ActivityGeneralFragmentBinding
    private lateinit var popupBinding: PopupBinding
    private val sharePref: SharedPreferences by lazy {
        requireContext().getSharedPreferences(
            SAVE_WHERE,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityGeneralFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clicked()
    }

    override fun onResume() {
        super.onResume()
        displayValues()

    }

    private fun clicked() {
        binding.cardViewBinDay.setOnClickListener { editDisplay(BIN) }
        binding.cardViewNationalNo.setOnClickListener { editDisplay(INSURANCE) }
        binding.cardViewWeddingAnniversary.setOnClickListener { editDisplay(WEDDING) }
    }

    private fun editDisplay(value: String) {
        popupBinding = PopupBinding.inflate(requireActivity().layoutInflater)
        popupBinding.editTextPopupValue.setText(sharePref.getString(value, null))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Set MY Value")
            .setView(popupBinding.root)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Save") { _, _ ->
                sharePref.edit {
                    putString(value, popupBinding.editTextPopupValue.text.toString())
                }
                displayValues()
            }
            .show()
    }

    private fun displayValues() {
        binding.editTextBinDay.text = sharePref.getString(BIN, null)
        binding.editTextNationalInsuranceNo.text = sharePref.getString(INSURANCE, null)
        binding.editTextWeddingAnniversary.text = sharePref.getString(WEDDING, null)
    }

    companion object {
        const val BIN = "bin"
        const val INSURANCE = "insurance"
        const val WEDDING = "wedding"
        const val SAVE_WHERE = "general"
        //const val  = ""

    }
}

