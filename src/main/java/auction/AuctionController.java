package auction;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path="/auction")
public class AuctionController {
	
	private final AtomicLong counter = new AtomicLong();
	
	@PostConstruct
	public void initialize(){
		//auctions = new ArrayList<Auction>();
	}
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewAuction (@RequestParam String title,
			@RequestParam String description, 
			@DateTimeFormat(pattern="dd-MM-yyyy HH:mm") @RequestParam Date startTime, 
			@DateTimeFormat(pattern="dd-MM-yyyy HH:mm") @RequestParam Date endTime, 
			@RequestParam boolean isSilent,
			@RequestParam Integer user) {
		//todo: check if user exists
		Auction a = new Auction();
		User s = userRepository.findById(user);
		a.setTitle(title);
		a.setDescription(description);
		a.setStartAuction(startTime);
		a.setEndAuction(endTime);
		a.setSilentAuction(isSilent);
		a.setOwner(s);
		//auctions.add(a);
		auctionRepository.save(a);
		return a.getId().toString();
	}
	
	@PostMapping(path="/{auctionId}/additem")
	public @ResponseBody String addNewAuctionItem (@PathVariable Integer auctionId, 
			@RequestParam String itemName,
			@RequestParam String description,
			@RequestParam Integer price) {
		Auction a = auctionRepository.findById(auctionId);
		if(a != null){
			AuctionItem t = new AuctionItem();
			t.setName(itemName);
			t.setDescription(description);
			t.setMinimumPrice(price);
			t.setAuction(a);
			itemsRepository.save(t);
			
			a.getAuctionItems().add(t);
			auctionRepository.save(a);
			
			//auctions.add(a);
			return t.getId().toString();
		}
		return "-1";
	}
	
	@GetMapping(path="/{auctionId}/items")
	public @ResponseBody Iterable<AuctionItem> getAuctionItems (@PathVariable Integer auctionId) {
		Auction a = auctionRepository.findById(auctionId);
		if(a != null){
			return a.getAuctionItems();
		}
		return null;
	}
	
	@PostMapping(path="/{auctionId}/{itemId}/bid")
	public @ResponseBody AuctionItem getAuctionItem (@PathVariable Integer auctionId, 
			@PathVariable Integer itemId, 
			@RequestParam Integer userId,
			@RequestParam Integer price) {
		AuctionItem a = itemsRepository.findById(itemId);
		AuctionItemBid b = new AuctionItemBid();
		User s = userRepository.findById(userId);
		b.setBidder(s);
		b.setAuctionItem(a);
		b.setBidPrice(price);
		b.setBidTs(new Date());
		b.setAuctionItem(a);
		userRepository.save(s);
		a.getBids().add(b);
		itemsRepository.save(a);
		return a;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{auctionId}/{itemId}/getImage" )
	public @ResponseBody AuctionItemImage getAcutionItemImage (@PathVariable Integer auctionId, @PathVariable Integer itemId){
		AuctionItem a = itemsRepository.findById(itemId);
		List<AuctionItemImage> list = new ArrayList<AuctionItemImage>(a.getImages());
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{auctionId}/{itemId}/uploadImage" )
	public @ResponseBody String addAcutionItemImage (@PathVariable Integer auctionId, 
			@PathVariable Integer itemId, 
			@RequestParam("file") MultipartFile file,
			@RequestParam String desc) {
		AuctionItem a = itemsRepository.findById(itemId);
		String name = String.valueOf(counter.getAndIncrement());
		String fileName = name + ".jpg";
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
               /* BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                stream.write(bytes);
                stream.close();*/
                AuctionItemImage im= new AuctionItemImage();
                im.setImageUrl(name);
                im.setDescription(desc);
                im.setImage(bytes);
                im.setAuctionItem(a);
                itemsImageRepository.save(im);
                a.getImages().add(im);
                itemsRepository.save(a);
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
	}
	
	@GetMapping(path="/{auctionId}/{itemId}/bids")
	public @ResponseBody Iterable<AuctionItemBid> getAuctionItemBid (@PathVariable Integer auctionId, 
			@PathVariable Integer itemId) {
		AuctionItem a = itemsRepository.findById(itemId);
		return a.getBids();
	}
	
	private int getWinnerId(int itemId){
		Query query = em.createQuery("select bid.id from AuctionItemBid bid JOIN bid.auctionItem ai JOIN ai.auction a"
				//+ " ON bid.auction_item_id = ai.id JOIN Auction a"
				//+ " ON ai.auction_id = a.id"
				+ " where bid.bidTs <= a.endAuction"
				+ " and ai.id = :item  "
				+ "and bid.bidPrice =" 
				+ " (select MAX(bid.bidPrice) from bid.auctionItem ai JOIN ai.auction a"
				//+ " ON bid.auction_item_id = ai.id JOIN Auction a"
				//+ " ON ai.auction_id = a.id"
				//+ " JOIN ai.Auction a"
				+ " where bid.bidTs <= a.endAuction and ai.id= :item) ORDER BY bid.id");
	    query.setParameter("item", itemId);
	    return (int)query.getResultList().get(0);
	}
	
	@GetMapping(path="/{auctionId}/{itemId}/winner")
	public @ResponseBody int getAuctionItemWinner (@PathVariable Integer auctionId, @PathVariable Integer itemId) {
	    return getWinnerId(itemId);
	}
	
	@GetMapping(path="/{auctionId}/{itemId}/iswinner")
	public @ResponseBody boolean getIsAuctionItemWinner(@PathVariable Integer auctionId, @PathVariable Integer itemId, 
			@RequestParam int bidId) {
	    return getWinnerId(itemId) == bidId;
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Auction> getAll () {
		return auctionRepository.findAll();
	}
	
	
	
	
	@Autowired
	private AuctionsRepository auctionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuctionItemsRepository itemsRepository;
	@Autowired
	private AuctionItemImageRepository itemsImageRepository;
	
	@PersistenceContext
	private EntityManager em;
	//public static ArrayList<Auction> auctions = new ArrayList<Auction>();
}