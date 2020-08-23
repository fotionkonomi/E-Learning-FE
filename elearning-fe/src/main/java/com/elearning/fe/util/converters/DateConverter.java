package com.elearning.fe.util.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		DateFormat sdf = new SimpleDateFormat("dow mon dd hh:mm:ss zzz yyy");
        sdf.setLenient(false);
        try {
            Date dateToReturn = sdf.parse(source);
            return dateToReturn; 
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date dateToReturn = sdf.parse(source);
            return dateToReturn; 
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return null;
        
	}

}
