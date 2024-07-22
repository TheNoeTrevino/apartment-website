// package com.respec.training.initalization;

// import java.time.LocalDate;
// import java.time.ZoneId;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Date;
// import java.util.List;
// import java.util.Random;
// import java.util.concurrent.TimeUnit;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.beans.factory.annotation.Autowired;


// import com.github.javafaker.Faker;
// import com.respec.training.DTO.CreateUpdateDrumCorpsDTO;
// import com.respec.training.mappers.DrumCorpsMapper;
// import com.respec.training.models.DrumCorps;
// import com.respec.training.repositories.DrumCorpsRepository;


// @Configuration
// public class InitDatabase {

//     @Autowired
//     private DrumCorpsMapper mapper;

//     @Autowired
//     private DrumCorpsRepository repo;

//     @Bean
//     public InitDatabase addSampleData() {
        
//         Faker faker = new Faker();
//         Random random = new Random();

//         int twoHundredYears = (200 * 365);
//         int fiftyYears = (50 * 365);
        
//         // Custom library for "Boston Crusaders" format
//         List<String> descriptionLibrary = Arrays.asList(
//             "Troopers", "Guards", "Brigade", "Legion", "Battalion",
//             "Rangers", "Sentinels", "Regiment", "Vanguard", "Cavaliers",
//             "Stars", "Knights", "Phantoms", "Guardians", "Pioneers", "Crusaders"
//         );

//         List<DrumCorps> sampleDrumCorpsList = new ArrayList<DrumCorps>();

//         for (int j = 0; j < 1000; j++) {
//             for (int i = 0; i < 1000; i++) {
//                 // Generating name
//                 String city = faker.address().city();
//                 String description = descriptionLibrary.get(random.nextInt(descriptionLibrary.size()));
                
//                 // Keep date founded between 200 years & 50 years ago
//                 Date fakeDateFoundedUnformatted = faker.date().past(twoHundredYears, fiftyYears, TimeUnit.DAYS);
//                 LocalDate fakeDateFoundedFormmatted = fakeDateFoundedUnformatted.toInstant()
//                                                         .atZone(ZoneId.systemDefault()).toLocalDate();
                
//                 String fakeCorpsName = city + " " + description;
//                 int fakeNumOfChamp = random.nextInt(20);
//                 LocalDate fakeDateFounded = fakeDateFoundedFormmatted;
//                 Boolean fakeFolded = faker.bool().bool();
//                 LocalDate fakeDateFolded = null;
                
//                 if (fakeFolded) {
//                     // Keep date folded < 50 years ago
//                     Date fakeDateFoldedUnformatted = faker.date().past(fiftyYears, TimeUnit.DAYS, fakeDateFoundedUnformatted);
//                     fakeDateFolded = fakeDateFoldedUnformatted.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                 }

//                 CreateUpdateDrumCorpsDTO sampleDrumCorpsDTO = new CreateUpdateDrumCorpsDTO(fakeCorpsName, fakeNumOfChamp,
//                                                                 fakeDateFounded, fakeFolded, fakeDateFolded);
                       
//                 DrumCorps sampleDrumCorps = mapper.createUpdateDrumCorpsDTOToDrumCorps(sampleDrumCorpsDTO);
//                 sampleDrumCorpsList.add(sampleDrumCorps);

//                 if (i == 999) {
//                     repo.saveAll(sampleDrumCorpsList);
//                     sampleDrumCorpsList.clear();
//                 }
                
//             }
//         }
//         return null;
//     }
// }