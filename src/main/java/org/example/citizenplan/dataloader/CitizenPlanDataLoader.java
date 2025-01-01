package org.example.citizenplan.dataloader;

import org.example.citizenplan.model.CitizenPlan;
import org.example.citizenplan.repo.CitizenPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Component
public class CitizenPlanDataLoader implements ApplicationRunner {

    @Autowired
    private CitizenPlanRepo citizenPlanRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        citizenPlanRepo.deleteAll();

        CitizenPlan citizen1 = new CitizenPlan();
        citizen1.setCitizenName("John");
        citizen1.setGender("Male");
        citizen1.setPlanName("Cash");
        citizen1.setPlanStatus("Approved");
        citizen1.setPlanStartDate(LocalDate.now());
        citizen1.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen1.setBenefitAmt(5000.00);


        CitizenPlan citizen2 = new CitizenPlan();
        citizen2.setCitizenName("Smith");
        citizen2.setGender("Male");
        citizen2.setPlanName("Cash");
        citizen2.setPlanStatus("Denied");
        citizen2.setPlanStartDate(LocalDate.now());
        citizen2.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen2.setBenefitAmt(5000.00);
        citizen2.setDenialReason("Rental Income");


        CitizenPlan citizen3 = new CitizenPlan();
        citizen3.setCitizenName("Cathy");
        citizen3.setGender("Female");
        citizen3.setPlanName("Cash");
        citizen3.setPlanStatus("Terminated");
        citizen3.setPlanStartDate(LocalDate.now());
        citizen3.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen3.setBenefitAmt(5000.00);
        citizen3.setTerminationDate(LocalDate.now());
        citizen3.setTerminationReason("Employed");


//        Food Data

        CitizenPlan citizen4 = new CitizenPlan();
        citizen4.setCitizenName("David");
        citizen4.setGender("Male");
        citizen4.setPlanName("Food");
        citizen4.setPlanStatus("Approved");
        citizen4.setPlanStartDate(LocalDate.now());
        citizen4.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen4.setBenefitAmt(4000.00);

        CitizenPlan citizen5 = new CitizenPlan();
        citizen5.setCitizenName("Robert");
        citizen5.setGender("Male");
        citizen5.setPlanName("Food");
        citizen5.setPlanStatus("Denied");
        citizen5.setDenialReason("Property Income");

        CitizenPlan citizen6 = new CitizenPlan();
        citizen6.setCitizenName("Pooja");
        citizen6.setGender("Female");
        citizen6.setPlanName("Food");
        citizen6.setPlanStatus("Terminated");
        citizen6.setPlanStartDate(LocalDate.now().minusMonths(4));
        citizen6.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen6.setBenefitAmt(5000.00);
        citizen6.setTerminationDate(LocalDate.now());
        citizen6.setTerminationReason("Employed");


//        Medical Data

        CitizenPlan citizen7 = new CitizenPlan();
        citizen7.setCitizenName("Ronny");
        citizen7.setGender("Male");
        citizen7.setPlanName("Medical");
        citizen7.setPlanStatus("Denied");
        citizen7.setDenialReason("Property Income");


        CitizenPlan citizen8 = new CitizenPlan();
        citizen8.setCitizenName("Charles");
        citizen8.setGender("Male");
        citizen8.setPlanName("Medical");
        citizen8.setPlanStatus("Approved");
        citizen8.setPlanStartDate(LocalDate.now());
        citizen8.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen8.setBenefitAmt(4000.00);

        CitizenPlan citizen9 = new CitizenPlan();
        citizen9.setCitizenName("Neela");
        citizen9.setGender("Female");
        citizen9.setPlanName("Medical");
        citizen9.setPlanStatus("Terminated");
        citizen9.setPlanStartDate(LocalDate.now());
        citizen9.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen9.setBenefitAmt(5000.00);
        citizen9.setTerminationDate(LocalDate.now());
        citizen9.setTerminationReason("Employed");


//        Employment Plan Data
        CitizenPlan citizen10 = new CitizenPlan();
        citizen10.setCitizenName("Robin");
        citizen10.setGender("Male");
        citizen10.setPlanName("Employment");
        citizen10.setPlanStatus("Denied");
        citizen10.setDenialReason("Property Income");


        CitizenPlan citizen11 = new CitizenPlan();
        citizen11.setCitizenName("Tom");
        citizen11.setGender("Male");
        citizen11.setPlanName("Employment");
        citizen11.setPlanStatus("Approved");
        citizen11.setPlanStartDate(LocalDate.now());
        citizen11.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen11.setBenefitAmt(4000.00);

        CitizenPlan citizen12 = new CitizenPlan();
        citizen12.setCitizenName("Rosy");
        citizen12.setGender("Female");
        citizen12.setPlanName("Employment");
        citizen12.setPlanStatus("Terminated");
        citizen12.setPlanStartDate(LocalDate.now());
        citizen12.setPlanEndDate(LocalDate.now().plusMonths(6));
        citizen12.setBenefitAmt(5000.00);
        citizen12.setTerminationDate(LocalDate.now());
        citizen12.setTerminationReason("Govt Job");

        List<CitizenPlan> list = Arrays.asList(citizen1, citizen2, citizen3, citizen4, citizen5, citizen6, citizen7, citizen8
                , citizen9, citizen10, citizen11, citizen12);

        citizenPlanRepo.saveAll(list);
        List<String> planName = citizenPlanRepo.getPlanName();
      planName.forEach(e->System.out.println(e));

        List<String> planStatus = citizenPlanRepo.getPlanStatus();
        planStatus.forEach(e->System.out.println(e));

    }
}
