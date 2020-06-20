package com.elearning.fe.model;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FacultyDeserializer extends StdDeserializer<FacultyFeModel> {

	private static final long serialVersionUID = 7304453464382541624L;

	public FacultyDeserializer() {
		this(null);
	}
	
	protected FacultyDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public FacultyFeModel deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException {
		JsonNode node = jp.getCodec().readTree(jp);
		FacultyFeModel faculty = new FacultyFeModel();
		Long id = node.get("id").asLong();
		faculty.setId(id);
		faculty.setTimestamp(new Date(node.get("timestamp").asLong()));
		faculty.setName(node.get("name").asText());
		Iterator<JsonNode> universityNodes = node.get("university").elements();
		UniversityFeModel universityFeModel = new UniversityFeModel();
		while(universityNodes.hasNext()) {
			JsonNode universityNode = universityNodes.next();
			JsonNode universityId = universityNode.get("id");
			if(universityId != null) {
				universityFeModel.setId(universityId.asLong());
			}
			
			JsonNode universityTimestamp = universityNode.get("timestamp");
			if(universityTimestamp != null) {
				universityFeModel.setTimestamp(new Date(universityTimestamp.asLong()));
			}
			JsonNode universityName = universityNode.get("name");
			if(universityName != null) {
				universityFeModel.setName(universityName.asText());
			}
			JsonNode universityAddress = universityNode.get("address");
			if(universityAddress != null) {
				universityFeModel.setAddress(universityAddress.asText());
			}
		}
		return faculty;
	}
	
}
