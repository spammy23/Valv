package io.github.gitgideon.valv;

import io.github.gitgideon.valv.api.ValvAPI;
import io.github.gitgideon.valv.economy.EssentialsEcoEconomy;
import org.bukkit.plugin.java.JavaPlugin;

public final class Valv extends JavaPlugin {


    @Override
    public void onEnable() {
        setupEconomies();
    }

    private void setupEconomies() {
        if (getServer().getPluginManager().getPlugin("Essentials") != null)
            ValvAPI.economy = new EssentialsEcoEconomy(this);
    }

}
