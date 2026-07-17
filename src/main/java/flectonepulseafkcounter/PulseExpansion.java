package flectonepulseafkcounter;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PulseExpansion extends PlaceholderExpansion {

    private static final String IDENTIFIER = "fpafkcounter";
    private static final String ERROR_TOO_MANY_DECIMALS =
            "Too many decimal places (maximum: 10)";
    private static final int MAX_DECIMALS = 10;

    private static final Map<Integer, DecimalFormat> FORMATTERS =
            new ConcurrentHashMap<>();

    static {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.ROOT);

        for (int i = 0; i <= MAX_DECIMALS; i++) {

            StringBuilder pattern = new StringBuilder("0");

            if (i > 0) {
                pattern.append('.');
                pattern.append("0".repeat(i));
            }

            DecimalFormat format = new DecimalFormat(pattern.toString(), symbols);
            format.setGroupingUsed(false);
            format.setRoundingMode(RoundingMode.HALF_UP);

            FORMATTERS.put(i, format);
        }
    }

    private final JavaPlugin plugin;
    private final PulseService pulseService;

    public PulseExpansion(
            JavaPlugin plugin,
            PulseService pulseService
    ) {
        this.plugin = plugin;
        this.pulseService = pulseService;
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors());
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(
            OfflinePlayer player,
            String params
    ) {

        PulseStats stats = pulseService.getStats();

        switch (params) {

            case "online":
                return Integer.toString(stats.online());

            case "afk_count":
                return Integer.toString(stats.afk());

            case "not_afk_count":
                return Integer.toString(stats.notAfk());

            case "afk_percent":
                return formatPercent(stats.afk(), stats.online(), 0);

            case "not_afk_percent":
                return formatPercent(stats.notAfk(), stats.online(), 0);

        }

        if (params.startsWith("afk_percent_")) {

            Integer decimals = parseDecimals(params, "afk_percent_");

            if (decimals == null) {
                return null;
            }

            if (decimals > MAX_DECIMALS) {
                return ERROR_TOO_MANY_DECIMALS;
            }

            return formatPercent(
                    stats.afk(),
                    stats.online(),
                    decimals
            );
        }

        if (params.startsWith("not_afk_percent_")) {

            Integer decimals = parseDecimals(params, "not_afk_percent_");

            if (decimals == null) {
                return null;
            }

            if (decimals > MAX_DECIMALS) {
                return ERROR_TOO_MANY_DECIMALS;
            }

            return formatPercent(
                    stats.notAfk(),
                    stats.online(),
                    decimals
            );
        }

        return null;
    }

    private static Integer parseDecimals(
            String value,
            String prefix
    ) {

        try {
            return Integer.parseInt(value.substring(prefix.length()));
        } catch (NumberFormatException ex) {
            return null;
        }

    }

    private static String formatPercent(
            int value,
            int total,
            int decimals
    ) {

        if (total == 0) {
            return FORMATTERS.get(decimals).format(0D);
        }

        double percent = value * 100.0D / total;

        return FORMATTERS.get(decimals).format(percent);
    }

}