package com.fit.se.controller;

import com.fit.se.entity.Clothing;
import com.fit.se.repository.ClothingRedisRepository;
import com.fit.se.service.ClothingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clothings")
@AllArgsConstructor
public class ClothingController {

    private ClothingService clothingService;
    @Autowired
    private ClothingRedisRepository clothingRedisRepository;

    @PostMapping
    public ResponseEntity<?> saveClothing(@RequestBody Clothing clothing) {
        Clothing savedClothing = clothingService.saveClothing(clothing);
        clothingRedisRepository.saveClothing(clothing);
        return new ResponseEntity<>(savedClothing, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllClothings() {
        List<Clothing> clothingList = clothingRedisRepository.findAll();
        return ResponseEntity.ok(clothingList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClothingById(@PathVariable int id) {
        Clothing clothing = clothingRedisRepository.findById(id);
        return ResponseEntity.ok(clothing);
    }

    @DeleteMapping("/{id}")
    public void deleteClothingById(@PathVariable int id) {
        clothingService.deleteClothingById(id);
        clothingRedisRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClothingById(@PathVariable int id, @RequestBody Clothing clothing) {
        Clothing updatedClothing = clothingService.updateClothingById(id, clothing);
        clothingRedisRepository.update(updatedClothing);
        return ResponseEntity.ok(updatedClothing);
    }
}
