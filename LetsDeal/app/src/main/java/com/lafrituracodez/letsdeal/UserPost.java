package com.lafrituracodez.letsdeal;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserPost {
	private String uid;
	private String post_id;

	public UserPost() {

	}

	UserPost(String uid, String post_id) {
		this.uid = uid;
		this.post_id = post_id;

	}

	@Exclude
	public Map<String, Object> toMap() {
		HashMap<String, Object> result = new HashMap<>();
		result.put("uid", uid);
		result.put("post_id", post_id);

		return result;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
}
