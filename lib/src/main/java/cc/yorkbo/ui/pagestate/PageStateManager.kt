package cc.yorkbo.ui.pagestate

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleOwner

/**
 * 页面状态管理者
 * 功能：根据状态管理页面弹窗
 * 例如请求网络要弹加载框，加载失败要展示失败页，退出要弹出确认框等等
 * @author BoWei
 * 2020/9/2 2:39 PM
 */
class PageStateManager : IStateVerifier {

    /**
     * 用于存放类状态实体的容器
     * 由于每个类可能有多个实例，因此存放的 key 为对应的类名，存放的值为对应类的状态数据
     */

    companion object {
        private val stateMap by lazy {
            mutableMapOf<Any, StateData>()
        }

        fun removeState(page: LifecycleOwner) {
            val pageData = stateMap[page]
            pageData?.let {
                it.clearPageEntrust()
                stateMap.remove(pageData)
            }
        }
    }

    private val mHandle by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun addState(
        page: LifecycleOwner,
        pageStateKey: Int,
        stateEntrust: IStateEntrust
    ) {
        getStateData(page)?.addPageEntrust(pageStateKey, stateEntrust)
    }

    /**
     * 绑定页面
     */
    override fun bind(page: LifecycleOwner) {
        val stateData = StateData(page)
        stateMap[page] = stateData
        page.lifecycle.addObserver(stateData)
    }

    private fun getStateData(page: LifecycleOwner): StateData? {
        return stateMap[page]
    }

    /**
     * 执行页面状态委托的逻辑
     * 由于 UI 只能在主线程进行，因此对线程进行判断，如果是主线程直接执行，否则切换到主线程执行
     */
    override fun executeEntrust(page: LifecycleOwner, stateKey: Int) {
        val stateData = getStateData(page)
        stateData?.let {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                it.getPageEntrust(stateKey)?.onPageChange(page, stateData.getLifecycleEvent())
            } else {
                mHandle.post {
                    it.getPageEntrust(stateKey)?.onPageChange(page, stateData.getLifecycleEvent())
                }
            }
        }
    }

    override fun clearAllPageState() {
        stateMap.clear()
    }

}