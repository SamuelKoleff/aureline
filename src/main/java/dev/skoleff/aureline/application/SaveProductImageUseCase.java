package dev.skoleff.aureline.application;

import dev.skoleff.aureline.domain.service.FileStorageService;

@UseCase
public class SaveProductImageUseCase {

    public final FileStorageService fileStorageService;

    public SaveProductImageUseCase(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public String execute(String productId, byte[] file){
        return fileStorageService.save(productId, file);
    }
}
