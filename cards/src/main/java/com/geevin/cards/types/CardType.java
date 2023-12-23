package com.geevin.cards.types;


public enum CardType {
    PLATNIUM("Platinum", 100000.00), SUPREME("Supreme", 2000000.00), DINE("Dine", 50000.00), ENTERTAINMENT("Entertainment", 75000.00), TRAVELLER("Traveller", 500000.00);

    public String value;
    public double limit;
    CardType(String value, double limit) {
        this.value = value;
        this.limit = limit;
    }

    public static CardType elementOfValue(String value) {
        for (CardType card : CardType.values()) if (card.value.equals(value)) return card;
        return null;
    }
}
