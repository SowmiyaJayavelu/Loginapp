package com.assignment.login.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails  implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long userID;
	private String userName;
	private String password;
	private List<GrantedAuthority> grantedAuthrorities;
	
	public CustomUserDetails(String userName, String password, String[] roles) {
		this.userName = userName;
		this.password = password;
		this.grantedAuthrorities = AuthorityUtils.createAuthorityList(roles);
	}


	public Long getUserID() {
		return userID;
	}


	public void setUserID(Long userID) {
		this.userID = userID;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthrorities;	
	}

	@Override
	public String getUsername() {
		return userName;
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

	@Override
	public String getPassword() {
		return password;
	}

}
