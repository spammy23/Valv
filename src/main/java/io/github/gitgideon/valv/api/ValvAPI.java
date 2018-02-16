package io.github.gitgideon.valv.api;

public class ValvAPI {

    public static ValvEconomy economy = null;

    /**
     * Register a ValvEconomy instance
     * @param econ the ValvEconomy instance you want to register
     */
    public static void registerValvEconomy(ValvEconomy econ) {
        economy = econ;
    }

}
