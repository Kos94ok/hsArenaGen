package com.tianara.hsArenaGen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeckGenerator {
	
	private Map<String, Integer> rarityAmount = new HashMap<>();
	private Map<String, Map<String, List<Card>>> cards;
	
	//Determined by fair die roll :3
	public void setRarities() {
		rarityAmount.put("Common", 13);
		rarityAmount.put("Rare", 9);
		rarityAmount.put("Epic", 5);
		rarityAmount.put("Legendary", 3);
	}
	
	public List<Card> generate(String clas) {
		
		List<Card> deck = new ArrayList<>();
		
		for(String rarity : new String[] {"Common", "Rare", "Epic", "Legendary"}) {
			List<Card> rarityCards = new ArrayList<>();
			
			rarityCards.addAll(cards.get(rarity).get(clas));
			rarityCards.addAll(cards.get(rarity).get("Neutral"));
			
			Set<Integer> indices = new HashSet<>();
			
			while(indices.size() != rarityAmount.get(rarity)) {
				indices.add((int)(Math.random() * (double)(rarityCards.size() - 1)));
			}

			for(int index : indices) {
				deck.add(rarityCards.get(index));
			}
		}
		
		return deck;
	}
}
