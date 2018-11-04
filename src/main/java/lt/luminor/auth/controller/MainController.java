package lt.luminor.auth.controller;

import lt.luminor.auth.conf.AppProperties;
import lt.luminor.auth.conf.AuthenticatedUser;
import lt.luminor.auth.service.ContactService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class MainController {

    private ContactService contactService;

    public MainController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/")
    public String getContacts(Model model, Authentication authentication) throws IOException {
        AuthenticatedUser user = AuthenticatedUser.get(authentication);
        model.addAttribute("name", user.getName());
        model.addAttribute("unique_name", user.getUniqueName());
        model.addAttribute("contacts", contactService.getContactsList(user.getOid()));
        return "user";
    }
}
