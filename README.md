# MangoStateVerifier

#### 介绍
一款页面管理框架，可以极大的解耦 Fragment 和 Activity 的 UI 切换逻辑，并且让 UI 切换逻辑更方便进行复用。

#### 依赖

```
api com.github.YorkBo:MangoStateVerifier:v0.1.0
```

#### 使用简单，侵入性低

只需要在需要状态切换的 Activity和 Fragment 中进行绑定，然后添加对应状态委托即可，然后在对应需要调用的地方调用状态即可，框架会自动判断是哪个页面的委托，根据生命周期来显示，并且会自动切换至主线程执行。

**再也不用担心页面销毁，然后延时执行的逻辑里面修改 View 导致空指针等异常啦！**

##### 示例代码

```kotlin
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       
        StateVerifier.instance.bind(this)

        StateVerifier.instance.addState(this, 0, UIEntrust1())
        StateVerifier.instance.addState(this, 1, UIEntrust2())

}
```

```kotlin
// 执行对应的
StateVerifier.instance.executeEntrust(this, 0)
```

