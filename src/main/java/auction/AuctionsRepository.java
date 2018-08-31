package auction;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuctionsRepository extends CrudRepository<Auction, Long> {
	public Auction findById(Integer id);
	
	public List<Auction> findByEndAuctionLessThanEqual(Date endDate);
} 
