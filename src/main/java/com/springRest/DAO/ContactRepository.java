package com.springRest.DAO;

import com.springRest.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer>
{
}
