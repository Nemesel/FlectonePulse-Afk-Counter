package flectonepulseafkcounter;

import net.flectone.pulse.BukkitFlectonePulse;
import net.flectone.pulse.service.FPlayerService;
import net.flectone.pulse.service.SocialService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class PulseAfkCounterPlugin extends JavaPlugin {

    private static final String FLECTONE_PLUGIN = "FlectonePulse";
    private static final String PLACEHOLDER_PLUGIN = "PlaceholderAPI";

    private PulseService pulseService;

    @Override
    public void onEnable() {

        line();
        info("Starting FlectonePulse-Afk-Counter...");
        info("Version : " + getDescription().getVersion());
        info("Author  : " + String.join(", ", getDescription().getAuthors()));
        line();

        try {

            if (!checkDependencies()) {

                error("Plugin startup aborted.");

                Bukkit.getPluginManager().disablePlugin(this);

                return;

            }

            line();

            info("Initializing services...");

            BukkitFlectonePulse pulse =
                    (BukkitFlectonePulse) Bukkit.getPluginManager().getPlugin(FLECTONE_PLUGIN);

            SocialService socialService =
                    pulse.get(SocialService.class);

            FPlayerService fPlayerService =
                    pulse.get(FPlayerService.class);

            ok("SocialService");

            ok("FPlayerService");

            pulseService = new PulseService(
                    this,
                    socialService,
                    fPlayerService
            );

            pulseService.start();

            ok("PulseService");

            PulseExpansion expansion =
                    new PulseExpansion(this, pulseService);

            if (!expansion.register()) {

                error("Unable to register PlaceholderAPI expansion.");

                Bukkit.getPluginManager().disablePlugin(this);

                return;

            }

            ok("Placeholder expansion");

            if (getCommand("fpafkcounter") != null) {

                getCommand("fpafkcounter")
                        .setExecutor(new CommandFPAfkCounter(this));

                ok("Command /fpafkcounter");

            } else {

                warn("Command /fpafkcounter is missing in plugin.yml");

            }

            line();

            ok("Plugin enabled successfully.");

        }
        catch (NoSuchMethodError ex) {

            line();

            error("FlectonePulse API mismatch.");
            warn("This plugin was compiled against another version of FlectonePulse.");
            warn("Please rebuild the plugin.");

            Bukkit.getPluginManager().disablePlugin(this);

        }
        catch (Throwable ex) {

            line();

            error("Unexpected startup error.");
            error(ex.getClass().getSimpleName());

            getLogger().log(Level.SEVERE, "Stacktrace:", ex);

            Bukkit.getPluginManager().disablePlugin(this);

        }

    }

    @Override
    public void onDisable() {

        if (pulseService != null) {

            pulseService.stop();

        }

        info("Plugin disabled.");

    }

    private boolean checkDependencies() {

        info("Checking dependencies...");

        Plugin pulse =
                Bukkit.getPluginManager().getPlugin(FLECTONE_PLUGIN);

        if (!(pulse instanceof BukkitFlectonePulse)) {

            error("FlectonePulse not found.");
            warn("Download FlectonePulse before using this plugin.");

            return false;

        }

        ok("FlectonePulse");

        Plugin placeholder =
                Bukkit.getPluginManager().getPlugin(PLACEHOLDER_PLUGIN);

        if (placeholder == null) {

            error("PlaceholderAPI not found.");
            warn("Download PlaceholderAPI before using this plugin.");

            return false;

        }

        ok("PlaceholderAPI");

        return true;

    }

    private void info(String message) {

        getLogger().info(message);

    }

    private void ok(String message) {

        getLogger().info("✔ " + message);

    }

    private void warn(String message) {

        getLogger().warning("⚠ " + message);

    }

    private void error(String message) {

        getLogger().severe("✘ " + message);

    }

    private void line() {

        getLogger().info("");

    }

}