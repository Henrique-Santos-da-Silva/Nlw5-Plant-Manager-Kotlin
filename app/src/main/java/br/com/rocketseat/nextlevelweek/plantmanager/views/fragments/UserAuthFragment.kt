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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.rocketseat.nextlevelweek.plantmanager.R
import br.com.rocketseat.nextlevelweek.plantmanager.databinding.FragmentUserAuthBinding
import br.com.rocketseat.nextlevelweek.plantmanager.utils.hideBackButtonToBar
import br.com.rocketseat.nextlevelweek.plantmanager.utils.hideSoftKeyboard
import br.com.rocketseat.nextlevelweek.plantmanager.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class UserAuthFragment : Fragment() {
    private var _binding: FragmentUserAuthBinding? = null
    private val binding: FragmentUserAuthBinding? get() = _binding

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).hideBackButtonToBar()
    }

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

                userViewModel.insertUser(inputUserName)

                hideSoftKeyboard()

                findNavController().navigate(R.id.action_userAuthFragment_to_readyToStartFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).hideBackButtonToBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideSoftKeyboard()
        (activity as AppCompatActivity).hideBackButtonToBar()
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