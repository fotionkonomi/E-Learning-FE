package com.elearning.fe.model;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FacultyFeModel extends BaseClassModel {

	@Size(max = 100)
	@NotEmpty
	private String name;
	
	@NotNull
	private UniversityFeModel university;
	
	private List<BranchFeModel> branches = new ArrayList<>();

	
}
