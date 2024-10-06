package net.bunten.tooltiptweaks.config.options;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.text.Text;

public enum NourishmentDisplay implements NameableEnum {
    FOOD_ONLY,
    FOOD_AND_SATURATION,
    DISABLED;

    @Override
    public Text getDisplayName() {
        return Text.translatable("tooltiptweaks.value." + name().toLowerCase());
    }
}