package terramine.common.item.accessories.necklace;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import terramine.common.init.ModAttributes;
import terramine.common.item.accessories.TrinketTerrariaItem;

import java.util.UUID;

public class RangerEmblemItem extends TrinketTerrariaItem {

	@Override
	protected Multimap<Attribute, AttributeModifier> applyModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
		Multimap<Attribute, AttributeModifier> result = super.applyModifiers(stack, slot, entity, uuid);
		AttributeModifier modifier = new AttributeModifier(uuid, "ranger_emblem_attack_damage", 0.15f, AttributeModifier.Operation.MULTIPLY_TOTAL);
		result.put(ModAttributes.RANGER_ATTACK_DAMAGE, modifier);
		return result;
	}
}
