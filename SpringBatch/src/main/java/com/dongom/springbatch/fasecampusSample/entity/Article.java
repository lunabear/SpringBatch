package com.dongom.springbatch.fasecampusSample.entity;

import java.time.LocalDateTime;

public class Article {
	
	
	private long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Article [content=" + content + ", createdAt=" + createdAt + ", id=" + id + ", title=" + title + "]";
	}
	
}
