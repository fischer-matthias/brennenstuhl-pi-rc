package work.matse.brennstuhl_pirc

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import work.matse.brennstuhl_pirc.helper.IPMaskTextWatcher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iIP.addTextChangedListener(IPMaskTextWatcher())

        bAON.setOnClickListener({ v -> sendSignal("A", true) })
        bBON.setOnClickListener({ v -> sendSignal("B", true) })
        bCON.setOnClickListener({ v -> sendSignal("C", true) })
        bDON.setOnClickListener({ v -> sendSignal("D", true) })

        bAOFF.setOnClickListener({ v -> sendSignal("A", false) })
        bBOFF.setOnClickListener({ v -> sendSignal("B", false) })
        bCOFF.setOnClickListener({ v -> sendSignal("C", false) })
        bDOFF.setOnClickListener({ v -> sendSignal("D", false) })
    }

    private fun sendSignal(unitCode : String, on : Boolean) {
        var message = "$unitCode "
        var systemCode = getSystemcode()
        var onoff = ""

        if (on) {
            message += "ON"
            onoff = "on"
        } else {
            message += "OFF"
            onoff = "off"
        }

        val queue = Volley.newRequestQueue(this)
        val url = "http://" + iIP.text + ":8080/api/system/" + systemCode + "/unit/" + unitCode + '/' + onoff;

        System.out.println(url)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                System.out.println(response)
            },
            Response.ErrorListener { error -> System.out.println(error)}
        )

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun getSystemcode() : String {

        var systemCode = "";

        if (sys1.isChecked()) {
            systemCode += '1'
        } else {
            systemCode += '0'
        }

        if (sys2.isChecked()) {
            systemCode += '1'
        } else {
            systemCode += '0'
        }

        if (sys3.isChecked()) {
            systemCode += '1'
        } else {
            systemCode += '0'
        }

        if (sys4.isChecked()) {
            systemCode += '1'
        } else {
            systemCode += '0'
        }

        if (sys5.isChecked()) {
            systemCode += '1'
        } else {
            systemCode += '0'
        }

        return systemCode;
    }
}
