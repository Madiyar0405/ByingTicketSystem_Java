package com.example.ticketsystem.model;

public enum PassengerType {
    CHILD(0.10),
    RETIRED(0.30),
    COMMON(0.0);

    private final double discountFraction;

    PassengerType(double discountFraction) {
        this.discountFraction = discountFraction;
    }

    public double getDiscountFraction() {
        return discountFraction;
    }

    public static PassengerType fromAge(int age) {
        if (age >= 0 && age < 5) {
            return CHILD;
        }
        if (age > 62) {
            return RETIRED;
        }
        return COMMON;
    }
}
