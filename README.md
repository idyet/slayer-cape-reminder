# Slayer Cape Reminder

A RuneLite plugin for players who use the Slayer Cape's perk to request a repeated Slayer task. The cape must be in your inventory or equipped when speaking to a Slayer master — this plugin reminds you to grab it from the bank when it's absent.

## Behavior

The overlay triggers when:

- Your last Slayer task was completed and the Slayer Cape is not in your inventory or equipped, **or**
- You walk within range of a Slayer master without an active task and without your cape

The overlay hides automatically once you pick up or equip the cape, receive a new task, or after a configurable auto-dismiss delay.

## Configuration

| Option | Description | Default |
|--------|-------------|---------|
| Only at 99 Slayer | Restrict the reminder to players with level 99 | Enabled |
| Reminder text | Message displayed in the overlay | `Get your Slayer Cape!` |
| Text color | Color of the reminder text | Red |
| Background color | Overlay background color | — |
| Flash background | Alternates between two background colors for a more visible alert | Disabled |
| Flash color | Second background color used when flashing | — |
| Auto-dismiss delay | Seconds before the overlay hides after task completion (0 = never) | 120s |

### Proximity Alert

Per-master toggles for proximity-based reminders. When enabled for a master, the overlay appears if you walk within 16 tiles of them without an active task and without your cape.

| Master | Location |
|--------|----------|
| Duradel | Shilo Village |
| Nieve / Steve | Gnome Stronghold |
| Konar quo Maten | Mount Karuulm |
| Krystilia | Edgeville |
