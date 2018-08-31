package auction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BidsRepository extends CrudRepository<AuctionItemBid, Long> {
	public AuctionItemBid findById(Integer id);
	
	public Iterable<AuctionItemBid> findByBidder(User bidder);
	
/*
	@Query("select auction_item_bid.bidder_id from mydb.auction_item_bid JOIN mydb.auction_item "
			+ "ON auction_item_bid.auction_item_id = auction_item.id JOIN mydb.auction "
			+ "ON auction_id = auction.id"
			+ "where auction_item_bid.bid_ts <= auction.end_auction "
			+ "and auction_item_bid.auction_item_id = auction_item.id and auction_item_bid.bid_price = "
			+ "(select MAX(auction_item_bid.bid_price) from mydb.auction_item_bid JOIN mydb.auction_item "
			+ "ON auction_item_bid.auction_item_id = auction_item.id JOIN mydb.auction "
			+ "ON auction_id = auction.id"
			+ "where auction_item_bid.bid_ts <= auction.end_auction and auction_item_bid.auction_item_id = auction_item.id)")
	
	@Query("select auction_item_bid.bidder_id from mydb.auction_item_bid JOIN mydb.auction_item "
			+ "ON b.auction_item_id = auction_item.id JOIN mydb.auction "
			+ "ON auction_id = auction.id"
			+ "where b.bid_ts <= auction.end_auction "
			+ "and auction_item_bid.auction_item_id = auction_item.id and auction_item_bid.bid_price = "
			+ "(select MAX(auction_item_bid.bid_price) from mydb.auction_item_bid JOIN mydb.auction_item "
			+ "ON auction_item_bid.auction_item_id = auction_item.id JOIN mydb.auction "
			+ "ON auction_id = auction.id"
			+ "where auction_item_bid.bid_ts <= auction.end_auction and auction_item_bid.auction_item_id = auction_item.id)")
	public int getWinningBid();*/
}