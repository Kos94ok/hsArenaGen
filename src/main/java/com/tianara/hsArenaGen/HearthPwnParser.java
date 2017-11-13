package com.tianara.hsArenaGen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HearthPwnParser {

	private String nickname;
	private Map<String, Map<String, List<Card>>> cards = new HashMap<>();
	
	public HearthPwnParser() {
		
	}
	
	public void initialize() {
		cards = new HashMap<>();
		cards.put("Common", new HashMap<>());
		cards.put("Rare", new HashMap<>());
		cards.put("Epic", new HashMap<>());
		cards.put("Legendary", new HashMap<>());
		
		String[] classes = {"Rogue", "Shaman", "Druid", "Warlock", "Warrior", "Hunter", "Paladin", "Mage", "Priest", "None"};
		
		for(String clas : classes) {
			cards.get("Common").put(clas, new ArrayList<>());
			cards.get("Rare").put(clas, new ArrayList<>());
			cards.get("Epic").put(clas, new ArrayList<>());
			cards.get("Legendary").put(clas, new ArrayList<>());
		}
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Map<String, Map<String, List<Card>>> readAll() throws IOException {
		
		Document doc = Jsoup.parse(new URL("http://www.hearthpwn.com/members/" + nickname + "/collection"), 1000000);
		
		Elements divs = doc.select(".listing-body div");
		
		for(Element div : divs) {
			String rarityAttr = div.attr("data-rarity");
			String rarity = "Common";
			
			if(rarityAttr.equals("3")) {
				rarity = "Rare";
			} else if(rarityAttr.equals("4")) {
				rarity = "Epic";
			} else if(rarityAttr.equals("5")) {
				rarity = "Legendary";
			}
			
			String classAttr = div.attr("data-card-class");
			String clas;
			if(classAttr.equals("NONE")) {
				clas = "Neutral";
			} else {
				clas = classAttr.substring(0, 1) + classAttr.substring(1).toLowerCase();
			}
			
			String nameAttr = div.attr("data-card-name");
			
			for(int i = 0; i < Integer.parseInt(div.text().substring(0, 1)); i++) {
				cards.get(rarity).get(clas).add(new Card(nameAttr));
			}
		}
		
		return cards;
	}
}
