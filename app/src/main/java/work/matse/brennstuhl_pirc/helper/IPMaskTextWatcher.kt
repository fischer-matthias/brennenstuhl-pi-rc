package work.matse.brennstuhl_pirc.helper

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

class IPMaskTextWatcher : TextWatcher {

    private val regex = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])\\.){0,3}((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])){0,1}$")
    private var previousText: String = ""

    override fun afterTextChanged(s: Editable) {
        if (regex.matcher(s).matches()) {
            previousText = s.toString()
        } else {
            s.replace(0, s.length, previousText)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
}