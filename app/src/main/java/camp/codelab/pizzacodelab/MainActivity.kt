package camp.codelab.pizzacodelab

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val tip = sharedPreferences.getFloat("TIP", 0f)
        tipEditText.setText(tip.toString())



        groupRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            calculatePrise()
        }
        pepsiCheckBox.setOnCheckedChangeListener { buttonView, isCHecke ->
            calculatePrise()
        }
        garlicCheckBox.setOnCheckedChangeListener { buttonView, isCHecke ->
            calculatePrise()
        }
        friesCheckBox.setOnCheckedChangeListener { buttonView, isCHecke ->
            calculatePrise()
        }
        tipEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                calculatePrise()

            }

        })


    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        val tipString = tipEditText.text.toString()
        if (tipString.isNotEmpty()) {
            val tip = tipString.toFloat()
            editor.putFloat("TIP", tip)
        } else {
            editor.putFloat("TIP", 0f)
        }
        editor.apply()
    }

    fun calculatePrise() {
        val tipEditTextString: String = tipEditText.text.toString()
        var prise: Float = 0f
        when (groupRadioGroup.checkedRadioButtonId) {
            sawsageRadioButton.id -> {
                prise += 8f
            }
            cheeseRadioButton.id -> {
                prise += 5f
            }
            appleRadioButton.id -> {
                prise += 20f
            }
            oliverRadioButton.id -> {
                prise += 6.5f
            }
        }
        if (pepsiCheckBox.isChecked) {
            prise += 0.5f
        }
        if (garlicCheckBox.isChecked) {
            prise += 0.2f
        }
        if (friesCheckBox.isChecked) {
            prise += 2f
        }
        if (tipEditTextString.isNotEmpty()) {
            prise += tipEditTextString.toFloat()
        }
        resultPrice.text = prise.toString()
    }
}
