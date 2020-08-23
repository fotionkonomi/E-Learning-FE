package com.elearning.fe.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UniversityFeModel extends BaseClassModel {

	@Size(max = 100)
	@NotEmpty
	private String name;

	@Size(max = 65535)
	@NotEmpty
	private String address;

	private List<FacultyFeModel> faculties = new ArrayList<>();
}
