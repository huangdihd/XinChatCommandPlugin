# XinChatCommandPlugin

A robust and extensible chat command framework for [XinBot](https://github.com/huangdihd/xinbot).

## Features

- **Asynchronous Execution**: Commands are executed in separate threads to prevent blocking the main bot loop.
- **Easy Registration**: Simple API to register commands and aliases.
- **Permission Management**: Support for owner-only commands.
- **Built-in Help**: Automatically generated help information for all registered commands.
- **JitPack Support**: Easily include this as a dependency in your project.

## Installation

### Prerequisites
- Java 17 or higher.
- [XinBot](https://github.com/huangdihd/xinbot) installed.

### Download
Download the latest JAR from the [Releases](https://github.com/huangdihd/XinChatCommandPlugin/releases) page and place it in your bot's plugins folder.

### Dependency (Maven)
To use this library in your project, add the following to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.huangdihd</groupId>
        <artifactId>XinChatCommandPlugin</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## Creating a Command

Extend the `ChatCommand` class and implement the `onCommand` method:

```java
public class MyCommand extends ChatCommand {
    public MyCommand() {
        super("mycmd", "A simple test command", "!mycmd", false, "test");
    }

    @Override
    public void onCommand(GameProfile sender, String label, String[] args, boolean isPrivate) {
        // Your logic here
    }
}
```

## License

This project is licensed under the [GPLv3 License](LICENSE).
