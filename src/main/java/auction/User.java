package auction;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="owner")
	private Set<Auction> auctions;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="bidder")
	private Set<AuctionItemBid> bids;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private boolean isAdmin;
	//List of auctions ?
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private boolean isAdmin() {
		return isAdmin;
	}
	private void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@JsonIgnore
	public Set<AuctionItemBid> getBids() {
		return bids;
	}
	public void setBids(Set<AuctionItemBid> bids) {
		this.bids = bids;
	}
	@JsonIgnore
	public Set<Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(Set<Auction> auctions) {
		this.auctions = auctions;
	}
	public Integer getId(){
		return id;
	}
	
}
