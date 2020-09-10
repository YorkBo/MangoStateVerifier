package cc.yorkbo.ui.pagestate

import androidx.lifecycle.LifecycleOwner

/**
 * Description:
 *
 * @author BoWei
 * 2020/9/8 10:29 AM
 */
class StateVerifier : IStateVerifier {

    companion object {
        val instance: StateVerifier by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            StateVerifier()
        }
    }

    private val pageStateManager by lazy {
        PageStateManager()
    }

    override fun addState(page: LifecycleOwner, pageStateKey: Int, stateEntrust: IStateEntrust) {
        pageStateManager.addState(page, pageStateKey, stateEntrust)
    }

    override fun bind(page: LifecycleOwner) {
        pageStateManager.bind(page)
    }

    override fun executeEntrust(page: LifecycleOwner, stateKey: Int) {
        pageStateManager.executeEntrust(page, stateKey)
    }

    override fun clearAllPageState() {
        pageStateManager.clearAllPageState()
    }

}