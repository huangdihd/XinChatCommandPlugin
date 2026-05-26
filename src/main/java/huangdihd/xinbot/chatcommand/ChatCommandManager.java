package huangdihd.xinbot.chatcommand;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.geysermc.mcprotocollib.auth.GameProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.bbtt.mcbot.Bot;
import xin.bbtt.mcbot.command.CommandManager;

public class ChatCommandManager {
    private static final Logger log = LoggerFactory.getLogger(ChatCommandManager.class);
    private final Map<String, ChatCommand> commands = new HashMap<>();

    public void registerCommand(ChatCommand command) {
        commands.put(command.getName().toLowerCase(), command);
        for (String alias : command.getAliases()) {
            commands.put(alias.toLowerCase(), command);
        }
    }

    public void unregisterCommand(String name) {
        ChatCommand cmd = commands.remove(name.toLowerCase());
        if (cmd == null) return;
        
        for (String alias : cmd.getAliases()) {
            commands.remove(alias.toLowerCase());
        }
    }

    public Collection<ChatCommand> getRegisteredCommands() {
        return commands.values().stream().distinct().toList();
    }

    public void executeCommand(GameProfile sender, String commandLine, boolean isPrivate) {
        List<String> tokens = CommandManager.tokenize(commandLine);
        if (tokens.isEmpty()) return;

        String label = tokens.get(0).toLowerCase();
        ChatCommand command = commands.get(label);

        if (command == null) return;

        if (command.isOwnerOnly()) {
            String owner = Bot.INSTANCE.getConfig().getConfigData().getOwner();
            if (owner == null || !owner.equals(sender.getName())) {
                log.warn("User {} tried to execute owner-only command: {}", sender.getName(), label);
                return;
            }
        }

        String[] args = tokens.size() > 1
                ? tokens.subList(1, tokens.size()).toArray(new String[0])
                : new String[0];
        
        CompletableFuture.runAsync(() -> {
            try {
                command.onCommand(sender, label, args, isPrivate);
            } catch (Exception e) {
                log.error("Error executing chat command", e);
            }
        });
    }
}
