package com.soccer.league.service;

public interface FileReadService {
	
	public Object readFileData(String fileName);
	
	public boolean isFileNonempty(String fileName);

}
