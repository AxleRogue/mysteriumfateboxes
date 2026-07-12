# Changelog

## [1.0.8-release] - 2026-07-11

### Added
* **Configuration Options**: Added a new configuration file (`mysteriumfateboxes-server.toml` or via Mod Menu) powered by `FateBoxConfig`. Server owners and players can now toggle the spawning of Lava Pools, Explosions, and Wither Bosses. 
  * If a disabled trap/event is rolled, the system will dynamically fall back to an appropriate Mob Horde spawn to ensure the player still receives a Bad Fate punishment.
* **API Configuration Access**: The `FateBoxConfig` class has been exposed in the Developer API. Addons can now read from or extend this configuration class to manage their own settings alongside the main mod!
* **Improved Lava Pool Trap**: The Lava Pool Trap (High Bad Fate) now generates a solid bowl (randomly choosing between Obsidian and Stone) underneath and around the lava pool, rather than just replacing the blocks on the current layer, preventing the lava from breaking map terrain in unexpected ways or leaking downward.

## [1.0.7-release] - 2026-07-11

### Added
* **Developer Addon API**: Mysterium Fateboxes now features a robust Addon API allowing developers to hook custom events directly into the mod!
  * Added core interfaces: `IFateHandler`, `IGoodFateHandler`, `IBadFateHandler`, `ITrapHandler`, `ILootDropHandler`, `IEntitySpawnHandler`, `IBossSpawnHandler`, `ITitleHandler`, `IChoiceHandler`, and `IRollChanceHandler`.
  * Added `AbstractFateBoxBlock` and `AbstractFateBoxKeyItem` so addons can create their own custom blocks that hook right into the 15-second spinning state and cooldown logic.
  * Added `FateEventRegistry` allowing addons to seamlessly register custom Good and Bad Fates to compete natively with default mod logic.
  * Added comprehensive API Developer documentation to the GitHub Wiki.

### Changed
* **Code Refactoring & Cleanup**: Extracted the Woolarama Event and Happy/Angry Puppers Event titles into their own dedicated animated title handlers (`WoolaramaTitleHandler` and `PuppersTitleHandler`) to improve handler package organization and clean up event classes.
* **API Integration**: Core `GoodFateHandler` and `BadFateHandler` refactored to query the `FateEventRegistry`, allowing registered addon fates to override vanilla static executions dynamically based on assigned weights.

## [1.0.6-release] - 2026-07-07

### Added
* **Happy Puppers Event**: A highly anticipated new Good Fate event! A 20% chance to trigger an event on a MID Fate Level that drops 16 to 32 pre-tamed, slow-falling wolves from the sky directly onto the player's position. Each wolf features a randomized collar color and variant model, paired with an animated "HAPPY PUPPERS!" title screen!
* **Angry Puppers Event**: The chaotic counterpart! A 20% chance to trigger an event on a HIGH Bad Fate Level that drops 16 to 32 angered, slow-falling wolves from the sky that immediately target the player who opened the box. Accompanied by a bold red "ANGRY PUPPERS!" title screen!

### Changed
* **Code Cleanup**: Removed the unused and deprecated `roll` parameter from the fate execution handlers.
* **Texture Update**: Updated the Fate Box texture to improve visual clarity and contrast.

## [1.0.5-release] - 2026-07-06

### Added
* **Dynamic Reward & Punishment Tiering**: Completely overhauled how items, weapons, armor, tools, food, traps, and mob spawns are distributed. They are now strictly filtered into **Low**, **Mid**, and **High** tiers based on the `FateLevelType` that rolls!
  * **Traps**: Explosions (Low Fate), Flaming Arrows (Mid Fate), Lava Pools (High Fate).
  * **Mob Hordes**: Drowned (Low Fate), Husks (Mid Fate), Zombies (High Fate).
  * **Bosses & Events**: Wither Bosses and the Woolarama Event will now only trigger on **High** Fate levels.
* **15-Second Opening Suspense**: The Fate Box opening sequence has been increased from 3 seconds to a suspenseful 15 seconds.
* **Spinning Block Animation**: During the 15-second opening sequence, the Fate Box will now rapidly spin around on the Y-axis until it detonates!

### Changed
* **Precise Item Drop Tracking**: All Good Fate reward handlers (Items, Weapons, Armor, Tools, Food) now actively track the player's exact `blockPosition()` at the moment the 15-second timer ends, ensuring your rewards spawn exactly where you are standing, not where you left the Fate Box!
* **Model Rendering Optimization**: The block model JSON files were split into base and diamond components behind the scenes for cleaner structural rendering.

