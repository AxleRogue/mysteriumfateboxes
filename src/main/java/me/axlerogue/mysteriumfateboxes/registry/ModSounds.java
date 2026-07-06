package me.axlerogue.mysteriumfateboxes.registry;

import me.axlerogue.mysteriumfateboxes.MysteriumFateboxes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, MysteriumFateboxes.MODID);

    public static final Supplier<SoundEvent> GOOD_FATE = registerSoundEvent("mysterium_fateboxes_good_fate");
    public static final Supplier<SoundEvent> BAD_FATE = registerSoundEvent("mysterium_fateboxes_bad_fate");

    public static final Supplier<SoundEvent> FATE_BOX_OPEN = registerSoundEvent("mysterium_fateboxes_open_fatebox");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(MysteriumFateboxes.MODID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
