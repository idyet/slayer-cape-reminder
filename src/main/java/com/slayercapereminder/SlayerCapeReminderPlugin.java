package com.slayercapereminder;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import net.runelite.api.Skill;
import net.runelite.api.VarPlayer;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.Text;
import net.runelite.client.plugins.slayer.SlayerPlugin;

@Slf4j
@PluginDescriptor(
	name = "Slayer Cape Reminder",
	description = "Reminds you to retrieve your Slayer Cape from the bank if it is not in your inventory or equipped.",
	tags = {"slayer", "cape", "reminder", "bank"}
)
public class SlayerCapeReminderPlugin extends Plugin
{
	static final String TASK_COMPLETE_MESSAGE = "return to a Slayer master";

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private SlayerCapeReminderOverlay overlay;

	// Package-private so the overlay can read them directly.
	boolean capePresent = true;
	boolean taskComplete = false;
	boolean isLevel99 = false;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
		clientThread.invokeLater(() -> {
			updateCapePresence();
			updateSlayerLevel();
		});
		log.debug("SlayerCapeReminderPlugin started");
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		capePresent = true;
		taskComplete = false;
		isLevel99 = false;
		log.debug("SlayerCapeReminderPlugin stopped");
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		switch (event.getType())
		{
			case GAMEMESSAGE:
			case SPAM:
				break;
			default:
				return;
		}

		String message = Text.removeTags(event.getMessage());

		if (message.contains(TASK_COMPLETE_MESSAGE))
		{
			taskComplete = true;
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		if (event.getVarpId() != VarPlayer.SLAYER_TASK_SIZE)
		{
			return;
		}

		if (event.getValue() > 0)
		{
			// New task assigned — hide the reminder.
			taskComplete = false;
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		int id = event.getContainerId();
		if (id == InventoryID.INVENTORY.getId() || id == InventoryID.EQUIPMENT.getId())
		{
			updateCapePresence();
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		GameState state = event.getGameState();
		if (state == GameState.LOGGING_IN || state == GameState.LOGIN_SCREEN)
		{
			capePresent = true;
			taskComplete = false;
		}
	}

	@Subscribe
	public void onStatChanged(StatChanged event)
	{
		if (event.getSkill() == Skill.SLAYER)
		{
			updateSlayerLevel();
		}
	}

	private void updateSlayerLevel()
	{
		isLevel99 = client.getRealSkillLevel(Skill.SLAYER) >= 99;
	}

	private void updateCapePresence()
	{
		ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
		ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);

		boolean inInventory = inventory != null
			&& (inventory.contains(ItemID.SLAYER_CAPE) || inventory.contains(ItemID.SLAYER_CAPET));
		boolean equipped = equipment != null
			&& (equipment.contains(ItemID.SLAYER_CAPE) || equipment.contains(ItemID.SLAYER_CAPET));

		capePresent = inInventory || equipped;
	}

	@Provides
	SlayerCapeReminderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SlayerCapeReminderConfig.class);
	}
}
