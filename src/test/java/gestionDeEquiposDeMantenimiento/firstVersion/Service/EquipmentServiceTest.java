package gestionDeEquiposDeMantenimiento.firstVersion.Service;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentUpdateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentService;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentStatus;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.BusinessRuleException;
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
public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @InjectMocks
    private EquipmentService equipmentService;


    @Test
    void shouldSaveEquipmentSuccessfully() {
        EquipmentCreateDTO request = TestDataFactory.validEquipmentRequest();

        when(equipmentRepository.save(any(EquipmentModel.class)))
                .thenAnswer(invocation -> {
                    EquipmentModel equipment = invocation.getArgument(0);
                    equipment.setIdEquipment(1L);
                    return equipment;
                });
        EquipmentResponseDTO response = equipmentService.saveEquipment(request);

        assertNotNull(response);
        assertEquals(
                EquipmentStatus.AVAILABLE,
                response.getStatus()
        );


        verify(equipmentRepository).save(any(EquipmentModel.class));

    }

    @Test
    void shouldThrowExceptionWhenEquipmentExists() {
        EquipmentCreateDTO request = TestDataFactory.validEquipmentRequest();

        when(equipmentRepository.existsBycodeInventory(request.getCodeInventory())).
                thenReturn(true);


        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> equipmentService.saveEquipment(request));
        assertEquals("A equipment with that code already Exists", exception.getMessage());

        verify(equipmentRepository, never()).save(any(EquipmentModel.class));
    }


    @Test
    void shouldUpdateSuccesfully() {
        EquipmentUpdateDTO request = TestDataFactory.validUpdateEquipmentRequest();
        EquipmentModel equipment = new EquipmentModel();
        equipment.setIdEquipment(1L);
        when(equipmentRepository.findById(equipment.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(equipmentRepository.save(any(EquipmentModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EquipmentModel response = equipmentService.updateEquipment(request, equipment.getIdEquipment());
        assertNotNull(response);
        assertEquals(false, equipment.getActive());

        verify(equipmentRepository).save(any(EquipmentModel.class));
    }


    @Test
    void shouldThrowExceptionWhenEquipmentIsInMaintenance() {
        EquipmentUpdateDTO request = TestDataFactory.validUpdateEquipmentRequest();
        EquipmentModel equipment = new EquipmentModel();
        equipment.setIdEquipment(1L);
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        equipment.setActive(true);

        when(equipmentRepository.findById(equipment.getIdEquipment()))
                .thenReturn(Optional.of(equipment));



        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> equipmentService.updateEquipment(request, equipment.getIdEquipment()));
        assertEquals("The equipment is being used", exception.getMessage());
        verify(equipmentRepository, never()).save(any(EquipmentModel.class));
    }


    @Test
    void shouldThrowExceptionWhenEquipmentIsLoaned() {
        EquipmentUpdateDTO request = TestDataFactory.validUpdateEquipmentRequest();
        EquipmentModel equipment = new EquipmentModel();
        equipment.setIdEquipment(1L);
        equipment.setStatus(EquipmentStatus.LOANED);
        equipment.setActive(true);

        when(equipmentRepository.findById(equipment.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> equipmentService.updateEquipment(request, equipment.getIdEquipment()));
        assertEquals("The equipment is being used", exception.getMessage());
        verify(equipmentRepository, never()).save(any(EquipmentModel.class));
    }




}
