package dev.skoleff.aureline.application;

import dev.skoleff.aureline.domain.service.FileStorageService;

@UseCase
public class DeleteProductImageUseCase {
    public final FileStorageService fileStorageService;

    public DeleteProductImageUseCase(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public Boolean execute(String productId, String name){
        return fileStorageService.delete(productId, name);
    }
}
