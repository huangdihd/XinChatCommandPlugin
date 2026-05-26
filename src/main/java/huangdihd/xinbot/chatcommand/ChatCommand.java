package huangdihd.xinbot.chatcommand;

import lombok.Getter;
import org.geysermc.mcprotocollib.auth.GameProfile;

@Getter
public abstract class ChatCommand {
    private final String name;
    private final String[] aliases;
    private final String description;
    private final String usage;
    private final boolean ownerOnly;

    public ChatCommand(String name, String description, String usage, boolean ownerOnly, String... aliases) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.ownerOnly = ownerOnly;
        this.aliases = aliases;
    }

    public ChatCommand(String name, String description, String usage, String... aliases) {
        this(name, description, usage, false, aliases);
    }

    /**
     * Executes the chat command.
     * @param sender The sender of the command
     * @param label The triggered command name
     * @param args The command arguments
     * @param isPrivate Whether the command was sent in private chat
     */
    public abstract void onCommand(GameProfile sender, String label, String[] args, boolean isPrivate);
}
