import java.util.ArrayList;

public class Table {
	static final int MAXPLAYER = 4;
	private Deck AllCard;private Player[] Allplayer;private Dealer dealer;private int[] pos_betArray = new int[MAXPLAYER];
	public Table(int nDeck){
		Deck deck=new Deck(4);
		AllCard = deck;
		Allplayer=new Player[MAXPLAYER];
	}

	public void set_player(int pos, Player p){
		Allplayer[pos]=p;
	}
	public Player[] get_player(){
		return Allplayer;
	}
	public void set_dealer(Dealer d){
		dealer=d;
	}
	public Card get_face_up_card_of_dealer(){
		return dealer.getOneRoundCard().get(1);
	}
	private void ask_each_player_about_bets(){
		for(int i=0;i<MAXPLAYER;i++){
		 Allplayer[i].say_hello();
		 pos_betArray[i]=Allplayer[i].make_bet();
		 }
	}
	private void distribute_cards_to_dealer_and_players(){
		ArrayList<Card> dealerCard=new ArrayList<Card>();
		dealerCard.add(AllCard.getOneCard(true));
		dealerCard.add(AllCard.getOneCard(true));
		dealer.setOneRoundCard(dealerCard);
		for(int i=0;i<MAXPLAYER;i++){
			ArrayList<Card> playerCard=new ArrayList<Card>();
			playerCard.add(AllCard.getOneCard(true));
			dealerCard.add(AllCard.getOneCard(true));
			Allplayer[i].setOneRoundCard(playerCard);
			System.out.println("Dealer's face up card is");
		Card c = new Card(dealer.getOneRoundCard().get(1).getSuit(),dealer.getOneRoundCard().get(1).getRank());
		c.printCard();
		}
		
	}
	private void ask_each_player_about_hits(){
		
			for(int i=0;i<MAXPLAYER;i++){
				boolean hit=false;
			do{
				hit=Allplayer[i].hit_me(this); 
				if(hit){
					Allplayer[i].getOneRoundCard().add(AllCard.getOneCard(true));
					System.out.print("Hit! ");
					System.out.println(Allplayer[i].get_name()+"'s Cards now:");
					Allplayer[i].printAllCard();
				}
				else{
					System.out.println(Allplayer[i].get_name()+", Pass hit!");
					System.out.println(Allplayer[i].get_name()+", Final Card:");
					Allplayer[i].printAllCard();
					
				}
			}while(hit);
			
						
		}

		
	}
	private void ask_dealer_about_hits(){
		boolean hit=false;
		do{
			hit=dealer.hit_me(this); 
			if(hit){
				dealer.getOneRoundCard().add(AllCard.getOneCard(true));
			}
			else{
				System.out.println("Dealer's hit is over!");
			
			}
		}while(hit);

	}
	private void calculate_chips(){
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");
		dealer.printAllCard();
		for(int i=0;i<MAXPLAYER;i++){
			System.out.println(Allplayer[i].get_name()+" card value is "+Allplayer[i].getTotalValue());
			if(Allplayer[i].getTotalValue()>21 && dealer.getTotalValue()>21){
				System.out.println(",chips have no change! The Chips now is: "+Allplayer[i].get_current_chips());
			}else if(Allplayer[i].getTotalValue()<21&&dealer.getTotalValue()>21){
				Allplayer[i].increase_chips(pos_betArray[i]);
				System.out.println(",Get "+pos_betArray[i]+" Chips, the Chips now is: "+Allplayer[i].get_current_chips());
			}else if(Allplayer[i].getTotalValue()>21&&dealer.getTotalValue()<=21){
				Allplayer[i].increase_chips(pos_betArray[i]);
				System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+Allplayer[i].get_current_chips());
			}else if(Allplayer[i].getTotalValue()>dealer.getTotalValue()&&Allplayer[i].getTotalValue()<=21){
				Allplayer[i].increase_chips(pos_betArray[i]);
				System.out.println("Get "+pos_betArray[i]+" Chips, the Chips now is: "+Allplayer[i].get_current_chips());
			}else if(Allplayer[i].getTotalValue()<dealer.getTotalValue()&&dealer.getTotalValue()<=21){
				Allplayer[i].increase_chips(pos_betArray[i]);
				System.out.println(", Loss "+pos_betArray[i]+" Chips, the Chips now is: "+Allplayer[i].get_current_chips());
			}else{
				System.out.println(",chips have no change! The Chips now is: "+Allplayer[i].get_current_chips());
			}
		}

	}
	public int[] get_palyers_bet(){
		return  pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}





}
