package com.slayercapereminder;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

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
}
