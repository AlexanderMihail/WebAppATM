package org.example.atm;

public class Account {
    public String cardNumber;
    public int balance = 0;
    Account(String cardNumber){ this.cardNumber = cardNumber; }
    int getBalance() { return balance; }
    void setBalance(int value) { balance = value; }
}
