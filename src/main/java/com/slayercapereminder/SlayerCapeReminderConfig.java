package com.slayercapereminder;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;
import net.runelite.client.config.Units;

@ConfigGroup("slayercapereminder")
public interface SlayerCapeReminderConfig extends Config
{
	@ConfigItem(
		keyName = "reminderText",
		name = "Reminder text",
		description = "Text displayed in the overlay when your Slayer Cape is absent.",
		position = 0
	)
	default String reminderText()
	{
		return "Get your Slayer Cape!";
	}

	@ConfigItem(
		keyName = "warningColor",
		name = "Text color",
		description = "Color of the reminder text.",
		position = 1
	)
	default Color warningColor()
	{
		return Color.RED;
	}

	@ConfigItem(
		keyName = "backgroundColor",
		name = "Background color",
		description = "Color of the overlay box background.",
		position = 2
	)
	default Color backgroundColor()
	{
		return new Color(70, 61, 50, 156);
	}

	@ConfigItem(
		keyName = "flashEnabled",
		name = "Flash background",
		description = "Flashes the overlay background between the background color and the flash color.",
		position = 3
	)
	default boolean flashEnabled()
	{
		return false;
	}

	@ConfigItem(
		keyName = "flashColor",
		name = "Flash color",
		description = "Second background color used when flashing is enabled.",
		position = 4
	)
	default Color flashColor()
	{
		return new Color(150, 0, 0, 156);
	}

	@ConfigSection(
		name = "Proximity Alert",
		description = "Show the overlay when approaching a slayer master without your cape and without an active task.",
		position = 5
	)
	String proximitySection = "proximitySection";

	@ConfigItem(
		keyName = "onlyAt99",
		name = "Only at 99 Slayer",
		description = "Only show the overlay if you have 99 Slayer.",
		position = 6
	)
	default boolean onlyAt99()
	{
		return true;
	}

	@ConfigItem(
		keyName = "proximityDuradel",
		name = "Duradel",
		description = "Alert when approaching Duradel (or Kuradal) in Shilo Village.",
		section = "proximitySection",
		position = 0
	)
	default boolean proximityDuradel()
	{
		return true;
	}

	@ConfigItem(
		keyName = "proximityNieve",
		name = "Nieve / Steve",
		description = "Alert when approaching Nieve (or Steve) in the Gnome Stronghold.",
		section = "proximitySection",
		position = 1
	)
	default boolean proximityNieve()
	{
		return true;
	}

	@ConfigItem(
		keyName = "proximityKonar",
		name = "Konar quo Maten",
		description = "Alert when approaching Konar on Mount Karuulm.",
		section = "proximitySection",
		position = 2
	)
	default boolean proximityKonar()
	{
		return true;
	}

	@ConfigItem(
		keyName = "proximityKrystilia",
		name = "Krystilia",
		description = "Alert when approaching Krystilia in Edgeville.",
		section = "proximitySection",
		position = 3
	)
	default boolean proximityKrystilia()
	{
		return true;
	}

	@ConfigItem(
		keyName = "dismissDelay",
		name = "Auto-dismiss delay",
		description = "Seconds before the overlay automatically hides after a task is completed. Set to 0 to never auto-dismiss.",
		position = 6
	)
	@Range(min = 0)
	@Units(Units.SECONDS)
	default int dismissDelay()
	{
		return 120;
	}
}
