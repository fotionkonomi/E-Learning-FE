package com.elearning.fe.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.elearning.fe.common.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserFeModel extends BaseClassModel implements UserDetails {

	private static final long serialVersionUID = -4829622563554311339L;

	@Size(max = 50)
	@NotEmpty
	private String firstName;

	@Size(max = 50)
	@NotEmpty
	private String lastName;

	@Size(max = 50)
	@NotEmpty
	private String username;

	@Size(max = 255)
	@NotEmpty
	@Size(min = 8, max = 16)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
	private String password;

	@NotNull
	private GenderEnum gender;

	@Size(max = 100)
	@NotEmpty
	@Email
	private String email;

	@NotNull
	private Date dateOfBirth;

	private Date dateRegistered;

	private BranchFeModel branch;

	private RoleFeModel role;

	private Set<CourseFeModel> myStudentCourses = new HashSet<>();

	private List<CourseFeModel> myProfessorCourses = new ArrayList<>();

	private Set<AnswerFeModel> myAnswers = new HashSet<>();
	
	private String token;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		RoleFeModel roleObject = this.getRole();
		String role = null;
		if(roleObject != null) {
			role = roleObject.getName();
		}
		if ("admin".equalsIgnoreCase(role)) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if ("student".equalsIgnoreCase(role)) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		} else if("professor".equalsIgnoreCase(role)) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_PROFFESOR"));
		}

		return grantedAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
