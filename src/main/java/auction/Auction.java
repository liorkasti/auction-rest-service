package auction;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Auction {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String title;
	private String description;
	private Date startAuction;
	private Date endAuction;
	private String auctionType;
	private String imageUrl;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="auction")
	private Set<AuctionItem> auctionItems;
	
	@ManyToOne
	private User owner;
	
	private boolean silentAuction;
	//list of bidders?
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartAuction() {
		return startAuction;
	}
	public void setStartAuction(Date startAuction) {
		this.startAuction = startAuction;
	}
	public Date getEndAuction() {
		return endAuction;
	}
	public void setEndAuction(Date endAuction) {
		this.endAuction = endAuction;
	}
	public String getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}

	public boolean isSilentAuction() {
		return silentAuction;
	}
	public void setSilentAuction(boolean silentAuction) {
		this.silentAuction = silentAuction;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Set<AuctionItem> getAuctionItems() {
		return auctionItems;
	}
	public void setAuctionItems(Set<AuctionItem> auctionItems) {
		this.auctionItems = auctionItems;
	}
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}



}
