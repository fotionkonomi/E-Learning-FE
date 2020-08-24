package com.elearning.fe.util.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        sdf.setLenient(false);
        try {
            Date dateToReturn = sdf.parse(source);
            return dateToReturn; 
        } catch (ParseException e) {
            log.error("The date format isn't EEE MMM dd HH:mm:ss zzz yyyy ");
        }
       
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date dateToReturn = sdf.parse(source);
            return dateToReturn; 
        } catch (ParseException e) {
            log.error("Date format isn't dd/MM/yyyy");
        }
        log.info("Returning null for the date");
        return null;
        
	}

}
