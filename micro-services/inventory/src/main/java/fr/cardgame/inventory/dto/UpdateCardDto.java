package fr.cardgame.inventory.dto;

public class UpdateCardDto {

    private Integer idCard;
    private Integer energy;

    public UpdateCardDto() {
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }
}
