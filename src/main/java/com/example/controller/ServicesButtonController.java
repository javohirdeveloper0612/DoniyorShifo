package com.example.controller;

import com.example.dto.services_button.ButtonCreateDTO;
import com.example.dto.services_button.ButtonResponseDTO;
import com.example.dto.services_button.ButtonUpdateDTO;
import com.example.enums.Language;
import com.example.service.ServicesButtonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services_button")
@Tag(name = "Services Button Controller", description = "this Controller for add button and delete button get list button and get by id")
public class ServicesButtonController {

    private final ServicesButtonService service;

    public ServicesButtonController(ServicesButtonService service) {
        this.service = service;
    }

    /**
     * This method for create new ServicesButton (only ADMIN)
     *
     * @param dto      ButtonCreateDTO
     * @return ButtonResponseDTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Create", description = "this method for create new button (only ADMIN)")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ButtonCreateDTO dto) {
        ButtonResponseDTO result = service.create(dto, Language.UZ);
        return ResponseEntity.ok().body(result);
    }

    /**
     * This method for delete ServicesButton (only ADMIN)
     *
     * @param id       the id of the button you want to launch is entered
     * @return String, Returns ButtonNotFoundException if not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete button", description = "This method for delete Services Button by Id (only ADMIN)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        String result = service.deleteById(id,Language.UZ);

        return ResponseEntity.ok(result);
    }

    /**
     * This Method get Button by id
     *
     * @param id       enter the id of the button you want to search for
     * @return ButtonResponseDto ,returns a ButtonNotFoundException if the button is not found
     */
    @Operation(summary = "Get Button", description = "This method for get Button by Id ")
    @GetMapping("/public/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {

        ButtonResponseDTO result = service.geById(id, Language.UZ);

        return ResponseEntity.ok().body(result);
    }

    /**
     * This method for Update Button by id
     *
     * @param id       enter the button id you want to change
     * @param dto      ButtonUpdateDTO
     * @return ButtonResponseDto, Throws a ButtonNotFoundException if the id is not found
     */

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update Services Button", description = "This method for services button by Id (only ADMIN) ")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody ButtonUpdateDTO dto) {
        ButtonResponseDTO result = service.updateById(id, dto, Language.UZ);

        return ResponseEntity.ok(result);
    }

    /**
     * This method for get Button List
     *
     * @return buttonList ,Returns ButtonNotFoundException if the button does not exist
     */
    @Operation(summary = "Get Button List", description = "This method for get list Button")
    @GetMapping("/public/get_list")
    public ResponseEntity<?> getList() {
        List<ButtonResponseDTO> result = service.getListButton(Language.UZ);

        return ResponseEntity.ok(result);
    }

}
