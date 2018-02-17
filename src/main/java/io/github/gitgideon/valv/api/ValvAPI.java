package io.github.gitgideon.valv.api;

public class ValvAPI {

    private static ValvEconomy economy = null;

    /**
     * Register a ValvEconomy instance
     * @param economy the ValvEconomy instance you want to register
     */
    public static void registerValvEconomy(ValvEconomy economy) {
        if(ValvAPI.economy != null){
            throw new UnsupportedOperationException(/*TODO: add text here*/);
        }
        else {
            ValvAPI.economy = economy;
        }
    }

}
