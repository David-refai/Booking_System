package com.example.bookingsystem.repository.addonRepo;

import com.example.bookingsystem.entities.Addon;

public class AddonRepoImp {
    private AddonRepo addonRepo;

    public AddonRepoImp(AddonRepo addonRepo) {
        this.addonRepo = addonRepo;
    }

    public void save(Addon addon) {
        addonRepo.save(addon);
    }

    public void deleteById(Long id) {
        addonRepo.deleteById(id);
    }

    public Iterable<Addon> findAll() {
        return addonRepo.findAll();
    }

    public Addon findById(Long id) {
        return addonRepo.findById(id).orElse(null);
    }

    public Addon update(Addon addon) {
        return addonRepo.save(addon);
    }

}
