package com.fit.se.service;

import com.fit.se.entity.Clothing;
import com.fit.se.repository.ClothingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClothingServiceImpl implements ClothingService {

    @Autowired
    private ClothingRepository clothingRepository;

    @Override
    public Clothing saveClothing(Clothing clothing) {
        return clothingRepository.save(clothing);
    }

    @Override
    public List<Clothing> getAllClothings() {
        return clothingRepository.findAll();
    }

    @Override
    public Clothing getClothingById(int id) {
        return clothingRepository.findById(id).get();
    }

    @Override
    public void deleteClothingById(int id) {
        clothingRepository.deleteById(id);
    }

    @Override
    public Clothing updateClothingById(int id, Clothing newClothing) {
        Clothing tempClothing = clothingRepository.findById(id).get();
        tempClothing.setSize(newClothing.getSize());
        tempClothing.setName(newClothing.getName());
        tempClothing.setPrice(newClothing.getPrice());
        return clothingRepository.save(tempClothing);
    }
}
