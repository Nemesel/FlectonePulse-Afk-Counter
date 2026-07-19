🇬🇧 **English** | [🇷🇺 Русский](README-RU.md)

# FlectonePulse AFK Counter

Adds placeholders for AFK statistics using FlectonePulse.

## Features:

- AFK player count
- Online player count
- AFK percentage

## Requirements:

- Java 25+
- [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI)
- [FlectonePulse 1.11+](https://github.com/Flectone/FlectonePulse)

## Installation

1. Download `FlectonePulse-Afk-Counter.jar`.
2. Place the file into your server's `plugins` folder.
3. Restart the server.
4. Use the provided PlaceholderAPI placeholders in supported plugins.

## Commands:

```text
/fpafkcounter
/fpafkcounter version
/fpafkcounter diagnostics
```

## Placeholders:

| Placeholder | Description |
|-------------|-------------|
| `%fpafkcounter_online%` | Online players |
| `%fpafkcounter_afk_count%` | AFK players |
| `%fpafkcounter_not_afk_count%` | Non-AFK players |
| `%fpafkcounter_afk_percent%` | AFK percentage (0 decimal places) |
| `%fpafkcounter_not_afk_percent%` | Non-AFK percentage (0 decimal places) |
| `%fpafkcounter_afk_percent_<digits>%` | AFK percentage with custom decimal places |
| `%fpafkcounter_not_afk_percent_<digits>%` | Non-AFK percentage with custom decimal places |

### Examples:

| Placeholder | Output |
|------------|--------|
| `%fpafkcounter_afk_percent%` | `25` |
| `%fpafkcounter_afk_percent_1%` | `25.3` |
| `%fpafkcounter_afk_percent_2%` | `25.35` |
| `%fpafkcounter_afk_percent_5%` | `25.34500` |
