package io.github.gitgideon.valv.api;

public class EconomyResponse {

    public double amount;
    public double balance;
    public ResponseType type;
    public String errorMessage;

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
    public boolean transactionSuccess() {
        return type == ResponseType.SUCCESS;
    }

    public enum ResponseType {
        SUCCESS, FAILURE, NOT_IMPLEMENTED
    }

}
