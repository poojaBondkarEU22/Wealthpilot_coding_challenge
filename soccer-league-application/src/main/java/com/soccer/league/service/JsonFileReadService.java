package com.soccer.league.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soccer.league.exception.ApplicationException;
import com.soccer.league.model.SeccorSeasonsData;


@Service
public class JsonFileReadService implements FileReadService{
	
	private static final Logger logger = (Logger) LogManager.getLogger(JsonFileReadService.class);

	@Override
	public Object readFileData(String fileName) {
		
		SeccorSeasonsData seccorSeasonsData= null;
		if(null != fileName ) {
			if(new File(fileName).exists()) {
				if( isJsonFile(fileName)) {
					if( isFileNonempty(fileName)) {
						
						ObjectMapper mapper = new ObjectMapper();
						try {
							 
							seccorSeasonsData = mapper.readValue(Paths.get(new File(fileName).getAbsolutePath()).toFile(), 
								 				SeccorSeasonsData.class);
							
						} catch (StreamReadException e) {
							throw new ApplicationException(fileName+" file reading failed ", e);
						} catch (DatabindException e) {
							throw new ApplicationException(fileName+" file reading failed ", e);
						}catch (FileNotFoundException e) {
							throw new ApplicationException("File reading failed ", e);
						}catch (IOException e) {
							throw new ApplicationException(fileName+" file reading failed ", e);
						}catch(Exception e) {
							throw new ApplicationException(fileName+" file reading failed ", e);
						}
						
					}else {
						throw new ApplicationException("File is empty");
					}       
				}else {
					throw new ApplicationException("Invalid input json file");
				}
			}else {
				throw new ApplicationException("File Not Found");
			}
		}else {
			throw new ApplicationException("Filename is missing");
		}
		return seccorSeasonsData;
	}


	@Override
	public boolean isFileNonempty(String fileName) {
		File file = new File(fileName);
		if(file.length()!= 0)
			return true;
		else
			return false;
	}
	
	public boolean isJsonFile(String fileName) {
		if(fileName.contains(".")) {
			if(fileName.split("\\.")[1].equals("json")) {
				return true;
			}
		}else {
			throw new ApplicationException("Invalid File Name");
		}
		return false;
	}
	
	public boolean isValidJsonData(String fileName)  {
	     try {
			new JSONObject(new String(Files.readAllBytes(Paths.get(fileName))));
		} catch (Exception e) {
			throw new JSONException("Invalid Json");
		} 
	  
		return true;
	}

}
