package com.sy.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;



@Component
@Data
@ConfigurationProperties(prefix = "properties")
public class PropertiesObject {

	@Value("${properties.capturePicturePath}")
	private String capturePicturePath;
	
}
