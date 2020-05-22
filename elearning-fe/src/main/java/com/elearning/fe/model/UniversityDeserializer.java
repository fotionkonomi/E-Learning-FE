package com.elearning.fe.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.LongNode;

public class UniversityDeserializer extends StdDeserializer<UniversityFeModel> {

	private static final long serialVersionUID = -5418122836839768933L;

	public UniversityDeserializer() {
		this(null);
	}

	protected UniversityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public UniversityFeModel deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		UniversityFeModel university = new UniversityFeModel();
		Long id = node.get("id").asLong();
		university.setId(id);
		university.setTimestamp(new Date(node.get("timestamp").asLong()));
		university.setName(node.get("name").asText());
		university.setAddress(node.get("address").asText());
		Iterator<JsonNode> facultyNodes = node.get("faculties").elements();
		List<FacultyFeModel> faculties = new ArrayList<>();
		while (facultyNodes.hasNext()) {
			JsonNode facultyNode = facultyNodes.next();
			FacultyFeModel faculty = new FacultyFeModel();
			faculty.setId(facultyNode.get("id").asLong());
			faculty.setTimestamp(new Date(facultyNode.get("timestamp").asLong()));
			faculty.setName(facultyNode.get("name").asText());
			faculties.add(faculty);
		}
		university.setFaculties(faculties);
		return university;

	}

}