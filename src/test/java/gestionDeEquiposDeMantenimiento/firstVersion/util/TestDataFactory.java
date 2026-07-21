package gestionDeEquiposDeMantenimiento.firstVersion.util;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentStatus;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceStatus;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;

public class TestDataFactory {

    public static EquipmentModel availableEquipment() {
        EquipmentModel equipment = new EquipmentModel();
        equipment.setIdEquipment(1L);
        equipment.setActive(true);
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        return equipment;
    }

    public static EquipmentModel maintenanceEquipment() {
        EquipmentModel equipment = availableEquipment();
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        return equipment;
    }

    public static EquipmentModel loanedEquipment() {
        EquipmentModel equipment = availableEquipment();
        equipment.setStatus(EquipmentStatus.LOANED);
        return equipment;
    }

    public static UserModel activeUser(Long id) {
        UserModel user = new UserModel();
        user.setIdUsuario(id);
        user.setActive(true);
        return user;
    }

    public static UserModel InactiveUser(Long id) {
        UserModel user = activeUser(id);
        user.setActive(false);
        return user;
    }

    public static LoanCreateDTO validLoanRequest() {
        LoanCreateDTO dto = new LoanCreateDTO();
        dto.setIdEquipment(1L);
        dto.setIdUserReceiver(2L);
        dto.setIdUserDeliverer(3L);
        dto.setObservationsOut("equipo en buen estado");
        return dto;
    }

    public static MaintenanceCreateDTO validMaintenanceRquest() {
        MaintenanceCreateDTO maintenance = new MaintenanceCreateDTO();
        maintenance.setIdEquipment(1L);
        maintenance.setIdUser(2L);
        maintenance.setDescription("equipo en mantenimiento");
        maintenance.setPriceMaintenance("500");
        return maintenance;
    }

    public static MaintenanceModel mainteanance() {
        MaintenanceModel maint = new MaintenanceModel();
        maint.setIdMaintenance(3L);
        maint.setEquipment(maintenanceEquipment());
        maint.setUserRegister(activeUser(2L));
        maint.setMaintenanceStatus(MaintenanceStatus.IN_PROGRESS);
        return maint;
    }



}
