package org.sid.springsecurity;

import org.sid.springsecurity.entity.AppRole;
import org.sid.springsecurity.entity.AppUser;
import org.sid.springsecurity.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

//	@Bean
	CommandLineRunner initUserInMemory(AccountService accountService) {
		return args -> {
			accountService.addNewRole(AppRole.builder().roleName("USER").build());
			accountService.addNewRole(AppRole.builder().roleName("ADMIN").build());
			accountService.addNewRole(AppRole.builder().roleName("CUSTOMER_MANAGER").build());
			accountService.addNewRole(AppRole.builder().roleName("PRODUCT_MANAGER").build());
			accountService.addNewRole(AppRole.builder().roleName("BILLS_MANAGER").build());

			accountService.addNewUser(AppUser.builder().username("user1").password("1234").build());
			accountService.addNewUser(AppUser.builder().username("admin").password("admin").build());
			accountService.addNewUser(AppUser.builder().username("user2").password("1234").build());
			accountService.addNewUser(AppUser.builder().username("user3").password("1234").build());
			accountService.addNewUser(AppUser.builder().username("user4").password("1234").build());

			accountService.addRoleToUser("user1", "USER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN");
			accountService.addRoleToUser("user2", "USER");
			accountService.addRoleToUser("user2", "CUSTOMER_MANAGER");
			accountService.addRoleToUser("user3", "USER");
			accountService.addRoleToUser("user4", "USER");
			accountService.addRoleToUser("user2", "BILLS_MANAGER");
		};
	}

	@Bean
	CommandLineRunner initUserInMysql(JdbcUserDetailsManager jdbcUserDetailsManager) {
		PasswordEncoder passwordEncoder = passwordEncoder();
		return args -> {
			if (!jdbcUserDetailsManager.userExists("user11")) {
				jdbcUserDetailsManager.createUser(User
						.withUsername("user11")
						.password(passwordEncoder.encode("1234"))
						.roles("USER")
						.build());
				jdbcUserDetailsManager.createUser(User
						.withUsername("user22")
						.password(passwordEncoder.encode("1234"))
						.roles("USER")
						.build()
				);
				jdbcUserDetailsManager.createUser(User
						.withUsername("admin2")
						.password(passwordEncoder.encode("1234"))
						.roles("USER", "ADMIN")
						.build()
				);
			}
		};
	}

	/**
	 * Create bean PasswordEncoder => Spring security encode password automatically
	 * @return PasswordEncoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
