package auction;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UsersController {
	
	@PostConstruct
	public void initialize(){
		//users = new ArrayList<User>();
	}
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String fName,@RequestParam String lName, 
			@RequestParam String email, @RequestParam String password) {
		//todo: check if user exists
		User s = userRepository.findByEmail(email);
		if(s != null){
			return null;
		}
		
		s = new User();
		s.setFirstName(fName);
		s.setLastName(lName);
		s.setEmail(email);
		s.setPassword(password);
		//users.add(s);
		userRepository.save(s);
		return s.getId().toString();
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<User> loginUser (@RequestParam String email, @RequestParam String password) {
		User s = userRepository.findByEmail(email);
		if(s != null && s.getPassword().compareTo(password) ==0){
			return new ResponseEntity<User>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers () {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/{userId}/bids")
	public @ResponseBody Iterable<AuctionItemBid> getUserBids(@PathVariable Integer userId) {
		User u = userRepository.findById(userId);
		return u.getBids();
	}
	
	@GetMapping(path="/{userId}/auctions")
	public @ResponseBody Iterable<Auction> getUserAuctions(@PathVariable Integer userId) {
		User u = userRepository.findById(userId);
		return u.getAuctions();
	}
	
	@Autowired
	private UserRepository userRepository;
}
