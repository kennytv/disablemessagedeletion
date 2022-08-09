package eu.kennytv.disablemessagedeletion.config;

import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = "disablemessagedeletion")
public class Config implements ConfigData {

    int minDeletionDelay = 5;

    public int minDeletionDelay() {
        return minDeletionDelay;
    }
}