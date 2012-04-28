package model;

import java.util.ArrayList;

public class LinkCollector {
	String name;
	ArrayList<Link> links = new ArrayList<Link>();

	
	public LinkCollector(String name) {
		super();
		this.name = name;
	}

	public String getName(){
		return name;
	}
	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}
	

}
