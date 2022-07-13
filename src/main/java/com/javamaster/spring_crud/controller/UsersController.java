package com.javamaster.spring_crud.controller;

import com.javamaster.spring_crud.dto.UsersDto;
import com.javamaster.spring_crud.exception.ValidationException;
import com.javamaster.spring_crud.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
@CrossOrigin
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/save")
    public UsersDto saveUsers(@RequestBody UsersDto usersDto) throws ValidationException {
        log.info("Handling save users: " + usersDto);
        return usersService.saveUser(usersDto);
    }

    @GetMapping("/findAll")
    public List<UsersDto> findAllUsers() {
        log.info("Handling find all users request");
        return usersService.findAll();
    }

    @GetMapping("/findByLogin")
    public UsersDto findByLogin(@RequestParam String login) {
        log.info("Handling find by login request: " + login);
        return usersService.findByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
        log.info("Handling delete user request: " + id);
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "soatcolpatria.herokuapp.com/api/document/{placa}")
    public void documet(HttpServletResponse response, @PathVariable String placa) {
        try {

            SOAT soat = new SOAT(vehiculoDAO.buscarVehiculoPlaca(placa));
            byte[] pdfReport = soat.generarSOAT();
            String mimeType = "application/pdf";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", "reporte.pdf"));
            response.setContentLength(pdfReport.length);
            ByteArrayInputStream inStream = new ByteArrayInputStream(pdfReport);
            FileCopyUtils.copy(inStream, response.getOutputStream());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
