package com.example.chatcommand;

import org.geysermc.mcprotocollib.auth.GameProfile;
import xin.bbtt.mcbot.Bot;

import java.util.Collection;

public class HelpCommand extends ChatCommand {
    
    private final ChatCommandManager manager;

    public HelpCommand(ChatCommandManager manager) {
        super("help", "List all available chat commands", "!help [command]", "?");
        this.manager = manager;
    }

    @Override
    public void onCommand(GameProfile sender, String label, String[] args, boolean isPrivate) {
        Collection<ChatCommand> commands = manager.getRegisteredCommands();
        String response;

        if (args.length == 0) {
            response = buildCommandList(commands);
        } else {
            response = buildCommandDetail(commands, args[0].toLowerCase());
        }

        if (isPrivate) {
            Bot.INSTANCE.sendChatMessage("/msg " + sender.getName() + " " + response);
        } else {
            Bot.INSTANCE.sendChatMessage(response);
        }
    }

    private String buildCommandList(Collection<ChatCommand> commands) {
        StringBuilder builder = new StringBuilder("Available commands: ");
        for (ChatCommand cmd : commands) {
            builder.append("!").append(cmd.getName()).append(" ");
        }
        return builder.toString();
    }

    private String buildCommandDetail(Collection<ChatCommand> commands, String target) {
        ChatCommand found = findCommand(commands, target);
        
        if (found == null) {
            return "Command not found: " + target;
        }

        return String.format("Command: !%s - %s | Usage: %s", 
                found.getName(), found.getDescription(), found.getUsage());
    }

    private ChatCommand findCommand(Collection<ChatCommand> commands, String target) {
        for (ChatCommand cmd : commands) {
            if (cmd.getName().equalsIgnoreCase(target)) {
                return cmd;
            }
            for (String alias : cmd.getAliases()) {
                if (alias.equalsIgnoreCase(target)) {
                    return cmd;
                }
            }
        }
        return null;
    }
}