## [1.0.4-release] - 2026-07-06

### Added
* **Woolarama Event**: A spectacular new Good Fate event! A 45% chance to trigger an event that drops 16 to 32 vibrant, randomly colored sheep from the sky equipped with the Slow Falling effect.
* **Animated Titles & Screen Feedback**: 
  * Added `HordeTitleHandler`: Hordes (Zombies, Husks, Drowned) now display a bold red "UNDEAD HORDE!" title on screen.
  * Added `TrapTitleHandler`: Traps (Explosions, Lava Pools, Raining Flaming Arrows) now display a bold red "TRAP TRIGGERED!" title on screen.
  * Added `WitherBossTitleHandler`: The Wither Boss ambush now displays a bold red "WITHER CURSE!" title on screen.
  * Triggering the Woolarama event displays a massive, rainbow-animated "WOOLARAMA!" title screen.
  * Receiving any of the major Gift rewards (Items, Weapons, Armor Sets, Tools, Food) now displays a brilliant diamond blue "A MYSTICAL GIFT!" title on screen.

### Changed
* **Code Refactoring & Cleanup**: Rewrote and cleaned up backend code logic across the mod for efficiency and maintainability. Properly formatted variables, unused imports, and unused parameters across multiple handlers.
* **Bad Fate Traps Targeting**: Horde spawn traps (Zombies, Husks, Drowned) and the Wither Boss trap now actively track and spawn relative to the player's exact position instead of the initial Fate Box location.
* **Good Fate System Rebalance**: Adjusted the primary reward threshold. There is now a 25% base chance to roll a major gift category (Items, Weapons, Armor Sets, Tools, Food). Failing the 25% roll will grant the default single item fallback reward.
* **Removed Redundancies**: Removed unused backend system handlers (like the FateDecisionHandler).

## [1.0.3-release] - 2026-07-05

### Added
* **Wither Boss Ambush**: Added a deadly new trap to the Bad Fate event. Rolling a 5% chance on a HIGH Bad Fate Level will now simultaneously spawn 4 Wither Bosses around the player's position!

### Changed
* **Recipe & Advancements**: Replaced `has_nether_star` and `nether_star` with `has_diamond` and `diamond` in advancements and recipes. The recipe character key `S` was also replaced with `D` for the diamond requirement.

## [1.0.1-release] - 2026-07-05

### Added
* **Fate Box Opening Sequence**: Added a suspenseful 3-second delay upon unlocking the Fate Box.
* **New Sounds**: 
  * Added `mysterium_fateboxes_open_fatebox` sound and subtitle to play during the 3-second suspense phase.
  * Added a vanilla firework twinkle sound to play right when the fate is executed.
* **Dynamic Fate Levels System**: 
  * Replaced the simple 50/50 flip with a deep `RollChanceHandler` system.
  * Introduced Fate Level mechanics (LOW, MID, HIGH) which modify the base 1-100 roll.
  * Chat messages now display your Fate Level when announcing your fate!
* **Overhauled Rewards & Punishments Math**:
  * Good Fate now relies on a 45% base threshold to roll major reward categories, with a fallback guarantee if you miss the roll.
  * Bad Fate traps are now distributed by roll chance brackets: Explosion (0-50%), Arrows (51-65%), Lava Pool (66-75%), Mob Horde (76-100%).

### Changed
* **Fate Box Properties**: The Mysterium Fate Box is now completely unbreakable in survival mode (identical to Bedrock), forcing players to use the Fate Box Key.
* **Internal Nomenclature**: Renamed all internal code instances, file names, enums, and variables referencing "Luck" to "Fate" to better match the mod's theme.
* **Explosion Trap Overhaul**: The explosion now actively targets the player's exact `blockPosition()` at the moment of detonation rather than the original box location.
* **Lava Pool Trap Overhaul**: The lava pool generation now actively tracks the player's position after the 3-second timer and spawns directly underneath them.
* **Flaming Arrows Overhaul**: Completely reworked the arrow mechanic to simulate an "army of archers".
  * Arrow count increased from 15-30 to 25-50.
  * Spawn radius expanded from a 10x10 area to a massive 20x20 area.
  * Altitude of spawned arrows increased significantly.
  * Added dynamic X/Z axis velocities to arc the arrows inward towards the player's position.

### Fixed
* Fixed mismatched sound registration keys preventing the open sound `.ogg` file from playing.

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