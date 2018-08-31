package auction;


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
public class AuctionItem 
{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private int minimumPrice;
	//private AuctionItemBid topBid;
	
	@ManyToOne
	private Auction auction;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="auctionItem")
	private Set<AuctionItemBid> bids;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="auctionItem")
	private Set<AuctionItemImage> images; 
	//list of bidders?
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
	public int getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(int minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	/*private AuctionItemBid getTopBid() {
		return topBid;
	}
	private void setTopBid(AuctionItemBid topBid) {
		this.topBid = topBid;
	}*/

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	
	@JsonIgnore
	public Set<AuctionItemBid> getBids() {
		return bids;
	}

	public void setBids(Set<AuctionItemBid> bids) {
		this.bids = bids;
	}
	
	@JsonIgnore
	public Set<AuctionItemImage> getImages() {
		return images;
	}

	public void setImages(Set<AuctionItemImage> images) {
		this.images = images;
	}
}
