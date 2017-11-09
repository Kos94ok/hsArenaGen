package com.tianara.hsArenaGen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeckGenerator {
	
	int common;
	int rare;
	int epic;
	int legendary;
	
	private Map<String, Map<String, List<Card>>> cards;
	
	//Determined by fair die roll :3
	public void setRarities() {
		common = 13;
		rare = 9;
		epic = 5;
		legendary = 3;
	}
	
	public List<Card> generate(String clas) {
		List<Card> commonCards = cards.get("Basic").get(clas);
		commonCards.addAll(cards.get("Basic").get("Neutral"));
		commonCards.addAll(cards.get("Common").get(clas));
		commonCards.addAll(cards.get("Common").get("Neutral"));
		
		List<Card> rareCards = cards.get("Rare").get(clas);
		commonCards.addAll(cards.get("Rare").get("Neutral"));
		
		List<Card> epicCards = cards.get("Epic").get(clas);
		commonCards.addAll(cards.get("Epic").get("Neutral"));
		
		List<Card> legendaryCards = cards.get("Legendary").get(clas);
		commonCards.addAll(cards.get("Legendary").get("Neutral"));
		
		Set<Integer> commonIndices = new HashSet<>();
		
		while(commonIndices.size() != common) {
			commonIndices.add((int)(Math.random() * (double)commonCards.size()));
		}
		
		Set<Integer> rareIndices = new HashSet<>();
		
		while(rareIndices.size() != common) {
			rareIndices.add((int)(Math.random() * (double)rareCards.size()));
		}
		
		Set<Integer> epicIndices = new HashSet<>();
		
		while(epicIndices.size() != common) {
			epicIndices.add((int)(Math.random() * (double)epicCards.size()));
		}
		
		Set<Integer> legendaryIndices = new HashSet<>();
		
		while(legendaryIndices.size() != common) {
			legendaryIndices.add((int)(Math.random() * (double)legendaryCards.size()));
		}
		
		List<Card> deck = new ArrayList<>();
		
		for(int index : commonIndices) {
			deck.add(commonCards.get(index));
		}

		for(int index : rareIndices) {
			deck.add(rareCards.get(index));
		}

		for(int index : epicIndices) {
			deck.add(epicCards.get(index));
		}

		for(int index : legendaryIndices) {
			deck.add(legendaryCards.get(index));
		}
		
		return deck;
	}
}
