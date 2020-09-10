package cc.yorkbo.ui.pagestate

import androidx.lifecycle.LifecycleOwner

/**
 *
 * @author BoWei
 * 2020/9/4 4:58 PM
 */
interface IStateEntrust {

    fun onPageChange(pageObj: LifecycleOwner, lifecycleEvent: PageLifecycle)

}