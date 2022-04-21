package terracraft.common.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import terracraft.TerraCraft;
import terracraft.common.utility.MagicMissileHelper;

import java.util.Random;

public class RainbowMissileEntity extends MagicMissileHelper {
    private final Random rand = new Random();

    public RainbowMissileEntity(EntityType<? extends MagicMissileHelper> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void createParticles() {
        float random = (rand.nextFloat() - 0.5F) * 0.1F;
        this.level.addParticle(ParticleTypes.FLAME, position().x(), position().y(), position().z(), random, -0.2D, random);
        this.level.addParticle(TerraCraft.GREEN_SPARK, position().x(), position().y(), position().z(), random, -0.2D, random);
        this.level.addParticle(TerraCraft.BLUE_POOF, position().x(), position().y(), position().z(), random, -0.2D, random);
    }
}
