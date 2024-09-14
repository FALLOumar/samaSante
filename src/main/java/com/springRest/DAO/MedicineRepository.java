package com.springRest.DAO;

import com.springRest.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicineRepository extends JpaRepository<Medicine, Integer>
{

}