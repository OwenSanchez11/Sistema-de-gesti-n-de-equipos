package gestionDeEquiposDeMantenimiento.firstVersion.Service;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentStatus;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.BusinessRuleException;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceService;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceStatus;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaintenanceServiceTest {

    @Mock
    private MaintenanceRepository maintenanceRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MaintenanceService maintenanceService;

    @Test
    void shouldSaveMaintenanceSuccesfuly() {
        MaintenanceCreateDTO request = TestDataFactory.validMaintenanceRquest();

        EquipmentModel equipment = TestDataFactory.availableEquipment();

        UserModel user = TestDataFactory.activeUser(request.getIdUser());

        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUser()))
                .thenReturn(Optional.of(user));

        when(maintenanceRepository.save(any(MaintenanceModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        MaintenanceResponseDTO response = maintenanceService.saveMaintenance(request);

        assertNotNull(response);
        assertEquals(
                EquipmentStatus.MAINTENANCE,
                equipment.getStatus()
        );

        verify(maintenanceRepository).save(any(MaintenanceModel.class));
    }


    @Test
    void shouldThrowExceptionWhenEquipmentNotFound() {
        MaintenanceCreateDTO request = TestDataFactory.validMaintenanceRquest();
        EquipmentModel equipment = TestDataFactory.availableEquipment();
        UserModel user = TestDataFactory.activeUser(request.getIdUser());

        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> maintenanceService.saveMaintenance(request));
        assertEquals("Equipment not found", exception.getMessage());

        verify(maintenanceRepository, never()).save(any(MaintenanceModel.class));

    }


    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        MaintenanceCreateDTO request = TestDataFactory.validMaintenanceRquest();

        EquipmentModel equipment = TestDataFactory.availableEquipment();

        UserModel user = TestDataFactory.activeUser(request.getIdUser());

        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUser()))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,() -> maintenanceService.saveMaintenance(request));
        assertEquals("User not found", exception.getMessage());
        verify(maintenanceRepository, never()).save(any(MaintenanceModel.class));


    }


    @Test
    void shouldThrowExceptionWhenEquipmentIsNotAvailable() {
        MaintenanceCreateDTO request = TestDataFactory.validMaintenanceRquest();

        EquipmentModel equipment = TestDataFactory.maintenanceEquipment();

        UserModel user = TestDataFactory.activeUser(request.getIdUser());

        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUser()))
                .thenReturn(Optional.of(user));


        BusinessRuleException exception = assertThrows(BusinessRuleException.class,() -> maintenanceService.saveMaintenance(request));
        assertEquals("The equipment is not available", exception.getMessage());
        verify(maintenanceRepository, never()).save(any(MaintenanceModel.class));

    }



    @Test
    void shouldThrowExceptionWhenUserIsInactive() {
        MaintenanceCreateDTO request = TestDataFactory.validMaintenanceRquest();

        EquipmentModel equipment = TestDataFactory.availableEquipment();

        UserModel user = TestDataFactory.InactiveUser(request.getIdUser());

        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUser()))
                .thenReturn(Optional.of(user));


        BusinessRuleException exception = assertThrows(BusinessRuleException.class,() -> maintenanceService.saveMaintenance(request));
        assertEquals("User is disconnect", exception.getMessage());
        verify(maintenanceRepository, never()).save(any(MaintenanceModel.class));

    }

    @Test
    void shouldCompleteMaintenanceSuccesfully() {


        MaintenanceModel maintenance = TestDataFactory.mainteanance();


        when(maintenanceRepository.findById(maintenance.getIdMaintenance()))
                .thenReturn(Optional.of(maintenance));

        when(maintenanceRepository.save(any(MaintenanceModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0  ));

        MaintenanceResponseDTO response = maintenanceService.updateMaintenance(maintenance.getIdMaintenance());
        assertNotNull(response);
        assertEquals(
                EquipmentStatus.AVAILABLE,
                maintenance.getEquipment().getStatus()
        );

        verify(maintenanceRepository).save(any(MaintenanceModel.class));

    }

    @Test
    void shouldThrowExceptionWhenMaintenanceNotFound() {

        MaintenanceModel maintenance = TestDataFactory.mainteanance();


        when(maintenanceRepository.findById(maintenance.getIdMaintenance()))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception =  assertThrows(ResourceNotFoundException.class,() -> maintenanceService.updateMaintenance(maintenance.getIdMaintenance()));
        assertEquals("Maintenance Not Found", exception.getMessage());
        verify(maintenanceRepository, never()).save(any(MaintenanceModel.class));
    }

    @Test
    void shouldThrowExceptionWhenMaintenanceIsCompleted() {
        MaintenanceModel maintenance = TestDataFactory.mainteanance();
        maintenance.setMaintenanceStatus(MaintenanceStatus.COMPLETED);

        when(maintenanceRepository.findById(maintenance.getIdMaintenance()))
                .thenReturn(Optional.of(maintenance));

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> maintenanceService.updateMaintenance(maintenance.getIdMaintenance()));
        assertEquals("Maintenance has already been completed.", exception.getMessage());
        assertEquals(
                EquipmentStatus.MAINTENANCE,
                maintenance.getEquipment().getStatus()
        );
        verify(maintenanceRepository, never()).save(any(MaintenanceModel.class));
    }

}
