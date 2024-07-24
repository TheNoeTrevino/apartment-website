package backend;


import backend.models.Apartment;
import backend.models.ApartmentComplex;
import backend.models.Lease;
import backend.models.MaintenanceRequest;
import backend.models.Tenant;
import backend.models.TenantStatus;
import backend.repositories.ApartmentComplexRepository;
import backend.repositories.ApartmentRepository;
import backend.repositories.LeaseRepository;
import backend.repositories.MaintenanceRequestRepository;
import backend.repositories.TenantRepository;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TestDataGenerator {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentComplexRepository apartmentComplexRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private MaintenanceRequestRepository maintenanceRequestRepository;

    private final Faker faker = new Faker();

    @PostConstruct
    @Transactional
    public void generateTestData() {
    Pattern phonePattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}");

        List<ApartmentComplex> complexes = IntStream.range(0, 5).mapToObj(i -> new ApartmentComplex()
                .setComplexName(faker.company().name())
                .setComplexLocation(faker.address().fullAddress())
                .setNumOfBuildings(faker.number().numberBetween(1, 5))
                .setNumOfUnits(faker.number().numberBetween(1, 50))
                .setDateBuilt(faker.date().past(9000, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .setManagerName(faker.name().fullName())
                .setManagerEmail(faker.internet().emailAddress())
                .setManagerPhone(generateValidPhoneNumber(phonePattern))
        ).collect(Collectors.toList());

        apartmentComplexRepository.saveAll(complexes);

        // generate some apartments
        complexes.forEach(complex -> {
            List<Apartment> apartments = IntStream.range(0, complex.getNumOfUnits()).mapToObj(i -> new Apartment()
                    .setApartmentName(faker.lorem().characters(10, 255))
                    .setApartmentLocation(faker.address().fullAddress())
                    .setNumOfRooms(faker.number().numberBetween(1, 5))
                    .setSquareFootage(faker.number().numberBetween(300, 1500))
                    .setDateBuilt(faker.date().past(6000, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .setApartmentComplex(complex)
            ).collect(Collectors.toList());

            apartmentRepository.saveAll(apartments);

            // generate apartments
            apartments.forEach(apartment -> {
                Tenant tenant = new Tenant()
                        .setFirstName(faker.name().firstName())
                        .setLastName(faker.name().lastName())
                        .setDateOfBirth(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .setEmail(faker.internet().emailAddress())
                        .setPhoneNumber(generateValidPhoneNumber(phonePattern))
                        .setSocialSecurityNumber(faker.idNumber().ssnValid())
                        .setDriversLicenseNumber(faker.idNumber().valid())
                        .setCurrentAddress(faker.address().fullAddress())
                        .setPreviousAddress(faker.address().fullAddress())
                        .setEmployerName(faker.company().name())
                        .setJobTitle(faker.job().title())
                        .setAnnualIncome(faker.number().numberBetween(30000, 100000))
                        .setEmergencyContactName(faker.name().fullName())
                        .setEmergencyContactRelationship("Relative")
                        .setEmergencyContactPhone(generateValidPhoneNumber(phonePattern))
                        .setReferenceName(faker.name().fullName())
                        .setReferenceRelationship("Friend")
                        .setReferencePhone(generateValidPhoneNumber(phonePattern))
                        .setTenantStatus(TenantStatus.ACTIVE)
                        .setApartment(apartment);
                        System.out.println(tenant.getEmergencyContactPhone());

                tenantRepository.save(tenant);

                Lease lease = new Lease()
                        .setLeaseStartDate(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .setLeaseEndDate(faker.date().future(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                        .setRentAmount(faker.number().randomDouble(2, 500, 2000))
                        .setSecurityDepositAmount(faker.number().randomDouble(2, 500, 2000))
                        .setTenant(tenant)
                        .setApartment(apartment);

                leaseRepository.save(lease);

                MaintenanceRequest request = new MaintenanceRequest()
                        .setDescription(faker.lorem().sentence())
                        .setRequestDate(LocalDateTime.now())
                        .setTenant(tenant)
                        .setType("General Maintenance");
                    maintenanceRequestRepository.save(request);
            });
        });
  }

  private String generateValidPhoneNumber(Pattern pattern) {
    String phoneNumber;
      do {
        phoneNumber = faker.phoneNumber().phoneNumber();
      } while (!pattern.matcher(phoneNumber).matches());
  return phoneNumber;
  }
}
