package com.slayercapereminder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.coords.WorldPoint;

@Getter
@RequiredArgsConstructor
enum SlayerMaster
{
	DURADEL(new WorldPoint(2869, 2982, 1)),
	NIEVE(new WorldPoint(2432, 3423, 0)),
	KONAR(new WorldPoint(1309, 3785, 0)),
	KRYSTILIA(new WorldPoint(3109, 3516, 0));

	private final WorldPoint location;
}
