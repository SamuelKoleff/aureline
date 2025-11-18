package dev.skoleff.aureline;

import dev.skoleff.aureline.domain.enums.Gender;
import dev.skoleff.aureline.domain.enums.ProductType;
import dev.skoleff.aureline.infrastructure.persistence.entity.ProductEntity;
import dev.skoleff.aureline.infrastructure.persistence.repository.JpaProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class ProductJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JpaProductRepository repository;

    @Test
    void saveProduct_validIsSaved_EqualsEntities() {
        //given
        ProductEntity productEntityToSave = new ProductEntity(
                "name", "model", "description", BigDecimal.valueOf(100.50).setScale(2, RoundingMode.HALF_DOWN), true, Gender.HOMBRE, ProductType.ACCESORIO, "nike", List.of()
        );
        //when
        ProductEntity productEntitySaved = repository.save(productEntityToSave);
        entityManager.flush();
        entityManager.clear();
        //then
        Optional<ProductEntity> productFound = this.repository.findById(productEntitySaved.getId());
        assertThat(productFound.isPresent()).isTrue();
        assertThat(productFound.get()).usingRecursiveComparison().isEqualTo(productEntitySaved);
    }

    @Test
    void getActiveProducts_validRetrieved(){
        //given
        ProductEntity productEntityToSave1 = new ProductEntity("n1", "m1", "description", BigDecimal.valueOf(100), true, Gender.HOMBRE, ProductType.ACCESORIO, "nike", List.of());
        ProductEntity productEntityToSave2 = new ProductEntity("n2", "m2", "description", BigDecimal.valueOf(100), true, Gender.HOMBRE, ProductType.ACCESORIO, "nike", List.of());
        ProductEntity productEntityToSave3 = new ProductEntity("n3", "m3", "description", BigDecimal.valueOf(100), true, Gender.HOMBRE, ProductType.ACCESORIO, "nike", List.of());
        ProductEntity productEntityToSave4 = new ProductEntity("n4", "m4", "description", BigDecimal.valueOf(100), true, Gender.HOMBRE, ProductType.ACCESORIO, "nike", List.of());
        productEntityToSave4.setActive(false);
        //when
        repository.save(productEntityToSave1);
        repository.save(productEntityToSave2);
        repository.save(productEntityToSave3);
        repository.save(productEntityToSave4);
        entityManager.flush();
        entityManager.clear();
        //then
        List<ProductEntity> actives = repository.findByActiveTrue();
        assertThat(actives)
                .extracting(ProductEntity::getName)
                .containsExactlyInAnyOrder("n1", "n2", "n3");
    }


}
