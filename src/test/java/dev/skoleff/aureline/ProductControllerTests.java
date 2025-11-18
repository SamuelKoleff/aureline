package dev.skoleff.aureline;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@SpringJUnitWebConfig(AurelineApplication.class)
@Transactional
public class ProductControllerTests {

    private final MockMvcTester mockMvc;

    ProductControllerTests(@Autowired WebApplicationContext wac) {
        this.mockMvc = MockMvcTester.from(wac);
    }

    @Test
    void createProductTest() {
        mockMvc.post().uri("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "name": "n1",
                    "model": "m1",
                    "description": "description",
                    "brand": "nike",
                    "price": 100,
                    "type": "ACCESORIO",
                    "gender": "HOMBRE",
                    "productVariants": []
                }
                """)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.CREATED)
                .bodyJson().extractingPath("$.name").isEqualTo("n1");
    }

    @Test
    void badRequestCreateProductTest(){
        mockMvc.post().uri("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "name": "n1",
                    "model": "m1",
                    "description": "description",
                    "brand": "nike",
                    "price": 100,
                    "type": "ACCESORI",
                    "gender": "HOMBRE"
                }
                """)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.BAD_REQUEST);
    }

    @Test
    @Sql("/insert_product.sql")
    void retrieveProductByIdTest(){
        String realProductId = "c2e1298f-9c05-4991-b78b-d60e9b18c1cb";
        mockMvc.get().uri("/api/products/".concat(realProductId))
                .exchange()
                .assertThat()
                .hasStatusOk()
                .bodyJson().extractingPath("$.name").isEqualTo("n1");
    }

    @Test
    @Sql("/insert_product.sql")
    void notFoundRetriveProductTest(){
        String fakeProductId = "c2e1298f-9c05-4991-b78b-d60e9b18c1cc";
        mockMvc.get().uri("/api/products/".concat(fakeProductId))
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.NOT_FOUND);
    }

    @Test
    @Sql("/insert_product.sql")
    void updateProductTest(){
        String realProductId = "c2e1298f-9c05-4991-b78b-d60e9b18c1cb";
        mockMvc.put().uri("/api/products/".concat(realProductId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "name": "nn1",
                    "model": "m1",
                    "description": "description",
                    "brand": "nike",
                    "price": 100,
                    "type": "ACCESORIO",
                    "gender": "HOMBRE",
                    "active": "false",
                    "productVariants": []
                }
                """)
                .exchange()
                .assertThat()
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.name").isEqualTo("nn1");

    }

    @Test
    @Sql("/insert_product.sql")
    void badRequestUpdateProductTest(){
        String realProductId = "c2e1298f-9c05-4991-b78b-d60e9b18c1cb";
        mockMvc.put().uri("/api/products/".concat(realProductId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "name": "nn1",
                    "model": "m1",
                    "description": "description",
                    "brand": "nike",
                    "price": 100,
                    "type": "ACCESORIO"
                }
                """)
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.BAD_REQUEST);
    }

    @Test
    @Sql("/insert_product.sql")
    void deleteProductTest(){
        String realProductId = "c2e1298f-9c05-4991-b78b-d60e9b18c1cb";
        mockMvc.delete().uri("/api/products/".concat(realProductId))
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.NO_CONTENT);
    }

    @Test
    @Sql("/insert_product.sql")
    void notFoundDeleteProductTest(){
        String fakeProductId = "c2e1298f-9c05-4991-b78b-d60e9b18c1cc";
        mockMvc.delete().uri("/api/products/".concat(fakeProductId))
                .exchange()
                .assertThat()
                .hasStatus(HttpStatus.NOT_FOUND);
    }

    @Test
    @Sql("/insert_product.sql")
    void uploadAndDeleteImageTest() throws IOException {
        String productId = "c2e1298f-9c05-4991-b78b-d60e9b18c1cb";

        byte[] imageBytes = getClass().getResourceAsStream("/jean_azul.jpg")
                .readAllBytes();

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "jean_azul.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                imageBytes
        );

        MvcTestResult result = mockMvc.perform(multipart("/api/products/" + productId + "/images")
                .file(mockFile));

        result.assertThat().hasStatusOk();

        String fileName = result.getResponse().getContentAsString();
        assertNotNull(fileName);
        assertFalse(fileName.isEmpty());

        Path filePath = Path.of("aureline-images", productId, fileName);
        assertTrue(Files.exists(filePath));

        byte[] savedBytes = Files.readAllBytes(filePath);
        assertArrayEquals(imageBytes, savedBytes);

        mockMvc.perform(delete("/api/products/" + productId + "/images/" + fileName))
                .assertThat()
                .hasStatus(HttpStatus.NO_CONTENT);

        assertFalse(Files.exists(filePath));
    }

    @Test
    @Sql("/insert_product.sql")
    void deleteImageNotFoundTest() {
        String productId = "c2e1298f-9c05-4991-b78b-d60e9b18c1cb";

        mockMvc.perform(delete("/api/products/" + productId + "/images/noexiste.jpg"))
                .assertThat()
                .hasStatus(HttpStatus.NOT_FOUND);
    }

}
