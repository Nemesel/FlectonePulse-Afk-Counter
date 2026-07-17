package flectonepulseafkcounter;

public record PulseStats(
        int online,
        int afk,
        int notAfk
) {
}