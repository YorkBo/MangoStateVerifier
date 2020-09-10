package cc.yorkbo.mango

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.yorkbo.ui.pagestate.StateVerifier
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt1.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        StateVerifier.instance.bind(this)

        StateVerifier.instance.addState(this, 0, UIEntrust1())
        StateVerifier.instance.addState(this, 1, UIEntrust2())

        bt1.postDelayed({
            StateVerifier.instance.executeEntrust(this, 0)
        }, 1500)
    }

}