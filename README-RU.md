
[🇬🇧 English](README.md) | **🇷🇺 Русский**

# FlectonePulse AFK Counter

Добавляет плейсхолдеры для получения статистики AFK из FlectonePulse.

## Возможности

- Количество AFK-игроков
- Количество игроков онлайн
- Процент AFK-игроков

## Требования

- Java 25+
- PlaceholderAPI
- FlectonePulse 1.11+

## Команды

```text
/fpafkcounter
/fpafkcounter version
/fpafkcounter diagnostics
```

## Плейсхолдеры

| Плейсхолдер | Описание |
|-------------|----------|
| `%fpafkcounter_online%` | Количество игроков онлайн |
| `%fpafkcounter_afk_count%` | Количество AFK-игроков |
| `%fpafkcounter_not_afk_count%` | Количество не-AFK игроков |
| `%fpafkcounter_afk_percent%` | Процент AFK-игроков (без дробной части) |
| `%fpafkcounter_not_afk_percent%` | Процент не-AFK игроков (без дробной части) |
| `%fpafkcounter_afk_percent_<digits>%` | Процент AFK-игроков с указанным количеством знаков после запятой |
| `%fpafkcounter_not_afk_percent_<digits>%` | Процент не-AFK игроков с указанным количеством знаков после запятой |

### Примеры

| Плейсхолдер | Пример |
|-------------|-----------|
| `%fpafkcounter_afk_percent%` | `25` |
| `%fpafkcounter_afk_percent_1%` | `25.3` |
| `%fpafkcounter_afk_percent_2%` | `25.35` |
| `%fpafkcounter_afk_percent_5%` | `25.34500` |
