package bingo.link.uam.sync.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import link.uam.sync.service.OrganizationSyncService;
import link.uam.sync.service.UserSyncService;

@Configuration
public class DemoConfiguration {
	@Value("${clientId}")
	private String clientId;
	
	@Value("${clientSecret}")
	private String clientSecret;
	
	@Value("${ssoServer}")
	private String ssoServer;
	
	@Value("${uamServer}")
	private String uamServer;

	@Bean
	public UserSyncService uamSyncService() {
		return new UserSyncService(clientId, clientSecret, ssoServer, uamServer);
	}
	
	@Bean
	public OrganizationSyncService orgSyncService() {
		return new OrganizationSyncService(clientId, clientSecret, ssoServer, uamServer);
	}
}
