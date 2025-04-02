package org.apoorv.problems.parkinglot.payment;

import org.apoorv.problems.parkinglot.models.*;

public interface PaymentStrategy {
    public void pay(double amount);
}
