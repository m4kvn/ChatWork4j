# ChatWork4j

**ChatWork4j** は [ChatWork API](http://developer.chatwork.com/ja/) を *Java* で利用するためのライブラリです。
直接ダウンロードしてビルドパスに追加するか、Maven や Gradle などの依存関係に追加する形で利用できます。

## ダウンロード

| ChatWork4j Version | ChatWork API Version |
| :----------------: | :------------------: |
| [1.0](https://github.com/MasahiroSaito/ChatWork4j/raw/master/repo/com/MasahiroSaito/ChatWork4j/1.0/ChatWork4j-1.0.jar) | v1 |

## 依存関係に追加する

| repository url                                              |
| :---------------------------------------------------------- |
| https://github.com/MasahiroSaito/ChatWork4j/raw/master/repo |

| dependency |                   |
| :--------- | :---------------- |
| groupId    | com.MasahiroSaito |
| artifactId | ChatWork4j        |
| version    | 1.0               |

### Gradle Example build.gradle

Gradle の build.gradle に以下をそれぞれ追加する

#### repositories

```gradle
    maven {
        name = 'chatwork4j-repo'
        url = 'https://github.com/MasahiroSaito/ChatWork4j/raw/master/repo'
    }
```

#### dependencies

```gradle
    compile 'com.MasahiroSaito:ChatWork4j:1.0'
```

### Maven Example pom.xml

Maven の pom.xml に以下をそれぞれ追加する

#### repositories

```xml
  <repository>
    <id>core-repo</id>
    <url>https://github.com/MasahiroSaito/Core/raw/master/mvn-repo</url>
  </repository>
```

#### dependencies

```xml
  <dependency>
    <groupId>com.Nepian.Spigot</groupId>
    <artifactId>Core</artifactId>
    <version>1.0</version>
  </dependency>
```