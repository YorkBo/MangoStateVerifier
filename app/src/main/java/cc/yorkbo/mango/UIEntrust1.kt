package cc.yorkbo.mango

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import cc.yorkbo.ui.pagestate.IStateEntrust
import cc.yorkbo.ui.pagestate.PageLifecycle

/**
 * Description:
 *
 * @author BoWei
 * 2020/9/7 8:10 PM
 */
class UIEntrust1 : IStateEntrust {

    override fun onPageChange(pageObj: LifecycleOwner, lifecycleEvent: PageLifecycle) {
        if (lifecycleEvent == PageLifecycle.RESUME) {
            var context: Context? = null
            if (pageObj is Fragment) {
                context = pageObj.context
            } else if (pageObj is FragmentActivity) {
                context = pageObj
            }
            context?.let {
                Toast.makeText(it, "我是第一个 UI 状态", Toast.LENGTH_SHORT).show()
            }
        }
    }
}