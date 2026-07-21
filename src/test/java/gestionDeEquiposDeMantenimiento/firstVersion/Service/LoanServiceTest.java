package gestionDeEquiposDeMantenimiento.firstVersion.Service;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentStatus;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.BusinessRuleException;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanService;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanResponseDTO;
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
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoanService loanService;


    @Test
    void shouldSaveLoanSuccessfully() {
        //Arrange
        LoanCreateDTO request = TestDataFactory.validLoanRequest();
        EquipmentModel equipment = TestDataFactory.availableEquipment();
        UserModel userReceiver = TestDataFactory.activeUser(2L);
        UserModel userDeliverer = TestDataFactory.activeUser(3L);


        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUserReceiver()))
                .thenReturn(Optional.of(userReceiver));

        when(userRepository.findById(request.getIdUserReceiver()))
                .thenReturn(Optional.of(userDeliverer));


        when(loanRepository.save(any(LoanModel.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        //Act
        LoanResponseDTO response = loanService.saveLoan(request);

        //Assert
        assertNotNull(response);
        assertEquals(
                EquipmentStatus.LOANED,
                equipment.getStatus()
        );

        verify(loanRepository).save(any(LoanModel.class));


    }

    @Test
    void shouldThrowExceptionWhenEquipmentIsNotAvailable() {
        //Arrange
        LoanCreateDTO request = TestDataFactory.validLoanRequest();
        EquipmentModel equipment = TestDataFactory.loanedEquipment();
        UserModel userReceiver = TestDataFactory.activeUser(2L);
        UserModel userDeliverer = TestDataFactory.activeUser(3L);

        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUserReceiver()))
                .thenReturn(Optional.of(userReceiver));

        when(userRepository.findById(request.getIdUserDeliverer()))
                .thenReturn(Optional.of(userDeliverer));


        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> loanService.saveLoan(request));

        assertEquals("The equipment is not available", exception.getMessage());

        verify(loanRepository, never()).save(any(LoanModel.class));
    }

    @Test
    void shouldThrowExceptionWhenEquipmentNotFound() {
        LoanCreateDTO request = TestDataFactory.validLoanRequest();


        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.empty());


        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> loanService.saveLoan(request));

        assertEquals("Equipment Not Found", exception.getMessage());

        verify(loanRepository, never()).save(any(LoanModel.class));

    }

    @Test
    void shouldThrowExceptionWhenUserReceiverIsInactvie() {

        LoanCreateDTO request = TestDataFactory.validLoanRequest();
        EquipmentModel equipment = TestDataFactory.availableEquipment();

        UserModel userReceiver = TestDataFactory.InactiveUser(request.getIdUserReceiver());
        UserModel userDeliverer = TestDataFactory.activeUser(request.getIdUserReceiver());



        when(equipmentRepository.findById(equipment.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(userReceiver.getIdUsuario()))
                .thenReturn(Optional.of(userReceiver));

        when(userRepository.findById(request.getIdUserDeliverer()))
                .thenReturn(Optional.of((userDeliverer)));

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> loanService.saveLoan(request));
        assertEquals("User is inactive", exception.getMessage());

        verify(loanRepository, never()).save(any(LoanModel.class));
    }

    @Test
    void shouldThrowExceptionWhenUserDelivererIsInactive() {
        LoanCreateDTO request = TestDataFactory.validLoanRequest();

        EquipmentModel equipment = TestDataFactory.availableEquipment();

        UserModel userReceiver = TestDataFactory.activeUser(request.getIdUserReceiver());

        UserModel userDeliverer = TestDataFactory.InactiveUser(request.getIdUserReceiver());

        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUserReceiver()))
                .thenReturn(Optional.of(userReceiver));

        when(userRepository.findById(request.getIdUserDeliverer()))
                .thenReturn(Optional.of(userDeliverer));

        BusinessRuleException exception = assertThrows(BusinessRuleException.class, () -> loanService.saveLoan(request));
        assertEquals("User is inactive", exception.getMessage());

        verify(loanRepository, never()).save(any(LoanModel.class));


    }


    @Test
    void shouldThrowExceptionWhenUserReceiverNotFound(){
        LoanCreateDTO request = TestDataFactory.validLoanRequest();
        EquipmentModel equipment = TestDataFactory.availableEquipment();
        UserModel userReceiver = TestDataFactory.activeUser(request.getIdUserReceiver());
        UserModel userDeliverer = TestDataFactory.activeUser(request.getIdUserDeliverer());
        userDeliverer.setActive(true);

        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUserReceiver()))
                .thenReturn(Optional.of(userReceiver));

        when(userRepository.findById(request.getIdUserReceiver()))
                .thenReturn(Optional.empty());


        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> loanService.saveLoan(request));
        assertEquals("User not found", exception.getMessage());

        verify(loanRepository, never()).save(any(LoanModel.class));
    }



    @Test
    void shouldThrowExceptionWhenUserDelivererNotFound(){
        LoanCreateDTO request = TestDataFactory.validLoanRequest();
        EquipmentModel equipment = TestDataFactory.availableEquipment();
        UserModel userReceiver = TestDataFactory.activeUser(request.getIdUserReceiver());


        when(equipmentRepository.findById(request.getIdEquipment()))
                .thenReturn(Optional.of(equipment));

        when(userRepository.findById(request.getIdUserReceiver()))
                .thenReturn(Optional.of(userReceiver));

        when(userRepository.findById(request.getIdUserDeliverer()))
                .thenReturn(Optional.empty());


        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> loanService.saveLoan(request));
        assertEquals("User not found", exception.getMessage());

        verify(loanRepository, never()).save(any(LoanModel.class));
    }


}
