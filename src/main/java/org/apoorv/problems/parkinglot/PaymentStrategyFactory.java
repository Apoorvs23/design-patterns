package org.apoorv.problems.parkinglot;

public class PaymentStrategyFactory {
    public static PaymentStrategy createPaymentStrategy(String type, String... params) {
        switch (type.toUpperCase()) {
            case "CREDIT_CARD":
                if (params.length != 3) {
                    throw new IllegalArgumentException("Credit card payment requires card number, CVV, and expiry date");
                }
                return new CreditCardPayment(params[0], params[1], params[2]);
            case "DEBIT_CARD":
                if (params.length != 2) {
                    throw new IllegalArgumentException("Debit card payment requires card number and PIN");
                }
                return new DebitCardPayment(params[0], params[1]);
            default:
                throw new IllegalArgumentException("Invalid payment type: " + type);
        }
    }
} 