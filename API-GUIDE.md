# Mysterium Fateboxes API Guide

Welcome to the **Mysterium Fateboxes API** documentation! This guide will teach you how to hook into the Mysterium Fateboxes mod to create custom addon events, traps, loot drops, and completely custom fates for your players.

## Table of Contents
1. [Introduction](#introduction)
2. [Adding the API to your Workspace](#adding-the-api-to-your-workspace)
3. [The Core Interfaces](#the-core-interfaces)
4. [Creating Custom Fate Handlers](#creating-custom-fate-handlers)
   - [Custom Good Fate](#custom-good-fate)
   - [Custom Bad Fate (Trap)](#custom-bad-fate-trap)
5. [Registering Your Events](#registering-your-events)
6. [Advanced API usage](#advanced-api-usage)
   - [Titles](#titles)
   - [Entities & Bosses](#entities--bosses)

---

## Introduction

The Mysterium Fateboxes API exposes all the required interfaces and registration points needed to inject your custom fate events directly into the fate box logic. Instead of replacing default logic, your addons run alongside the existing default outcomes seamlessly.

## Adding the API to your Workspace

To use this API, make sure your mod's `build.gradle` defines Mysterium Fateboxes as a dependency or references the API package if distributed separately. 

*(Specific maven instructions or repository links will be provided here as the mod is released onto mod platforms)*

---

## The Core Interfaces

Everything hinges on the `IFateHandler` which expects an execution context with the `Level` and `Player`. From here, it branches into:

* `IGoodFateHandler` - For rewarding events. Includes `getWeight()` for roll probabilities.
* `IBadFateHandler` - For punishing events. Also includes `getWeight()`.
* `ITrapHandler` - Specifically for environment-altering traps.
* `ILootDropHandler` - For dropping items/rewards.
* `IEntitySpawnHandler` - For spawning hordes or passive mobs.
* `IBossSpawnHandler` - Sub-interface for boss encounters.
* `ITitleHandler` - Utility for displaying custom animated screen titles.

### Custom Blocks and Keys

In addition to custom events, the API also exposes base classes to allow you to create your own custom Fate Box and custom Fate Box Key!
* `AbstractFateBoxBlock` - Extend this block to create a block with the exact same 15-second spinning and unlocking sequence as the Mysterium Fate Box. It handles the cooldowns and state logic automatically. You must implement `getCustomShape()` and `getRequiredKey()`.
* `AbstractFateBoxKeyItem` - Extend this to create custom keys.

### Configuration Base

The API provides access to the `FateBoxConfig` configuration base.
* `FateBoxConfig` - Found in `me.axlerogue.mysteriumfateboxes.api.configuration`. You can use `Config.FATE_BOX_CONFIG` to read core mod settings like `allowLavaPools`, `allowExplosions`, `allowWitherBoss`, and `devMode`. You can also extend this class or construct it with your own `ModConfigSpec.Builder` to easily add event-toggling configurations to your own addons!

> **Tip for Addon Devs:** If you need to rapidly test your custom events in-game, be sure to enable **Developer Mode** (`devMode`) in the mod configuration! This bypasses the 15-second opening sequence, disables the 24-second global cooldown, and swaps out the loud, overlapping custom sounds with short vanilla sounds (Chest open, XP drop, Dragon roar) so you can spam-open Fate Boxes safely.

---

## Creating Custom Fate Handlers

To inject your own events, simply create a class implementing either `IGoodFateHandler` or `IBadFateHandler`.

### Custom Good Fate
Here is a small example of a custom loot drop event:

```java
import me.axlerogue.mysteriumfateboxes.api.handlers.IGoodFateHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.item.ItemEntity;

public class MyCustomLootDrop implements IGoodFateHandler {

    @Override
    public String getId() {
        return "myaddon:custom_loot_drop";
    }

    @Override
    public int getWeight() {
        return 10; // 10 weight priority
    }

    @Override
    public void execute(Level level, Player player) {
        if (!level.isClientSide()) {
            ItemStack stack = new ItemStack(Items.DIAMOND, 64);
            ItemEntity entity = new ItemEntity(level, player.getX(), player.getY(), player.getZ(), stack);
            level.addFreshEntity(entity);
        }
    }
}
```

### Custom Bad Fate (Trap)
Here is an example of a custom trap event:

```java
import me.axlerogue.mysteriumfateboxes.api.handlers.ITrapHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class PoisonTrapHandler implements ITrapHandler {

    @Override
    public String getId() {
        return "myaddon:poison_trap";
    }

    @Override
    public int getWeight() {
        return 20; // High probability of triggering relative to other bad fates
    }

    @Override
    public void execute(Level level, Player player) {
        if (!level.isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
        }
    }
}
```

---

## Registering Your Events

Once your custom handlers are created, you must register them to the `FateEventRegistry` so the mod knows they exist.

Register your handlers during your mod's `FMLCommonSetupEvent`:

```java
import me.axlerogue.mysteriumfateboxes.api.registry.FateEventRegistry;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public void commonSetup(final FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
        FateEventRegistry.registerGoodFate(new MyCustomLootDrop());
        FateEventRegistry.registerBadFate(new PoisonTrapHandler());
    });
}
```

## Advanced API Usage

### Titles
You can use `ITitleHandler` to construct an animated screen title that you call in your `execute()` method, perfectly matching the style of Mysterium's "WOOLARAMA!" and "ANGRY PUPPERS!" screens.

### Entities & Bosses
Implement `IBossSpawnHandler` if you want to define specific parameters about the hostility of your spawned entities using `isHostile()`. This helps compatibility tracking later on.
