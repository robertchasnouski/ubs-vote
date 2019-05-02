package com.social.entities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="User")
@Scope("session")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public  class User implements UserDetails{
	public static enum Role{ USER }

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name =  "USER_ID")
	private Long id ;

	@Column(unique = true)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password ;

    private String role;
    private String fullName;

	@OneToMany(mappedBy = "user",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("userPolls")
	private Set<UserPoll> userPolls;

	public Set<UserPoll> getUserPolls() {
		return userPolls;
	}

	public void setUserPolls(Set<UserPoll> polls) {
		this.userPolls = polls;
	}

	public void addUserPoll(UserPoll userPoll) {
		this.userPolls.add(userPoll);
	}

	public User(){
    	
    }
    
    public User(String username,String password,String fullName){
    	this.username=username;
    	this.password= password;
    	this.fullName=fullName;
    }
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role +
				 ",]";
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
