package fr.cardgame.user.dto;

public class UpdateUserCashDto {
    private int id;
    private int cash;

    public UpdateUserCashDto() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
