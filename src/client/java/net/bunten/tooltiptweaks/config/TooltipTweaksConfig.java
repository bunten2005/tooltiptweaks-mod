package net.bunten.tooltiptweaks.config;


import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.bunten.tooltiptweaks.TooltipTweaksMod;
import net.bunten.tooltiptweaks.config.options.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class TooltipTweaksConfig {

    public static ConfigClassHandler<TooltipTweaksConfig> HANDLER = ConfigClassHandler.createBuilder(TooltipTweaksConfig.class)
            .id(TooltipTweaksMod.id("config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("tooltiptweaks.json5"))
                    .setJson5(true)
                    .build())
            .build();

    public static TooltipTweaksConfig getInstance() {
        return HANDLER.instance();
    }

    @SerialEntry public boolean displayAxolotlVariants = true;
    @SerialEntry public boolean displayRepairCost = false;
    @SerialEntry public boolean displayUsesLeft = false;
    @SerialEntry public boolean showMoonPhase = false;
    @SerialEntry public boolean updatePotionTooltips = true;

    @SerialEntry public int containerEntries = 6;
    @SerialEntry public int percentageDecimals = 0;

    @SerialEntry public ClockTime clockTime = ClockTime.TWELVE_HOUR;
    @SerialEntry public CompassDisplay compassDisplay = CompassDisplay.DISTANCE;
    @SerialEntry public ContainerStyle containerStyle = ContainerStyle.LIST_PER_ITEM;
    @SerialEntry public DurabilityStyle durabilityStyle = DurabilityStyle.PERCENTAGE;
    @SerialEntry public EffectDisplay foodEffectDisplay = EffectDisplay.POSITIVE_EFFECTS_ONLY;
    @SerialEntry public EffectDisplay stewEffectDisplay = EffectDisplay.CREATIVE_ONLY;
    @SerialEntry public IconLocation nourishmentIconLocation = IconLocation.BELOW;
    @SerialEntry public NourishmentDisplay nourishmentDisplay = NourishmentDisplay.FOOD_ONLY;
    @SerialEntry public NourishmentStyle nourishmentStyle = NourishmentStyle.TEXT;
    @SerialEntry public OtherEffectDisplay modifierDisplay = OtherEffectDisplay.ENABLED;
    @SerialEntry public OtherEffectDisplay otherEffectDisplay = OtherEffectDisplay.ENABLED;

    public static Screen buildMenu(Screen parent) {
        return YetAnotherConfigLib.create(TooltipTweaksConfig.HANDLER,
                (defaults, config, builder) -> {
                    return builder
                            .title(Text.translatable("tooltiptweaks.menu"))
                            .category(toolsCategory(config))
                            .category(consumablesCategory(config));
                }).generateScreen(parent);
    }

    private static ConfigCategory toolsCategory(TooltipTweaksConfig config) {
        return ConfigCategory.createBuilder()
                .name(Text.translatable("tooltiptweaks.category.tools"))

                .group(OptionGroup.createBuilder()
                        .name(Text.translatable("tooltiptweaks.group.clock"))
                        .option(Option.<ClockTime>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.clock.time_display"))
                                .binding(ClockTime.TWELVE_HOUR, () -> config.clockTime, value -> config.clockTime = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(ClockTime.class))
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.clock.moon_phase"))
                                .binding(false, () -> config.showMoonPhase, value -> config.showMoonPhase = value)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())

                .group(OptionGroup.createBuilder()
                        .name(Text.translatable("tooltiptweaks.group.containers"))
                        .option(Option.<ContainerStyle>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.container.display"))
                                .binding(ContainerStyle.LIST_PER_ITEM, () -> config.containerStyle, value -> config.containerStyle = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(ContainerStyle.class))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.container.entries"))
                                .binding(6, () -> config.containerEntries, value -> config.containerEntries = value)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 27).step(1))
                                .build())
                        .build())

                .group(OptionGroup.createBuilder()
                        .name(Text.translatable("tooltiptweaks.group.durability"))
                        .option(Option.<DurabilityStyle>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.container.display"))
                                .binding(DurabilityStyle.PERCENTAGE, () -> config.durabilityStyle, value -> config.durabilityStyle = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(DurabilityStyle.class))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.durability.decimal_count"))
                                .binding(0, () -> config.percentageDecimals, value -> config.percentageDecimals = value)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 4).step(1))
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.durability.uses_left"))
                                .binding(false, () -> config.displayUsesLeft, value -> config.displayUsesLeft = value)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())

                .group(OptionGroup.createBuilder()
                        .name(Text.translatable("tooltiptweaks.group.other_tools"))
                        .option(Option.<CompassDisplay>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.other_tools.compass_display"))
                                .binding(CompassDisplay.DISTANCE, () -> config.compassDisplay, value -> config.compassDisplay = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(CompassDisplay.class))
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.other_tools.display_repair_cost"))
                                .binding(false, () -> config.displayRepairCost, value -> config.displayRepairCost = value)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.tools.other_tools.display_axolotl_variants"))
                                .binding(true, () -> config.displayAxolotlVariants, value -> config.displayAxolotlVariants = value)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
                .build();
    }

    private static ConfigCategory consumablesCategory(TooltipTweaksConfig config) {
        return ConfigCategory.createBuilder()
                .name(Text.translatable("tooltiptweaks.category.consumables"))
                .group(OptionGroup.createBuilder()
                        .name(Text.translatable("tooltiptweaks.group.food"))
                        .option(Option.<NourishmentStyle>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.consumables.nourishment_style"))
                                .binding(NourishmentStyle.TEXT, () -> config.nourishmentStyle, value -> config.nourishmentStyle = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(NourishmentStyle.class))
                                .build())
                        .option(Option.<IconLocation>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.consumables.nourishment_icon_location"))
                                .binding(IconLocation.BELOW, () -> config.nourishmentIconLocation, value -> config.nourishmentIconLocation = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(IconLocation.class))
                                .build())
                        .option(Option.<NourishmentDisplay>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.consumables.nourishment_display"))
                                .binding(NourishmentDisplay.FOOD_ONLY, () -> config.nourishmentDisplay, value -> config.nourishmentDisplay = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(NourishmentDisplay.class))
                                .build())
                        .option(Option.<EffectDisplay>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.consumables.food_effect_display"))
                                .binding(EffectDisplay.POSITIVE_EFFECTS_ONLY, () -> config.foodEffectDisplay, value -> config.foodEffectDisplay = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(EffectDisplay.class))
                                .build())
                        .option(Option.<EffectDisplay>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.consumables.stew_effect_display"))
                                .binding(EffectDisplay.CREATIVE_ONLY, () -> config.stewEffectDisplay, value -> config.stewEffectDisplay = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(EffectDisplay.class))
                                .build())
                        .option(Option.<OtherEffectDisplay>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.consumables.modifier_display"))
                                .binding(OtherEffectDisplay.ENABLED, () -> config.modifierDisplay, value -> config.modifierDisplay = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(OtherEffectDisplay.class))
                                .build())
                        .option(Option.<OtherEffectDisplay>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.consumables.other_effect_display"))
                                .binding(OtherEffectDisplay.ENABLED, () -> config.otherEffectDisplay, value -> config.otherEffectDisplay = value)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(OtherEffectDisplay.class))
                                .build())
                        .build())
                .group(OptionGroup.createBuilder()
                        .name(Text.translatable("tooltiptweaks.group.other_consumables"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("tooltiptweaks.option.consumables.update_potion_tooltips"))
                                .binding(true, () -> config.updatePotionTooltips, value -> config.updatePotionTooltips = value)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
                .build();
    }

    static {
        HANDLER.load();
    }
}