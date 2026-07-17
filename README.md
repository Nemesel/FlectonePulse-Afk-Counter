FlectonePulse-Afk-Counter

Adds PlaceholderAPI placeholders for AFK statistics using FlectonePulse.

Features:
• AFK player count
• Online player count
• AFK percentage
• Custom decimal precision

Requirements:
• Java 25+
• PlaceholderAPI 
• FlectonePulse 1.11+

Commands:
/fpafkcounter
/fpafkcounter version
/fpafkcounter diagnostics



Placeholders:
Online players: %fpafkcounter_online%

AFK players: %fpafkcounter_afk_count%
		
Non-AFK players: %fpafkcounter_not_afk_count%


AFK percentage: %fpafkcounter_afk_percent_<digits>%
Non-AFK percentage: %fpafkcounter_not_afk_percent_<digits>%

Example:
No decimal places (25): %fpafkcounter_afk_percent%
Five decimal places (25.34500) : %fpafkcounter_afk_percent_5%