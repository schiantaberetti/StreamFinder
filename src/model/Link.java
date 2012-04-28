package model;

import java.util.ArrayList;

public class Link {
	private String name;
	private String url;
	
	
	public Link(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isIn(ArrayList<Link> links){
		for (Link l : links){
			if(l.getUrl().equalsIgnoreCase(this.url))
				return true;
		}
		return false;
	}
}
