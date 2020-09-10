package cc.yorkbo.mango

import android.app.Application

/**
 * Description:
 * @author YorkBo
 */
class App : Application() {

    companion object {
        const val TAG = "AladdinAppStore"

        @JvmStatic
        var instance: App? = null
            private set

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}