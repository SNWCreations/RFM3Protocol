# RFM3 Protocol

此仓库是逃走中插件第三代提供的协议，用于允许 Minecraft 客户端从实现了此协议的服务端获得游戏数据。

## 导入

你可以使用 Maven/Gradle 导入此 API 以调用协议。

此仓库的工件在 JitPack 编译，因此需要添加 JitPack 仓库:
```groovy
repositories {
    maven {
        name = 'JitPack'
        url = 'https://jitpack.io'
    }
}
```

工件坐标: `com.github.SNWCreations:RFM3Protocol:main-SNAPSHOT`

如对于 Gradle 的完整示例:
```groovy
repositories {
    maven {
        name = 'JitPack'
        url = 'https://jitpack.io'
    }
}

dependencies {
    compileOnly 'com.github.SNWCreations:RFM3Protocol:main-SNAPSHOT'
}
```

## 许可

RFM3Protocol - 逃走中插件第三代的网络协议

Copyright (C) 2024, 2025 SNWCreations, 使用 Apache License 2.0 。

关于许可条款，详见 `LICENSE` 文件。
