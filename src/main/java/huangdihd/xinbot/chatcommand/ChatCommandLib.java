package huangdihd.xinbot.chatcommand;

import lombok.Getter;
import xin.bbtt.mcbot.Bot;
import xin.bbtt.mcbot.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatCommandLib implements Plugin {
    private static final Logger log = LoggerFactory.getLogger(ChatCommandLib.class);
    
    @Getter
    private static ChatCommandLib instance;
    private ChatCommandManager chatCommandManager;

    public ChatCommandManager getCommandManager() {
        return chatCommandManager;
    }

    @Override
    public void onLoad() {
        instance = this;
        chatCommandManager = new ChatCommandManager();
        log.info("ChatCommandLib loaded!");
    }

    @Override
    public void onEnable() {
        chatCommandManager.registerCommand(new HelpCommand(chatCommandManager));

        ChatListener listener = new ChatListener(chatCommandManager);
        Bot.INSTANCE.getPluginManager().events().registerEvents(listener, this);
        
        log.info("ChatCommandLib enabled! Other plugins can now register chat commands.");
    }

    @Override
    public void onDisable() {
        Bot.INSTANCE.getPluginManager().events().unregisterAll(this);
        log.info("ChatCommandLib disabled!");
    }

    @Override
    public void onUnload() {
        instance = null;
        log.info("ChatCommandLib unloaded!");
    }
}
