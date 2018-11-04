package lt.luminor.auth.service.impl;

import lt.luminor.auth.dao.entity.ContactEntity;
import lt.luminor.auth.dao.repository.ContactRepository;
import lt.luminor.auth.service.ContactService;
import lt.luminor.auth.service.HttpClientService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;
    private HttpClientService httpClientService;

    public ContactServiceImpl(ContactRepository contactRepository, HttpClientService httpClientService) {
        this.contactRepository = contactRepository;
        this.httpClientService = httpClientService;
    }

    @Override
    public List<ContactEntity> getContactsList(String oid) throws IOException {
        httpClientService.addContact(oid);
        return Arrays.asList(contactRepository.getContactByOid(oid)).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
