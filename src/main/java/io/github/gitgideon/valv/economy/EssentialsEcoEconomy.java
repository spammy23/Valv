package io.github.gitgideon.valv.economy;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import io.github.gitgideon.valv.Valv;
import io.github.gitgideon.valv.api.EconomyResponse;
import io.github.gitgideon.valv.api.ValvEconomy;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class EssentialsEcoEconomy implements ValvEconomy {

    private final EconomyResponse unimplemented = new EconomyResponse(
            0, 0,
            EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Essentials Eco does not support bank accounts!");

    public EssentialsEcoEconomy(Valv plugin) {
        Plugin essentials = plugin.getServer().getPluginManager().getPlugin("Essentials");
        plugin.getLogger().info("Hooked Valv into Essentials");
    }

    @Override
    public String getName() {
        return "Essentials";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public boolean hasMultiworldSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return -1;
    }

    @Override
    public String format(double amount) {
        return Economy.format(amount);
    }

    @Override
    public String currencyNameSingular() {
        return "";
    }

    @Override
    public String currencyNamePlural() {
        return "";
    }

    @Override
    public boolean hasAccount(String playerName) {
        return Economy.playerExists(playerName);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return Economy.playerExists(playerName);
    }

    @Override
    public double getBalance(String playerName) {
        try {
            return Economy.getMoney(playerName);
        } catch (UserDoesNotExistException e) {
            createPlayerAccount(playerName);
            return 0;
        }
    }

    @Override
    public double getBalance(String playerName, String worldName) {
        return getBalance(playerName);
    }


    @Override
    public boolean has(String playerName, double amount) {
        try {
            return Economy.hasEnough(playerName, amount);
        } catch (UserDoesNotExistException e) {
            return false;
        }
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (amount < 0)
            return new EconomyResponse(
                    0, 0,
                    EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds");
        EconomyResponse response = new EconomyResponse(amount, 0, EconomyResponse.ResponseType.SUCCESS, null);
        try {
            Economy.subtract(playerName, amount);
            response.balance = Economy.getMoney(playerName);
            response.type = EconomyResponse.ResponseType.SUCCESS;
        } catch (NoLoanPermittedException e) {
            try {
                response.balance = Economy.getMoney(playerName);
                response.amount = 0;
                response.type = EconomyResponse.ResponseType.FAILURE;
                response.errorMessage = "Loan was not permitted";
            } catch (UserDoesNotExistException e1) {
                // Unreachable statement(?)
            }
        } catch (UserDoesNotExistException e) {
            if (createPlayerAccount(playerName)) return withdrawPlayer(playerName, amount);
            else {
                response.type = EconomyResponse.ResponseType.FAILURE;
                response.errorMessage = "User does not exist";
                response.balance = 0;
            }
        }
        return response;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (amount < 0)
            return new EconomyResponse(
                    0, 0,
                    EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative funds");
        EconomyResponse response = new EconomyResponse(amount, 0, EconomyResponse.ResponseType.SUCCESS, null);
        try {
            Economy.add(playerName, amount);
            response.balance = Economy.getMoney(playerName);
            response.type = EconomyResponse.ResponseType.SUCCESS;
        } catch (NoLoanPermittedException e) {
            // Unreachable statement(?)
        } catch (UserDoesNotExistException e) {
            if (createPlayerAccount(playerName)) return withdrawPlayer(playerName, amount);
            else {
                response.type = EconomyResponse.ResponseType.FAILURE;
                response.errorMessage = "User does not exist";
                response.balance = 0;
            }
        }
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse createBank(String name, String playerName) {
        return unimplemented;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return unimplemented;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return unimplemented;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return unimplemented;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return unimplemented;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return unimplemented;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return unimplemented;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return unimplemented;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return !hasAccount(playerName) && Economy.createNPC(playerName);
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }
}
