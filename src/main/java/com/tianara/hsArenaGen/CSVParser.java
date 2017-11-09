package com.tianara.hsArenaGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CSVParser {
	
	private Scanner sc;
	private Map<String, Map<String, List<Card>>> cards;

	public CSVParser() {}

	public void parse(File file) throws FileNotFoundException {
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw e;
		}
		
		cards = new HashMap<>();
		cards.put("Basic", new HashMap<>());
		cards.put("Common", new HashMap<>());
		cards.put("Rare", new HashMap<>());
		cards.put("Epic", new HashMap<>());
		cards.put("Legendary", new HashMap<>());
		
		String[] classes = {"Rogue", "Shaman", "Druid", "Warlock", "Warrior", "Hunter", "Paladin", "Mage", "Priest", "Neutral"};
		
		for(String clas : classes) {
			cards.get("Basic").put(clas, new ArrayList<>());
			cards.get("Common").put(clas, new ArrayList<>());
			cards.get("Rare").put(clas, new ArrayList<>());
			cards.get("Epic").put(clas, new ArrayList<>());
			cards.get("Legendary").put(clas, new ArrayList<>());
		}
	}
	
	private void readOneCard(String row) {
		String[] values = row.split("\t");
		
		String name = values[2];
		String rarity = values[3];
		String clas = values[5];
		String set = values[16];
		
		int quantity = Math.min(2, Integer.parseInt(values[6]) + Integer.parseInt(values[7]));
		
		Card card = new Card(name);
		
		if(set.equals("Standard")) {
			for(int i = 0; i < quantity; i++) {
				cards.get(rarity).get(clas).add(card);
			}
		}
	}
	
	public Map<String, Map<String, List<Card>>> readAll() {
		sc.nextLine();
		while(sc.hasNextLine()) {
			readOneCard(sc.nextLine());
		}
		
		return cards;
	}
	
}
