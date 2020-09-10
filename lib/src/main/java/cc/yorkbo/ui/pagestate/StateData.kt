package cc.yorkbo.ui.pagestate

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author BoWei
 * 2020/9/4 5:02 PM
 * 因为每一个页面只对应一个StateData，所以要么是 FragmentActivity，要么是 Fragment
 */
class StateData(page: LifecycleOwner) : LifecycleObserver {

    /**
     * 页面实例采用栈存储，因为是同一个页面的不同实例，所以最先加进来的，肯定是在栈顶的
     * 用栈来进行存储可以有效的提高获取实例的效率
     */
    private val pageEntrustMap = sortedMapOf<Int, IStateEntrust>()
    private val pageObj = page
    private var lifecycleEvent: PageLifecycle = PageLifecycle.CREATE

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        lifecycleEvent = PageLifecycle.START
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        lifecycleEvent = PageLifecycle.RESUME
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        lifecycleEvent = PageLifecycle.PAUSE
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        lifecycleEvent = PageLifecycle.STOP
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        PageStateManager.removeState(pageObj)
    }

    fun addPageEntrust(stateKey: Int, stateEntrust: IStateEntrust) {
        pageEntrustMap[stateKey] = stateEntrust
    }

    fun removePageEntrust(stateKey: Int) {
        pageEntrustMap.remove(stateKey)
    }

    fun getPageEntrust(stateKey: Int): IStateEntrust? {
        return pageEntrustMap[stateKey]
    }

    fun clearPageEntrust() {
        pageEntrustMap.clear()
    }

    fun getLifecycleEvent(): PageLifecycle {
        if (pageObj is Fragment && lifecycleEvent == PageLifecycle.RESUME) {
            return if (pageObj.isHidden) {
                PageLifecycle.HIDE
            } else {
                PageLifecycle.SHOW
            }
        }
        return lifecycleEvent
    }

}