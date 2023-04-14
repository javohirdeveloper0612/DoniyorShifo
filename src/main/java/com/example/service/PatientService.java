package com.example.service;

import com.example.controller.TelegramBotController;
import com.example.dto.patient.CreatePatientDto;
import com.example.entity.DoctorEntity;
import com.example.entity.PatientEntity;
import com.example.enums.Language;
import com.example.exp.doctor.DoctorNotFoundException;
import com.example.exp.patient.PatientNotFoundException;
import com.example.exp.telegram.TelegramBotException;
import com.example.repository.DoctorRepository;
import com.example.repository.PatientRepository;
import com.example.util.SendMsg;
import com.example.util.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ResourceBundleService resourceBundleService;
    private final TelegramBotController botController;

    private final ToDTO toDTO;


    @Autowired
    public PatientService(PatientRepository patientRepository, DoctorRepository doctorRepository, ResourceBundleService resourceBundleService, TelegramBotController botController, ToDTO toDTO) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.resourceBundleService = resourceBundleService;
        this.botController = botController;
        this.toDTO = toDTO;
    }


    /**
     * This method is used for saving Patient data in DB If doctor not found throw DoctorNotFoundException
     *
     * @param patientDto PatientDto
     * @return PatientDto
     */
    public ResponseEntity<?> createPatient(CreatePatientDto patientDto, Language language) {

        Optional<DoctorEntity> optional = doctorRepository.findById(patientDto.getDoctorId());
        if (optional.isEmpty()) {
            throw new DoctorNotFoundException(resourceBundleService.getMessage("doctor.not.found.id", language.name()));
        }



        DoctorEntity doctorEntity = optional.get();
        PatientEntity patient = new PatientEntity();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setPhone(patientDto.getPhone());
        patient.setDate(patientDto.getDate());
        patient.setDoctorId(doctorEntity);

        PatientEntity savedEntity = patientRepository.save(patient);

        try {
            botController.send(SendMsg.sendMsg(1024661500L,"\uD83C\uDD94  Id raqami: "+patient.getId()+"\n" +
                    "\uD83D\uDCCE  Ismi: "+patient.getFirstName()+"\n" +
                    "\uD83D\uDCCE  Familiya: "+patient.getLastName()+"\n" +
                    "☎  Raqami: "+patient.getPhone()+"\n" +
                    "\uD83D\uDC68\u200D⚕  Doctor Ismi Familiyasi: "+ optional.get().getFirstName_ru()+" "+optional.get().getLastName_ru() + "\n" +
                    "\uD83D\uDCC5  Sana: "+patient.getDate()));
        }catch (Exception e){

        }




        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO.toResponsePatientDto(savedEntity));
    }

    /**
     * This method is used for getting patient data by id If it is not exist throw PatientNotFoundException
     *
     * @param id       Integer
     * @return ResponsePatientDto
     */
    public ResponseEntity<?> getPatientById(Integer id, Language language) {
        Optional<PatientEntity> optional = patientRepository.findById(id);
        if (optional.isEmpty()) {
            throw new PatientNotFoundException(resourceBundleService.getMessage("patient.not.found", language.name()));
        }
        PatientEntity patient = optional.get();
        return ResponseEntity.ok(toDTO.toResponsePatientDto(patient));
    }

    /**
     * This method is used for getting all the patient data order by createdDate
     *
     * @return List<PatientEntity></PatientEntity>
     */
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(patientRepository.getAllPatient());
    }
}
