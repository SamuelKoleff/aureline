package dev.skoleff.aureline.infrastructure.persistence.storage;

import dev.skoleff.aureline.domain.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;


@Service
public class LocalFileStorageService implements FileStorageService {

    @Value("${app.upload.dir:aureline-images}")
    private String uploadDir;

    @Override
    public String save(String productId, byte[] fileData) {
        try {
            Path productDir = Path.of(uploadDir, productId);
            if (!Files.exists(productDir)) {
                Files.createDirectories(productDir);
            }

            String fileName = "image_" + UUID.randomUUID() + ".jpg";
            Path filePath = productDir.resolve(fileName);

            Files.write(filePath, fileData, StandardOpenOption.CREATE_NEW);

            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean delete(String productId, String name) {
        try {
            Path filePath = Path.of(uploadDir, productId, name);

            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return true;
            }

            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}