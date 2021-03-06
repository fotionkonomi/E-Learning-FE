package com.elearning.fe.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleFeModel extends BaseClassModel {

	@Size(max = 50)
	@NotEmpty
	private String name;
	
	@Size(max = 65535)
	@NotEmpty
	private String description;
	
	private List<UserFeModel> users = new ArrayList<>();
}
