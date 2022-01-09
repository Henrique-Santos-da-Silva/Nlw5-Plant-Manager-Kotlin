package br.com.rocketseat.nextlevelweek.plantmanager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentConfirmationBinding

class ConfirmationFragment : Fragment() {
    private var _binding: FragmentConfirmationBinding? = null
    private val binding: FragmentConfirmationBinding? get() = _binding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { confirmationBinding ->
            confirmationValidation(confirmationBinding.btnConfirm, confirmationBinding.edtName)
        }
    }

    private fun confirmationValidation(confirmButton: Button, editTextName: EditText) {
        confirmButton.apply {
            alpha = 0.5f
            isEnabled = false
        }

        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && s.length >= 3) {
                    confirmButton.apply {
                        alpha = 1f
                        isEnabled = true
                    }
                } else {
                    confirmButton.apply {
                        alpha = 0.5f
                        isEnabled = false
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }
}