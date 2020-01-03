package com.example.demo.common.vo;

public class Message {

	private int application;
	private String userId;
	private String timestamp;
	private String digest;

	public int getApplication() {
		return application;
	}

	public void setApplication(int application) {
		this.application = application;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

}
