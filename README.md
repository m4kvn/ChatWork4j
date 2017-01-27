# ChatWork4j

**ChatWork4j** is the Library for _Java_ to use [ChatWork API](http://developer.chatwork.com/ja/).

You can use **ChatWork4j** by adding it to your _maven_ or _gradle_ project.

## Downloads

| ChatWork4j Version | ChatWork API Version |
| :----------------: | :------------------: |
| [1.2](https://github.com/MasahiroSaito/ChatWork4j/raw/master/repo/com/MasahiroSaito/ChatWork4j/1.2/ChatWork4j-1.2.jar) | v1 |
| [2.0](https://github.com/MasahiroSaito/ChatWork4j/raw/master/repo/com/MasahiroSaito/ChatWork4j/2.0/ChatWork4j-2.0.jar) | v2 |

## Javadoc

[https://masahirosaito.github.io/ChatWork4j/](https://masahirosaito.github.io/ChatWork4j/)

## How to add to dependency

| repository url                                              |
| :---------------------------------------------------------- |
| https://github.com/MasahiroSaito/ChatWork4j/raw/master/repo |

| dependency |                   |
| :--------- | :---------------- |
| groupId    | com.MasahiroSaito |
| artifactId | ChatWork4j        |
| version    | 2.0               |

### Gradle in [build.gradle]()

#### repositories

```gradle
    maven {
        name = 'chatwork4j-repo'
        url = 'https://github.com/MasahiroSaito/ChatWork4j/raw/master/repo'
    }
```

#### dependencies

```gradle
    compile 'com.MasahiroSaito:ChatWork4j:2.0'
```

### Maven in [pom.xml]()

#### repositories

```xml
  <repository>
    <id>chatwork4j-repo</id>
    <url>https://github.com/MasahiroSaito/ChatWork4j/raw/master/repo</url>
  </repository>
```

#### dependencies

```xml
  <dependency>
    <groupId>com.MasahiroSaito</groupId>
    <artifactId>ChatWork4j</artifactId>
    <version>2.0</version>
  </dependency>
```