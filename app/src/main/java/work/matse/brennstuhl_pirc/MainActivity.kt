package work.matse.brennstuhl_pirc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import work.matse.brennstuhl_pirc.helper.IPMaskTextWatcher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initIPRegex()
    }

    fun initIPRegex() {
        val ip = findViewById<EditText>(R.id.iIP);
        ip.addTextChangedListener(IPMaskTextWatcher())
    }
}
