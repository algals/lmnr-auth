package lt.luminor.auth;

import lt.luminor.auth.conf.AppProperties;
import lt.luminor.auth.service.HttpClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(AppProperties.class)
public class AuthApplicationTests {

	@Autowired
	HttpClientService httpClientService;

	@Test
	public void contextLoads() throws IOException {
		httpClientService.addContact("34773w3444");
	}

}
