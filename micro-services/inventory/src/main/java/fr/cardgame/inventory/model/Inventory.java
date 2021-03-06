package fr.cardgame.inventory.model;

import javax.persistence.*;

@Entity(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String family;
    private int hp;
    private int energy;
    private int defence;
    private int attack;
    private int price;
    private String imgUrl;
    private Integer idUser;

    public Inventory() {
    }

    public Inventory(String name, String description, String family, int hp, int energy, int defence, int attack, int price, String imgUrl, int idUser) {
        this.name = name;
        this.description = description;
        this.family = family;
        this.hp = hp;
        this.energy = energy;
        this.defence = defence;
        this.attack = attack;
        this.price = price;
        this.imgUrl = imgUrl;
        this.idUser = idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getIdUser() {
        return idUser;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

}