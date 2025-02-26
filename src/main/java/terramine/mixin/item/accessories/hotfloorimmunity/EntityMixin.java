package terramine.mixin.item.accessories.hotfloorimmunity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terramine.common.init.ModItems;
import terramine.common.trinkets.TrinketsHelper;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(at = @At("HEAD"), method = "isInvulnerableTo", cancellable = true)
    private void hotFloorImmunity(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof LivingEntity entity) {
            if (TrinketsHelper.isEquipped(ModItems.OBSIDIAN_SKULL, entity) || TrinketsHelper.isEquipped(ModItems.OBSIDIAN_HORSESHOE, entity) || TrinketsHelper.isEquipped(ModItems.MAGMA_SKULL, entity) || TrinketsHelper.isEquipped(ModItems.OBSIDIAN_SKULL_ROSE, entity)
                    || TrinketsHelper.isEquipped(ModItems.MOLTEN_SKULL_ROSE, entity) || TrinketsHelper.isEquipped(ModItems.MOLTEN_CHARM, entity) || TrinketsHelper.isEquipped(ModItems.OBSIDIAN_WATER_WALKING_BOOTS, entity)
                    || TrinketsHelper.isEquipped(ModItems.LAVA_WADERS, entity) || TrinketsHelper.isEquipped(ModItems.TERRASPARK_BOOTS, entity) || TrinketsHelper.isEquipped(ModItems.OBSIDIAN_SHIELD, entity)
                    || entity.getMainHandItem().is(ModItems.OBSIDIAN_SHIELD) || entity.getOffhandItem().is(ModItems.OBSIDIAN_SHIELD)) {
                if (damageSource.equals(DamageSource.HOT_FLOOR)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
