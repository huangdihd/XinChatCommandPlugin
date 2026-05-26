package huangdihd.xinbot.chatcommand;

import org.geysermc.mcprotocollib.auth.GameProfile;

public interface ChatCommandExecutor {
    /**
     * Executes the chat command.
     * @param sender The sender of the command
     * @param label The triggered command name
     * @param args The command arguments
     * @param isPrivate Whether the command was sent in private chat
     */
    void onCommand(GameProfile sender, String label, String[] args, boolean isPrivate);
}
