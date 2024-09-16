package hazelclover.hazelsvariouswings.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class WingsItem extends TrinketItem {
    private final float speed;

    public WingsItem(Settings settings, float speed) {
        super(settings);
        this.speed = speed;
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier identifier){
        var modifiers = super.getModifiers(stack, slot, entity, identifier);
        // +10% movement speed (for testing purposes)
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(identifier, speed, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return modifiers;
    }
}
