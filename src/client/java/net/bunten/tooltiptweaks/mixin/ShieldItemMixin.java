package net.bunten.tooltiptweaks.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ShieldItem.class)
public abstract class ShieldItemMixin extends Item {

    public ShieldItemMixin(Settings settings) {
        super(settings);
    }

    // I'm not sure if this causes any issues, but otherwise it just seems they left it out unintentionally
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void reimplementSuper(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        super.appendTooltip(stack, context, tooltip, type);
    }
}