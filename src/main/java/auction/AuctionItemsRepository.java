package auction;

import org.springframework.data.repository.CrudRepository;

public interface AuctionItemsRepository extends CrudRepository<AuctionItem, Long> {
	public AuctionItem findById(Integer id);
}
