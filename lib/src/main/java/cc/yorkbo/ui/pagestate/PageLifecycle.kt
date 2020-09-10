package cc.yorkbo.ui.pagestate

/**
 * @author BoWei
 * 2020/9/9 5:35 PM
 * 页面生命周期
 */
enum class PageLifecycle {
    CREATE,
    START,
    RESUME,
    PAUSE,
    STOP,
    /**
     * Fragment的hide状态
     */
    HIDE,
    /**
     * Fragment的show状态
     */
    SHOW
}