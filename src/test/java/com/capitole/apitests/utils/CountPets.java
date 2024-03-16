package com.capitole.apitests.utils;

import com.capitole.apitests.model.PetInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountPets {
    private List<PetInfo> pets;

    public CountPets(List<PetInfo> pets) {
        this.pets = pets;
    }

    public Map<String, Integer> count() {
        Map<String, Integer> result = new HashMap<>();
        for (PetInfo pet : pets) {
            if (result.containsKey(pet.getName())) {
                result.put(pet.getName(), result.get(pet.getName()) + 1);
            } else {
                result.put(pet.getName(), 1);
            }
        }
        return result;
    }
}
