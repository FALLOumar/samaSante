package com.springRest.Controller;

import com.springRest.service.MedicalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medical-data")
public class MedicalDataController {

    @Autowired
    private MedicalDataService medicalDataService;

    @GetMapping("/load")
    public List<Map<String, String>> loadMedicalData() {
        return medicalDataService.loadMedicalData();
    }

    @GetMapping("/analyze")
    public String analyzeData() {
        medicalDataService.analyzeData();
        return "Data analysis complete.";
    }
}
