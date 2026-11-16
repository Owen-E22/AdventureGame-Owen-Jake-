package application;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Village extends Location{

	
	private Main game;
	public Village(Main game) {
        this.game = game;
    }
	
	private boolean hasBeenHere = false;
	private boolean hasSword = false;
	private boolean hasStrangeBox = false;
	
	
	public boolean isHasSword() {
		return hasSword;
	}

	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
	}

	public boolean isHasStrangeBox() {
		return hasStrangeBox;
	}

	public void setHasStrangeBox(boolean hasStrangeBox) {
		this.hasStrangeBox = hasStrangeBox;
	}

	private String boughtItem;
	
	
	public Village() {
		
	}

	@Override
	public void story(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		buttonReset(option1, option2, option3, option4);
		
		if(!hasBeenHere) {
		option1.setText("Continue");
		
		
		option1.setVisible(true);
		option2.setVisible(false);
		option3.setVisible(false);
		outputBox.setText("""
				
				
				You enter the village and see many shops and stands buying and selling an assortment of products
				
				The area is filled with the sounds of animals and busy people
				
				As you look around you notice that no one really seems happy
				
				
				""");
		
		option1.setOnAction(e -> {
			villageSquare(outputBox, option1, option2, option3, option4);
		});
		}
		else {
			villageSquare(outputBox, option1, option2, option3, option4);
		}
		
	}
	
	public void villageSquare(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		buttonReset(option1, option2, option3, option4);
		option1.setText("Buy shop");
		option2.setText("Sell shop");
		option3.setText("Ask Shopkeeper");
		
		option1.setVisible(true);
		option2.setVisible(true);
		option3.setVisible(true);
		
		outputBox.setText("""
				
				""");
		
		option1.setOnAction(e -> {
			buyShop(outputBox, option1, option2, option3, option4);
		});
		option2.setOnAction(e -> {
			sellShop(outputBox, option1, option2, option3, option4);
		});
		option3.setOnAction(e -> {
			askShopKeeper(outputBox, option1, option2, option3, option4);
		});
		
		
		
	}
	
	public void getLamb(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		if(game.getCastle().isObeyDarkKing()) {
			//getting lamb for feast urgent
			
			game.getCastle().setHasLamb(true);
		}
		else {
			if(game.getStart().isHasGold()) {
				outputBox.appendText("""
						
						Shopkeeper: "Here is a lamb"
						"Should be pretty tasty"
						
						""");
				game.getCastle().setHasLamb(true);
			}
			else {
				cannotPurchase(outputBox, option1, option2, option3, option4);
			}
		}
		
		
	}
	
	public void sellShop(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		//if you sell some item you can find in the cave you can get gold
		//or maybe if you sell a certain item the shopkeeper will help you with something?
	}
	public void buyShop(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		
		
		option1.setVisible(true);
		option2.setVisible(true);
		option3.setVisible(true);
		option4.setVisible(true);

		option1.setText("Buy Sword");
		option2.setText("Buy Strange Box");
		option3.setText("Buy Lamb");
		option4.setText("Exit Buy Shop");

		outputBox.setText("""
				
				Shopkeeper: "Hey there, What can I get ya!"
				
				"What do you have for sale?"
				
				Shopkeeper: "Here are my items, 1 Gold a piece!"
				
				- Sword
				- Strange box
				- Lamb
				
				
				""");
		
		
		if(game.getStart().isHasGold()) {  //add another location you can get gold from
		option1.setOnAction(e -> {
			boughtItem = "Sword";
			purchaseItem(outputBox, option1, option2, option3, option4, boughtItem);
		});
		option2.setOnAction(e -> {
			boughtItem = "StrangeBox";
			purchaseItem(outputBox, option1, option2, option3, option4, boughtItem);
		});
		}
		else {
			option1.setOnAction(e -> {
				cannotPurchase(outputBox, option1, option2, option3, option4);
			});
			option2.setOnAction(e -> {
				cannotPurchase(outputBox, option1, option2, option3, option4);
			});
			
		}
		option3.setOnAction(e -> {
			getLamb(outputBox, option1, option2, option3, option4);
		});
		option4.setOnAction(e -> {
			villageSquare(outputBox, option1, option2, option3, option4);
		});
	}
	
	public void purchaseItem(TextArea outputBox, Button option1, Button option2, Button option3, Button option4, String item) {
		if(item.equals("Sword")) {
			hasSword = true;
			
			outputBox.appendText("""
					
					Shopkeeper: "Ahh a sword, alright then"
					"Be careful with that, if the dark knight see you with it they will not be happy"
					"Consider yourself warned"
					
					""");
		}
		else if(item.equals("StrangeBox")) {
			hasStrangeBox = true;
			
			outputBox.appendText("""
					
					Shopkeeper: "The box huh, I've got not a clue what could be inside"
					"I can't seem to find out how to open it"
					"Not sure what you want with it but here you go!"
					
					""");
		}
		
		
		
	}
	
	
	public void askShopKeeper(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		
		option1.setVisible(true);
		option2.setVisible(false);
		option3.setVisible(false);
		
		option1.setText("Continue");
		
		
		outputBox.setText("""
				
				"Hey what's with all the sad faces?"
				
				Shopkeeper: "Ever since The Dark Crown have taken over, we have been opressed and used"
				"They come into our village and take what they want and there's not a thing we can do"
				
				"Why don't you resist and fight back?"
				
				Shopkeeper: "Fight back! You are a funny man"
				"We would be squashed like bugs under thier boots"
				"Maybe if King Aldric wasn't dead we would have a chance"
				
				
				""");
		
		option1.setOnAction(e -> {
			villageSquare(outputBox, option1, option2, option3, option4);
		});
	}
	
	
	public void cannotPurchase(TextArea outputBox, Button option1, Button option2, Button option3, Button option4) {
		outputBox.appendText("""
				
				Cannot purchase item - not enough Gold
				
				""");
		buyShop(outputBox, option1, option2, option3, option4);
	}
	
	
	
	

}
