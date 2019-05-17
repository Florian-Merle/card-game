package fr.cardgame.user.model;

import javax.persistence.*;

import fr.cardgame.card.model.Card;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "user")
public class User {

	public static final Integer DEFAULT_CASH = 1000;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
    private Integer cash;

    // fetch type eager -> when transforming it to json, fetch this data
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Card> cards;
    
    public User() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.password = "";
		this.cash = User.DEFAULT_CASH;
		this.cards = new ArrayList<Card>();
    }

	public User(String firstName, String lastName, String email, String password, Integer cash) {
		super();

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.cash = cash;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCash() {
		return this.cash;
	}

	public void setCash(Integer cash) {
		this.cash = cash;
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
