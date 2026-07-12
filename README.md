# Mysterium Fateboxes

**Mysterium Fateboxes** is a highly advanced, thrilling "Lucky Block" style mod for Minecraft (built on NeoForge 26.2). It introduces a high-stakes risk-vs-reward system where opening a Fate Box can either shower you with incredible wealth or unleash devastating punishments.

---

## 📦 Core Items & Blocks

### Mysterium Fate Box
The core block of the mod. Placing it down sets the stage for a game of chance. You cannot open it with your bare hands—you must use a special key, and the block is completely unbreakable in Survival mode (just like Bedrock)!
* **Recipe:** 4 Iron Ingots (corners), 4 Gold Ingots (sides), and 1 Diamond (center).

### Mysterium Fate Box Key
The mystical key required to unlock the Fate Box's secrets. Right-clicking a Fate Box with this key consumes the key and triggers the event.
* **Recipe:** 1 Gold Ingot (top), 1 Gold Ingot (middle), and 1 Redstone Dust (bottom).

---

## 🎲 The Fate System

When you unlock a Mysterium Fate Box, you experience a dynamic **Roll Chance System**. A 15-second opening sequence initiates with custom sounds, and the block itself rapidly spins on its axis as the suspense builds before your fate is revealed!

The system rolls a number from 1 to 100, modified dynamically by your hidden **Fate Level** (LOW, MID, or HIGH). In version 1.0.5, this Fate Level strictly determines the tier and intensity of the rewards or punishments you face!

### 🟢 Good Fate (Rewards)
If the odds are in your favor, a joyful sound plays, you receive a green chat message (detailing your Fate Level), and you are rewarded. A 25% chance grants you one of five incredible reward categories. Rewards dynamically drop directly at your feet even if you ran away from the box! Missing this roll triggers the Woolarama event (High Fate only) or gives a fallback item reward!

* **On-Screen Titles:** 
  * If you get a major Gift reward, your screen will light up with a diamond blue "A MYSTICAL GIFT!" text.
  * Triggering the Woolarama event shows a rainbow-animated "WOOLARAMA!" title.
  * Triggering the Happy Puppers event displays a joyful "HAPPY PUPPERS!" title.
  * Triggering trap events (Explosion, Lava Pool, Arrows) displays a red "TRAP TRIGGERED!" title.
  * Spawning mob hordes displays a red "UNDEAD HORDE!" title.
  * Spawning the Wither Boss ambush displays a red "WITHER CURSE!" title.
  * Spawning the Angry Puppers ambush displays a bold red "ANGRY PUPPERS!" title.
1. **Valuable Items:** Drops 1-3 valuable items tiered by Fate (Low: Iron/Pearls | Mid: Gold/Diamond/Emeralds | High: Netherite/Totems/Enchanted Apples).
2. **Weapons:** Drops a random weapon tiered by Fate (Low: Iron/Bow | Mid: Diamond/Gold/Crossbow | High: Netherite/Trident).
3. **Armor Sets:** Drops an entire, full set of armor at once tiered by Fate!
4. **Tools:** Drops a random tool tiered by Fate.
5. **Food:** Drops a stack of high-tier food in multiples of 2 (up to 16 items) tiered by Fate.
6. **Woolarama Event:** (High Fate Only) Drops 16 to 32 vibrantly colored rainbow sheep from the sky with a Slow Falling effect, complete with an animated "WOOLARAMA!" rainbow title screen!
7. **Happy Puppers Event:** (Mid Fate Only) Drops 16 to 32 pre-tamed, slow-falling wolves from the sky directly at your position, each with a randomized variant and collar color! Includes an animated "HAPPY PUPPERS!" title screen.

### 🔴 Bad Fate (Punishments)
If your luck runs out, an ominous sound plays, a red chat message appears, and one of several terrifying punishments is unleashed depending on your exact roll and Fate Level:
1. **Wither Boss Ambush:** If you hit a 15% chance roll on a HIGH Fate Level, 4 Wither Bosses will spawn simultaneously around you!
2. **Angry Puppers:** If you hit a 20% chance roll on a HIGH Fate Level, 16 to 32 slow-falling, extremely aggressive wolves will drop from the sky and immediately target you!
3. **Traps:** 
   * **Low Fate:** A raw TNT Explosion exactly at your location.
   * **Mid Fate:** Raining Flaming Arrows! 25 to 50 arrows arc towards you over a 20x20 area.
   * **High Fate:** Lava Pool! A 7x7 circular pool of lava spawns directly beneath your feet.
4. **Mob Hordes:** Spawns 4 to 7 enemies surrounding you based on your Fate level:
   * **Low Fate:** Drowned Horde.
   * **Mid Fate:** Husk Horde.
   * **High Fate:** Zombie Horde.
   * *Beware: Each mob has a 33% chance to spawn as an "Elite" wearing full Netherite Armor and wielding a Netherite weapon!*

---

## ⏱️ Mechanics & Systems

* **Global Cooldowns:** To prevent spamming, the mod features a built-in 24-second cooldown. If you try to open another box while on cooldown, you will receive a bold red warning message indicating how much time is left.
* **Custom Advancements:** The mod comes with a fully integrated advancement tree ("Mysterium Fate") to guide players through crafting their first Box and Key, complete with a custom background UI.
* **Safe Creative Mode:** If you are in Creative Mode, keys will *not* be consumed upon use.

---

## 🛠️ Installation
1. Install **NeoForge 26.2**.
2. Drop the `mysteriumfateboxes-1.0.8-release.jar` into your `.minecraft/mods` folder.
3. Load up your world and test your fate!

---

## ⚙️ Configuration

Mysterium Fateboxes includes a comprehensive configuration file that allows server owners and players to tailor the experience:
* **Disable Destructive Events:** You can easily disable Explosions, Lava Pools, and Wither Boss spawns via the config file. If one of these events is rolled while disabled, the mod gracefully falls back to an appropriate Mob Horde to ensure the punishment still happens without destroying your world!
* **Developer Mode:** A built-in toggle intended for Addon developers or curious players. Enabling `devMode` will completely remove the 24-second global cooldown, bypass the 15-second opening suspense sequence, and replace the custom mod sounds with vanilla sounds (Chest Open, Experience Orb, Ender Dragon Roar) so you can spam-open Fate Boxes rapidly for testing without deafening yourself with the suspense sounds!

---

## 💻 Developer Addon API

Mysterium Fateboxes now features a full-fledged Addon API, allowing other developers to create their own custom fate events, traps, loot drops, and bosses that run seamlessly alongside the vanilla mod logic!

If you want to build an addon for this mod, check out the comprehensive documentation and tutorials on the **[Mysterium Fateboxes GitHub Wiki](https://github.com/AxleRogue/mysteriumfateboxes/wiki)**!

---

## 📜 License
This mod is open-source and is distributed under the terms found in the [LICENSE](LICENSE) file provided with this repository.