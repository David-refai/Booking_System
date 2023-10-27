package com.example.bookingsystem.services;

import com.example.bookingsystem.entities.Addon;
import com.example.bookingsystem.repository.addonRepo.AddonRepo;
import org.springframework.stereotype.Service;


@Service
public class AddonService {

    private final AddonRepo addonRepository;

    public AddonService(AddonRepo addonRepository) {
        this.addonRepository = addonRepository;

}

    public void addAddon(Addon addon) {
        addonRepository.save(addon);
    }


    public void deleteAddonById(Long id) {
        addonRepository.deleteById(id);
    }

    public Iterable<Addon> findAll() {
        return addonRepository.findAll();
    }

    public Addon findById(Long id) {
        return addonRepository.findById(id).orElse(null);
    }

    public Addon update(Addon addon) {
        return addonRepository.save(addon);
    }
}
