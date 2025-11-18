package dev.skoleff.aureline.domain.service;

public interface FileStorageService {
    String save(String productId, byte[] file);
    Boolean delete(String productId, String imageName);
}
