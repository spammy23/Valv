package io.github.gitgideon.valv;

import io.github.gitgideon.valv.api.ValvEconomy;
import io.github.gitgideon.valv.economy.EssentialsEcoEconomy;
import org.bukkit.plugin.java.JavaPlugin;

public final class Valv extends JavaPlugin {

    private ValvEconomy economy;

    @Override
    public void onEnable() {
        setupEconomies();
    }

    private void setupEconomies() {
        if (getServer().getPluginManager().getPlugin("Essentials") != null)
            registerEconomy(new EssentialsEcoEconomy(this));
    }

    /**
     * Register a ValvEconomy instance
     *
     * @param economy the ValvEconomy instance
     * @return true if successful, false if not
     */
    public boolean registerEconomy(ValvEconomy economy) {
        String preferred = getConfig().getString("preferred.economy");
        if (preferred.equalsIgnoreCase("none")) {
            this.economy = economy;
        } else {
            if (preferred.equalsIgnoreCase(economy.getName())) {
                this.economy = economy;
            } else return false;
        }
        return true;
    }

    /**
     * Get the in use ValvEconomy instance
     *
     * @return the in use ValvEconomy instance
     */
    public ValvEconomy getEconomy() {
        return economy;
    }
}
