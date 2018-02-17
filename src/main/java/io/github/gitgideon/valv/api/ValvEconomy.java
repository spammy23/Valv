package io.github.gitgideon.valv.api;

import java.util.List;

public interface ValvEconomy {

    /**
     * Get the name of this implementation (probably the one being used)
     *
     * @return Name of this implementation (probably the one being used)
     */
    String getName();

    /**
     * Returns true if the given implementation supports banks.
     *
     * @return true if the implementation supports banks
     */
    boolean hasBankSupport();

    /**
     * Returns true if the given implementation supports multiple worlds.
     *
     * @return true if the implementation supports multiple worlds
     */
    boolean hasMultiworldSupport();

    /**
     * Some economy plugins round off after a certain number of digits.
     *
     * @return number of digits after the decimal point kept. -1 if no rounding occurs.
     */
    int fractionalDigits();

    /**
     * Format amount into a String formatted to the economy plugin's settings
     *
     * @param amount amount to format
     * @return the formatted String
     */
    String format(double amount);

    /**
     * Returns the name of the currency in singular form.
     * If the economy plugin used doesn't support this, an empty String is returned.
     *
     * @return name of currency (singular)
     */
    String currencyNameSingular();

    /**
     * Returns the name of the currency in plural form.
     * If the economy plugin used doesn't support this, an empty String is returned.
     *
     * @return name of currency (plural)
     */
    String currencyNamePlural();

    /**
     * Returns whether or not the player has an account
     *
     * @param playerName The player to check against
     * @return whether or not the player has an account
     */
    boolean hasAccount(String playerName);

    boolean hasAccount(String playerName, String worldName);

    /**
     * Returns the balance of the player's account
     *
     * @param playerName The name of the player assigned to the account
     * @return The balance of the account
     */
    double getBalance(String playerName);

    double getBalance(String playerName, String worldName);

    /**
     * Checks whether the player has at least a certain amount of money
     *
     * @param playerName The name of the player
     * @param amount The amount to check that the player has
     * @return Whether or not the player has at least the amount specified
     */
    boolean has(String playerName, double amount);

    boolean has(String playerName, String worldName, double amount);


    EconomyResponse withdrawPlayer(String playerName, double amount);

    EconomyResponse withdrawPlayer(String playerName, String worldName, double amount);

    EconomyResponse depositPlayer(String playerName, double amount);

    EconomyResponse depositPlayer(String playerName, String worldName, double amount);

    EconomyResponse createBank(String name, String playerName);

    EconomyResponse deleteBank(String name);

    EconomyResponse bankBalance(String name);

    EconomyResponse bankHas(String name, double amount);

    EconomyResponse bankWithdraw(String name, double amount);

    EconomyResponse bankDeposit(String name, double amount);

    /**
     * Checks whether or not a certain player owns the bank
     *
     * @param bankName The name of the bank
     * @param playerName The name of the player
     * @return Whether or not the player owns the bank
     */
    EconomyResponse isBankOwner(String bankName, String playerName);

    /**
     * Checks whether or not a certain player is a member of a bank
     *
     * @param bankName The name of the bank
     * @param playerName THe name of the player
     * @return
     */
    EconomyResponse isBankMember(String bankName, String playerName);

    List<String> getBanks();

    boolean createPlayerAccount(String playerName);

    boolean createPlayerAccount(String playerName, String worldName);
}
