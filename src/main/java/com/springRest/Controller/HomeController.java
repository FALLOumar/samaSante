package com.springRest.Controller;

import com.springRest.entity.Contact;
import com.springRest.service.ContactService;
import com.springRest.service.RoleService;
import com.springRest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Home")
public class HomeController
{
    private ContactService contactService;
    private UserService userService;
    private RoleService roleService;

    public HomeController(RoleService roleService, ContactService contactService, UserService userService)
    {
        this.contactService = contactService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getHomePage(Model model)
    {
        return "Home/home";
    }
    @GetMapping("/home-page")
    public String sayHello(Model model)
    {
        model.addAttribute("date",new java.util.Date());
        return "Home/home";
    }





    @GetMapping("/contact-US")
    public String getContactPage(Model model)
    {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contact-US/contact-US";
    }

    @PostMapping("/save-contact")
    public String reciveContact(@ModelAttribute("contact") Contact contact)
    {
        try
        {
            contactService.save(contact);
            return "redirect:/Home/home-page";
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/Home/home-page";
    }
}