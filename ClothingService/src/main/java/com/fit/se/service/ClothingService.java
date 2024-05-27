package com.fit.se.service;

import com.fit.se.entity.Clothing;

import java.util.List;

public interface ClothingService {

    Clothing saveClothing(Clothing clothing);

    List<Clothing> getAllClothings();

    Clothing getClothingById(int id);

    void deleteClothingById(int id);

    Clothing updateClothingById(int id, Clothing clothing);

}
