package br.com.rocketseat.nextlevelweek.plantmanager.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentUserAuthBinding
import br.com.rocketseat.nextlevelweek.plantmanager.hideSoftKeyboard

class UserAuthFragment : Fragment() {
    private var _binding: FragmentUserAuthBinding? = null
    private val binding: FragmentUserAuthBinding? get() = _binding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUserAuthBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.let { userAuthBinding ->
            userAuthValidation(userAuthBinding.btnConfirm, userAuthBinding.edtName, userAuthBinding.txtEmoji)

            userAuthBinding.btnConfirm.setOnClickListener {
                val inputUserName: String = userAuthBinding.edtName.text.toString()

                val actionUserAuthToPlantSelect: NavDirections = UserAuthFragmentDirections.actionUserAuthFragmentToReadyToStartFragment(inputUserName)

                hideSoftKeyboard()
                findNavController().navigate(actionUserAuthToPlantSelect)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideSoftKeyboard()
    }

    private fun userAuthValidation(confirmButton: Button, editTextName: EditText, emoji: TextView) {
        confirmButton.apply {
            alpha = 0.5f
            isEnabled = false
        }

        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && s.length >= 3) {
                    emoji.text = getString(R.string.happy_emoji_close_eyes)
                    confirmButton.apply {
                        alpha = 1f
                        isEnabled = true
                    }
                } else {
                    emoji.text = getString(R.string.happy_emoji_open_eyes)
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