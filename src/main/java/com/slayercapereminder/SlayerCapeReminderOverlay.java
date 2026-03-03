package com.slayercapereminder;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

public class SlayerCapeReminderOverlay extends OverlayPanel
{
	private final SlayerCapeReminderPlugin plugin;
	private final SlayerCapeReminderConfig config;

	@Inject
	public SlayerCapeReminderOverlay(SlayerCapeReminderPlugin plugin, SlayerCapeReminderConfig config)
	{
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.TOP_LEFT);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (plugin.capePresent || !plugin.taskComplete || (config.onlyAt99() && !plugin.isLevel99))
		{
			return null;
		}

		boolean flash = config.flashEnabled() && (System.currentTimeMillis() / 600) % 2 == 0;
		panelComponent.setBackgroundColor(flash ? config.flashColor() : config.backgroundColor());
		panelComponent.getChildren().add(LineComponent.builder()
			.left(config.reminderText())
			.leftColor(config.warningColor())
			.build());

		return super.render(graphics);
	}
}
