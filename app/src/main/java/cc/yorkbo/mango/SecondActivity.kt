package cc.yorkbo.mango

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.yorkbo.ui.pagestate.StateVerifier
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        StateVerifier.instance.bind(this)
        StateVerifier.instance.addState(this, 0, UIEntrust2())

        bt2.postDelayed({
            Thread {
                kotlin.run {
                    StateVerifier.instance.executeEntrust(this, 0)
                }
            }.start()
        }, 1500)

        bt2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

}