package com.springRest.DAO;

import com.springRest.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiseaseRepository extends JpaRepository<Disease, Integer>
{

}
