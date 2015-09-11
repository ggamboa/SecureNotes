package com.ggg.sn2.model;

import java.util.ArrayList;
import java.util.List;

import com.ggg.sn2.ui.console.Constants;

public class Note {
	
	private String title;
	private String body;

	public Note(String title) {
		this.title = title;
		body = "";
	}
	
	public Note(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public Note(String title, List<String> lines) {
		this.title = title;
		this.body = buildContent(lines);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(divider('*', 80));
		sb.append("\n");
		sb.append(this.title);
		sb.append("\n");
		sb.append(divider('-', this.title.length()));
		sb.append("\n");
		sb.append(this.body);
		if(!(this.body.charAt(this.body.length()-1) == '\n' || this.body.charAt(this.body.length()-1) == '\r')) {
			sb.append("\n");
		}
		sb.append(divider('*', 80));
		sb.append("\n");
		return sb.toString();
	}
	
	private String buildContent(List<String> lines) {
		StringBuilder sb = new StringBuilder();
		for(String line : lines) {
			sb.append(line);
			sb.append("\r\n");
		}
		return sb.toString();
	}
	
	private String divider(char c, int length) {
		char[] cbuf = new char[length];
		for (int i=0; i < length; i++) {
			cbuf[i] = c;
		}
		return new String(cbuf);
	}
	
	
}
