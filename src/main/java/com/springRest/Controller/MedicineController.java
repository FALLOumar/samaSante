package com.springRest.Controller;

import com.springRest.entity.Medicine;
import com.springRest.entity.Patient;
import com.springRest.service.MedicineService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/medicines")
public class MedicineController
{
    private MedicineService medicineService;
    private List<Medicine> themedicines;


    public MedicineController(MedicineService medicineService)
    {
        //this.medicineRepository = medicineRepository;
        this.medicineService = medicineService;
    }

    @GetMapping("/list")
    public String listmedicines(Model theModel)
    {


        themedicines = medicineService.getAllmedicines();
        //medicineRepository.findAll(PageRequest.of(page, size))
        theModel.addAttribute("medicines", themedicines);
        return "medicine/list-medicines";
    }

    @GetMapping("/addMedicine")
    public String getmedicineForm(Model model)
    {
        Medicine themedicine = new Medicine();
        model.addAttribute("medicine",themedicine);
        return "medicine/addMedicine";
    }

    @PostMapping("/save")
    public String savemedicine(@ModelAttribute("medicine") Medicine themedicine)
    {
        medicineService.save(themedicine);
        return "redirect:/medicines/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("medicineId") int theID,Model model)
    {
        Medicine medicine = medicineService.findById(theID);
        model.addAttribute("medicine",medicine);
        return "medicine/addMedicine";
    }




}









