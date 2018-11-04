package lt.luminor.auth.service.impl;

import lt.luminor.auth.conf.AppProperties;
import lt.luminor.auth.dao.entity.ContactEntity;
import lt.luminor.auth.dao.repository.ContactRepository;
import lt.luminor.auth.service.HttpClientService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HttpClientServiceImpl implements HttpClientService {

    private AppProperties appProperties;
    private ContactRepository contactRepository;
    private RestTemplate restTemplate;

    public HttpClientServiceImpl(AppProperties appProperties, ContactRepository contactRepository, RestTemplate restTemplate) {
        this.appProperties = appProperties;
        this.contactRepository = contactRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void addContact(String oid) throws IOException {
      if(contactRepository.getContactByOid(oid)==null){
          if(isReachable()){
              List<ContactEntity> getContactEntities = getContact(oid);
              getContactEntities.stream().filter(Objects::nonNull).forEach(c-> contactRepository.saveAndFlush(c));
          }
      }
    }

    private boolean isReachable(){
        SocketAddress sockaddr = new InetSocketAddress(appProperties.getHost(), Integer.parseInt(appProperties.getPort()));
        Socket socket = new Socket();
        try {
            socket.connect(sockaddr, 1000);
            return true;
        } catch (SocketTimeoutException stex) {
            return false;
        } catch (IOException iOException) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
               ex.getStackTrace();
            }
        }
    }

    private List<ContactEntity> getContact(String oid) throws IOException {
        ResponseEntity<List<ContactEntity>> entity = restTemplate.exchange(
                appProperties.getUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ContactEntity>>(){}, oid);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }
        return new ArrayList<>();
    }
}
