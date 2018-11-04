package lt.luminor.auth.service;

import lt.luminor.auth.dao.entity.ContactEntity;

import java.io.IOException;
import java.util.List;

public interface ContactService {
    List<ContactEntity> getContactsList(String oid) throws IOException;
}
