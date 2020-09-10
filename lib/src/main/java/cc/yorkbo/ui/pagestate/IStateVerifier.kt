package cc.yorkbo.ui.pagestate

import androidx.lifecycle.LifecycleOwner

/**
 *
 * @author BoWei
 * 2020/9/4 4:57 PM
 */
interface IStateVerifier {

    /**
     * 添加Fragment状态
     * @param pageName 页面名字，用于寻找对应的状态实体
     * @param pageStateKey 状态对应的 key，为整型值，每个页面的每个状态对应的 key 必须是不同的
     * @param stateEntrust 页面状态的委托接口，负责处理对应的状态处理逻辑
     */
    fun addState(
        page: LifecycleOwner,
        pageStateKey: Int,
        stateEntrust: IStateEntrust
    )

    /**
     * 绑定页面
     */
    fun bind(page: LifecycleOwner)

    /**
     * 执行对应页面的状态委托，obj 需要传入对应的实例
     */
    fun executeEntrust(page: LifecycleOwner, stateKey: Int)

    /**
     * 清除所有页面的状态和实例
     * 用于在退出App时调用
     * 防止内存泄露
     * 请谨慎确认调用位置！
     */
    fun clearAllPageState()

}