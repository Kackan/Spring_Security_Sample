package com.myapp.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myapp.entity.Role;
import com.myapp.entity.User;
import com.myapp.repository.RoleRepository;
import com.myapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user =userRepo.findByUsername(username);
		
		
		if(user==null)
		{
			throw new UsernameNotFoundException("ERROR IN USER SERVICE");
		}
		
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(),
				user.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList()));
		
	}

	@Override
	@Transactional
	public User findByUsername(String name) {
		return userRepo.findByUsername(name);
	}

	@Override
	@Transactional
	public void save(User user) 
	{
		User protectedUser=new User();
		Set<Role>roles=new HashSet<>();
		roles.add(roleRepo.findByRoleName("ROLE_EMPLOYEE"));
		protectedUser.setUsername(user.getUsername());
		protectedUser.setPassword(passwordEncoder.encode(user.getPassword()));
		protectedUser.setRoles(roles);
		userRepo.save(protectedUser);
	}
}
