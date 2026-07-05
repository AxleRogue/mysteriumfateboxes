# Changelog

## [1.0.0-release] - 2026-07-05

### Added
* **Mysterium Fate Box Block**: The core block of the mod. Place it in the world to test your fate.
* **Mysterium Fate Box Key**: A custom item required to unlock a placed Fate Box.
* **Advanced Luck System**: A 50/50 chance system determining your fate upon unlocking the box.
* **Good Fate Rewards**:
  * *Item Gifts*: Drops random high-value items (Diamonds, Emeralds, Netherite Ingots, Totems of Undying, etc.).
  * *Weapon Gifts*: Drops random high-tier weapons.
  * *Armor Set Gifts*: Drops an entire, matching set of armor (Diamond, Netherite, Iron, or Gold).
  * *Tool Gifts*: Drops high-tier tools.
  * *Food Gifts*: Drops an even number of high-quality food (2-16 items).
* **Bad Fate Punishments**:
  * *Explosion*: Instantly detonates a powerful explosion.
  * *Raining Flaming Arrows*: Drops 15 to 30 flaming arrows from the sky in a wide radius.
  * *Hordes*: Spawns a horde of 4-7 Zombies, Husks, or Drowned.
  * *Elite Mobs*: Every mob spawned by the horde trap has a 33% chance to be an Elite, spawning with full Netherite Armor and a Netherite weapon (Sword, Axe, or Trident).
  * *Lava Pool Trap*: Creates a 7x7 pool of lava exactly where the player stands and teleports them to the center obsidian block.
* **Cooldown System**: Added a 10-second global cooldown to prevent spam-opening boxes, complete with custom styled chat messages (AQUA for success, RED for cooldown errors).
* **Crafting Recipes**: 
  * Fate Box: 4 Iron Ingots, 4 Gold Ingots, 1 Nether Star.
  * Key: 2 Gold Ingots, 1 Redstone Dust.
* **Advancement Tree**: Fully integrated "Mysterium Fate" advancement tab with custom icons and background.
* **Custom Sounds & Subtitles**: Added specific sound events (`GOOD_LUCK` and `BAD_LUCK`) with matching subtitles.
* **Mod Logo**: Configured the official mod logo to display correctly in the NeoForge mod menu.

### Fixed
* Fixed an issue where the block's voxel shape/hitbox was misaligned and not wrapping the model properly.
* Fixed an issue where the Fate Box Key rendered as a missing texture (purple/black squares) by implementing a proper data-driven item model definition.
* Fixed an integer division rounding error that caused the cooldown timer to prematurely broadcast "Wait 0 seconds!".
* Fixed the Lava Pool trap logic to ensure it teleports the player directly into the trap instead of allowing them to trigger it safely from a distance.
* Fixed the BlockItem registration so the `useBlockDescriptionPrefix()` correctly hooks the item name to the block's translation key.