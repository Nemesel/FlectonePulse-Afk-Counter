package flectonepulseafkcounter;

import net.flectone.pulse.model.entity.FPlayer;
import net.flectone.pulse.service.FPlayerService;
import net.flectone.pulse.service.SocialService;
import net.flectone.pulse.util.constant.SettingText;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public final class PulseService {

    private static final long UPDATE_PERIOD = 20L;

    private final JavaPlugin plugin;
    private final SocialService socialService;
    private final FPlayerService fPlayerService;

    private volatile PulseStats stats = new PulseStats(0, 0, 0);

    private BukkitTask updateTask;

    public PulseService(
            JavaPlugin plugin,
            SocialService socialService,
            FPlayerService fPlayerService
    ) {
        this.plugin = plugin;
        this.socialService = socialService;
        this.fPlayerService = fPlayerService;
    }

    public void start() {

        update();

        updateTask = Bukkit.getScheduler().runTaskTimer(
                plugin,
                this::update,
                UPDATE_PERIOD,
                UPDATE_PERIOD
        );

    }

    public void stop() {

        if (updateTask != null) {
            updateTask.cancel();
            updateTask = null;
        }

    }

    public PulseStats getStats() {
        return stats;
    }

    private void update() {

        List<FPlayer> players = fPlayerService.getPlatformFPlayers();

        int online = players.size();
        int afk = 0;

        for (FPlayer player : players) {

            if (socialService.getSetting(player, SettingText.AFK_SUFFIX) != null) {
                afk++;
            }

        }

        stats = new PulseStats(
                online,
                afk,
                online - afk
        );

    }

}