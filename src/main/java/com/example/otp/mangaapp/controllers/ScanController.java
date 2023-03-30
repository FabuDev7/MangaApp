package com.example.otp.mangaapp.controllers;

import com.example.otp.mangaapp.dto.ScanDto;
import com.example.otp.mangaapp.services.ScanRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/scan")
public class ScanController {

    private ScanRetrieverService scanRetrieverService;

    @Autowired
    public ScanController(ScanRetrieverService scanRetrieverService) {
        this.scanRetrieverService = scanRetrieverService;
    }

    @Autowired


    @GetMapping("/all")
    public ResponseEntity<List<ScanDto>> listAllScans() {
        List<ScanDto> scanDtoList = new ArrayList<>();
        ScanDto scanDto = ScanDto.builder()
                .title("Boku no hero academia")
                .content("La storia è ambientata in un mondo in cui, a causa di una mutazione genetica, " +
                        "evidenziata in primo luogo dalla mancanza di un'articolazione del mignolo del piede, " +
                        "il genere umano ha ottenuto la capacità di sviluppare superpoteri denominati Quirk, " +
                        "che nei bambini si manifestano entro l'età di quattro anni: si stima che circa l’80% della popolazione mondiale possegga un Quirk.")
                .build();
        scanDtoList.add(scanDto);
        return ResponseEntity.ok().body(scanDtoList);
    }

    @GetMapping("/testScan")
    public ResponseEntity<List<String>> testScan() {
        List<String> linksScan = scanRetrieverService.retrieveScan();

        return ResponseEntity.ok().body(linksScan);
    }
}
