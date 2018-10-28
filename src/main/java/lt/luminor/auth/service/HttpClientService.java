package lt.luminor.auth.service;

import lt.luminor.auth.domain.Contact;

import java.io.IOException;
import java.util.List;

public interface HttpClientService {
    List<Contact> getContacts(String oid) throws IOException;
}
