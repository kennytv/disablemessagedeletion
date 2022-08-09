package eu.kennytv.disablemessagedeletion;

import eu.kennytv.disablemessagedeletion.config.Config;
import eu.kennytv.disablemessagedeletion.config.ConfigHolder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class DisableMessageDeletionMod implements ModInitializer {

    private static final int DEFAULT_MIN_DELETION_TICKS = 20 * 10;
    private static boolean hasClothConfig; // (▀̿Ĺ̯▀̿ ̿)

    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer("cloth-config").ifPresent(mod -> {
            AutoConfig.register(Config.class, GsonConfigSerializer::new);
            ConfigHolder.config = AutoConfig.getConfigHolder(Config.class).getConfig();
            hasClothConfig = true;
        });
    }

    public static int minDeletionDelayTicks() {
        return hasClothConfig ? ConfigHolder.config.minDeletionDelay() * 20 : DEFAULT_MIN_DELETION_TICKS;
    }
}
