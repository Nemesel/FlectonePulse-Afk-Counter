package flectonepulseafkcounter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandFPAfkCounter implements CommandExecutor {

    private final PulseAfkCounterPlugin plugin;

    public CommandFPAfkCounter(PulseAfkCounterPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (args.length == 0) {
            sendMain(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "help" -> sendHelp(sender);

            case "version" -> sendVersion(sender);

            case "diagnostics" -> sendDiagnostics(sender);

            default -> sendMain(sender);

        }

        return true;
    }

    private void sendMain(CommandSender sender) {

        sender.sendMessage(ChatColor.GOLD + "FlectonePulse-Afk-Counter "
                + ChatColor.YELLOW
                + "v"
                + plugin.getDescription().getVersion());

        sender.sendMessage("");

        sender.sendMessage(ChatColor.YELLOW + "Commands:");

        sender.sendMessage(ChatColor.GRAY + " • "
                + ChatColor.WHITE
                + "/fpafkcounter help");

        sender.sendMessage(ChatColor.GRAY + " • "
                + ChatColor.WHITE
                + "/fpafkcounter version");

        sender.sendMessage(ChatColor.GRAY + " • "
                + ChatColor.WHITE
                + "/fpafkcounter diagnostics");

    }

    private void sendHelp(CommandSender sender) {

        sender.sendMessage("");
		sender.sendMessage(ChatColor.GOLD + "FlectonePulse-Afk-Counter Help");
        sender.sendMessage("");

        sender.sendMessage(ChatColor.YELLOW + "General:");

        sender.sendMessage(ChatColor.WHITE + "%fpafkcounter_online%");
        sender.sendMessage(ChatColor.GRAY + "Online players");

        sender.sendMessage(ChatColor.WHITE + "%fpafkcounter_afk_count%");
        sender.sendMessage(ChatColor.GRAY + "AFK players");
		
        sender.sendMessage(ChatColor.WHITE + "%fpafkcounter_not_afk_count%");
        sender.sendMessage(ChatColor.GRAY + "Non-AFK players");

        sender.sendMessage("");
		sender.sendMessage("");
		sender.sendMessage("");

        sender.sendMessage(ChatColor.YELLOW + "Percentages:");

        sender.sendMessage(ChatColor.WHITE + "%fpafkcounter_afk_percent_<digits>%");
        sender.sendMessage(ChatColor.GRAY + "AFK percentage");
		
        sender.sendMessage(ChatColor.WHITE + "%fpafkcounter_not_afk_percent_<digits>%");
        sender.sendMessage(ChatColor.GRAY + "Non-AFK percentage");

        sender.sendMessage("");

        sender.sendMessage(ChatColor.YELLOW + "Example:");
		
		sender.sendMessage(ChatColor.WHITE + "%fpafkcounter_afk_percent%");
        sender.sendMessage(ChatColor.GRAY + "No decimal places");
		
        sender.sendMessage(ChatColor.WHITE + "%fpafkcounter_afk_percent_5%");
        sender.sendMessage(ChatColor.GRAY + "Five decimal places");
		
		sender.sendMessage("");

    }

    private void sendVersion(CommandSender sender) {

        sender.sendMessage(ChatColor.GOLD + "FlectonePulse-Afk-Counter");

        sender.sendMessage(ChatColor.YELLOW + "Version: "
                + ChatColor.WHITE
                + plugin.getDescription().getVersion());

        sender.sendMessage(ChatColor.YELLOW + "Author: "
                + ChatColor.WHITE
                + String.join(", ", plugin.getDescription().getAuthors()));

        sender.sendMessage(ChatColor.YELLOW + "Description: "
                + ChatColor.WHITE
                + plugin.getDescription().getDescription());

    }

    private void sendDiagnostics(CommandSender sender) {

        sender.sendMessage(ChatColor.GOLD + "Diagnostics");
        sender.sendMessage("");

        sender.sendMessage(ChatColor.YELLOW + "Java: "
                + ChatColor.WHITE
                + System.getProperty("java.version"));

        sender.sendMessage(ChatColor.YELLOW + "Server: "
                + ChatColor.WHITE
                + plugin.getServer().getVersion());

        sender.sendMessage(ChatColor.YELLOW + "FlectonePulse: "
                + (plugin.getServer().getPluginManager().getPlugin("FlectonePulse") != null
                ? ChatColor.GREEN + "Loaded"
                : ChatColor.RED + "Missing"));

        sender.sendMessage(ChatColor.YELLOW + "PlaceholderAPI: "
                + (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null
                ? ChatColor.GREEN + "Loaded"
                : ChatColor.RED + "Missing"));

    }

}