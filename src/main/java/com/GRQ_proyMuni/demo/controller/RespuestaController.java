package com.GRQ_proyMuni.demo.controller;

import com.GRQ_proyMuni.demo.entity.Respuesta;
import com.GRQ_proyMuni.demo.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    // Descargar archivo adjunto de una respuesta
    @GetMapping("/{id}/archivo")
    public ResponseEntity<org.springframework.core.io.Resource> descargarAdjunto(
            @PathVariable Integer id) {

        Respuesta respuesta = respuestaService.buscarPorId(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Respuesta no encontrada"));

        if (respuesta.getArchivoAdjunto() == null ||
                respuesta.getArchivoAdjunto().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No hay archivo adjunto");
        }

        Path archivo = Path.of(respuesta.getArchivoAdjunto());

        if (!Files.isRegularFile(archivo)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El archivo ya no está disponible");
        }

        return ResponseEntity.ok()
                .contentType(
                        org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .header(
                        org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                archivo.getFileName().toString() +
                                "\"")
                .body(
                        new org.springframework.core.io.FileSystemResource(
                                archivo));
    }
}