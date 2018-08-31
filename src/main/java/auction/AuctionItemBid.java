package auction;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AuctionItemBid {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id; 
	@ManyToOne
	private User bidder;
	@ManyToOne
	private AuctionItem auctionItem;
	private int bidPrice;
	private Date bidTs;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getBidder() {
		return bidder;
	}
	public void setBidder(User bidder) {
		this.bidder = bidder;
	}
	public AuctionItem getAuctionItem() {
		return auctionItem;
	}
	public void setAuctionItem(AuctionItem auctionItem) {
		this.auctionItem = auctionItem;
	}
	public int getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Date getBidTs() {
		return bidTs;
	}
	public void setBidTs(Date bidTs) {
		this.bidTs = bidTs;
	} 
}
