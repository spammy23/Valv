package io.github.gitgideon.valv.api;

public class EconomyResponse {

    private final double amount;
    private final double balance;
    private final ResponseType type;
    private final String errorMessage;

    /**
     * @param amount       Amount modified
     * @param balance      Balance after transaction
     * @param type         Whether the transaction succeeded
     * @param errorMessage The error message if necessary (commonly null)
     */
    public EconomyResponse(double amount, double balance, ResponseType type, String errorMessage) {
        this.amount = amount;
        this.balance = balance;
        this.type = type;
        this.errorMessage = errorMessage;
    }

    /**
     * Checks whether the transaction was successful
     *
     * @return true if the transaction was successful, otherwise false
     */
    public final boolean transactionSuccess() {
        return type == ResponseType.SUCCESS;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ResponseType getType(){
        return type;
    }

    public enum ResponseType {
        SUCCESS, FAILURE, NOT_IMPLEMENTED
    }

}
