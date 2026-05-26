package huangdihd.xinbot.chatcommand;

import org.geysermc.mcprotocollib.auth.GameProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.bbtt.mcbot.Bot;
import xin.bbtt.mcbot.event.EventHandler;
import xin.bbtt.mcbot.event.Listener;
import xin.bbtt.mcbot.events.PrivateChatEvent;
import xin.bbtt.mcbot.events.PublicChatEvent;

public class ChatListener implements Listener {
    private static final Logger log = LoggerFactory.getLogger(ChatListener.class);
    
    private static final String PREFIX = "!";
    
    private final ChatCommandManager manager;

    public ChatListener(ChatCommandManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPublicChat(PublicChatEvent event) {
        handleChat(event.getSender(), event.getMessage(), false);
    }

    @EventHandler
    public void onPrivateChat(PrivateChatEvent event) {
        handleChat(event.getSender(), event.getMessage(), true);
    }

    private void handleChat(GameProfile senderProfile, String message, boolean isPrivate) {
        String senderName = senderProfile.getName();

        // Ignore messages sent by the bot itself
        if (Bot.INSTANCE.getProtocol().getProfile().getName().equals(senderName)) {
            return;
        }

        if (!message.startsWith(PREFIX)) {
            return;
        }

        String commandLine = message.substring(PREFIX.length()).trim();
        log.info("Received {} chat command from {}: {}", isPrivate ? "private" : "public", senderName, commandLine);
        manager.executeCommand(senderProfile, commandLine, isPrivate);
    }
}
