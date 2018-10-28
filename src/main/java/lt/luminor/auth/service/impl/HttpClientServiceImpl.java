package lt.luminor.auth.service.impl;

import lt.luminor.auth.conf.AppProperties;
import lt.luminor.auth.domain.Contact;
import lt.luminor.auth.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class HttpClientServiceImpl implements HttpClientService {

    @Autowired
    AppProperties appProperties;

    @Override
    public List<Contact> getContacts(String oid) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        return (List<Contact>)restTemplate.getForObject(appProperties.getUrl(), Object.class, oid);
    }
}
