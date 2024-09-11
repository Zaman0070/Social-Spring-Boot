package com.social_app.social;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.social_app.social.configs.AppConstant;
import com.social_app.social.models.RoleModel;
import com.social_app.social.repository.RoleRepo;

import jakarta.annotation.PostConstruct;
@SpringBootApplication
public class SocialApplication {

	@Autowired
	private RoleRepo roleRepo;

	public static void main(final String[] args) {
		SpringApplication.run(SocialApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@PostConstruct
	public void initRole() {
		try{
			final RoleModel role = new RoleModel();
			role.setId(AppConstant.USER_ROLE_ID);
			role.setName("ROLE_USER");

			final RoleModel role2 = new RoleModel();
			role2.setId(AppConstant.ADMIN_ROLE_ID);
			role2.setName("ROLE_ADMIN");

		final List<RoleModel> roles =	List.of(role, role2);
		final List<RoleModel> result = roleRepo.saveAll(roles);
		result.forEach(System.out::println);
		}catch (final Exception e){
			e.printStackTrace();
		}
	}

	

}
