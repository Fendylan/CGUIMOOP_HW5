import java.util.ArrayList;
import java.util.Random;
public class Deck{
	public int nUsed;
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList<Card> openCard; 
	//TODO: Please implement the constructor
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		openCard=new ArrayList<Card>();
		for(int n =0;n<nDeck;n++){
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		
		for(Card.Suit s : Card.Suit.values())
			for (int j = 1; j < 14; j++) { 
				Card card = new Card (s,j);
				cards.add(card);
			
			} 
		}
		shuffle();
	}	
	//TODO: Please implement the method to print all cards on screen
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//please implement and reuse printCard method in Card class
		for(Card x:cards){
			x.printCard();
		}
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	
	public void shuffle(){
		usedCard=new ArrayList<Card>();
		openCard=new ArrayList<Card>();
	    Random rnd = new Random();
	    for (int i = 52 - 1; i >= 0; i--) {
	        int j = rnd.nextInt(51);

	        /* swap cards i,j */
	        Card card = cards.get(i);
	        cards.set(i, cards.get(j));
	        cards.set(j, card);
	    }
	    usedCard.clear();
	    nUsed=0;
	    
	    
}
	public Card getOneCard(){
		if(nUsed==52)shuffle();
		Card a = cards.get(nUsed);
		usedCard.add(a);
		nUsed++;
		return a;
		
		
		
	}
	public Card getOneCard (boolean isOpened){
		if(nUsed==52)shuffle();
		Card a = cards.get(nUsed);
		if(isOpened){
			openCard.add(a);
		}
		usedCard.add(a);
		nUsed++;
		return a;
	}
	public ArrayList<Card> getOpenedCard() {
		return openCard;
	}
}