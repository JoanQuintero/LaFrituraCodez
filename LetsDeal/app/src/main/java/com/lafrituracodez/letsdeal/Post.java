package com.lafrituracodez.letsdeal;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Post {

	private String uid;
	private String author;
	private String title;
	private String desc;
	private int resource;

	private double price;
	private int starCount = 0;
	private Map<String, Boolean> stars = new HashMap<>();

	public Post() {

	}

	Post(String uid, String author, String title, String desc, double price) {
		this.uid = uid;
		this.author = author;
		this.title = title;
		this.desc = desc;
		this.price = price;
	}

	Post(String uid, String author, String title, String desc, double price, int resource) {
		this.uid = uid;
		this.author = author;
		this.title = title;
		this.desc = desc;
		this.price = price;
		this.resource = resource;
	}

	@Exclude
	public Map<String, Object> toMap() {
		HashMap<String, Object> result = new HashMap<>();
		result.put("uid", uid);
		result.put("author", author);
		result.put("title", title);
		result.put("desc", desc);
		result.put("price", price);
		result.put("starCount", starCount);
		result.put("stars", stars);

		return result;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStarCount() {
		return starCount;
	}

	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}

	public Map<String, Boolean> getStars() {
		return stars;
	}

	public void setStars(Map<String, Boolean> stars) {
		this.stars = stars;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}
}
