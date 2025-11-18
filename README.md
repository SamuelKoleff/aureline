# Aureline

Sistema de gestión de productos para tienda de ropa, desarrollado con **Spring Boot** siguiendo principios de **Clean Architecture** y **Domain-Driven Design (DDD)**.

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas basada en DDD:

```
src/main/java/dev/skoleff/aureline/
├── domain/              # Lógica de negocio y modelos
│   ├── model/          # Agregados y entidades
│   ├── repository/     # Interfaces de repositorios
│   ├── service/        # Servicios de dominio
│   └── enums/          # Enumeraciones del dominio
├── application/         # Casos de uso
├── infrastructure/      # Adaptadores e implementaciones
│   ├── controller/     # API REST
│   ├── dto/            # DTOs y mappers
│   ├── persistence/    # JPA entities y repos
│   └── config/         # Configuración
```

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (desarrollo)
- **Lombok**
- **MapStruct** (mapeo de objetos)
- **SpringDoc OpenAPI** (documentación)

## 📦 Requisitos

- Java 21+
- Maven 3.8+

## 🔧 Instalación y Ejecución

```bash
# Clonar el repositorio
git clone https://github.com/SamuelKoleff/aureline.git
cd aureline

# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## 🗂️ Modelo de Dominio

### Product (Aggregate Root)
- Gestiona toda la información del producto
- Controla sus variantes (talle, color, stock)
- Administra las imágenes asociadas

### ProductVariant
- Variante específica de un producto
- Incluye: SKU, talle, color, stock, precio

### Enums
- **Gender**: `HOMBRE`, `MUJER`, `NINO`, `NINA`, `UNISEX`
- **ProductType**: `REMERA`, `PANTALON`, `ZAPATOS`, `VESTIDO`, `BUZO`, `CAMPERA`, `MUSCULOSA`, `ACCESORIO`

Tests incluidos:
- `ProductControllerTests`: Tests de endpoints
- `ProductJpaRepositoryTest`: Tests de repositorio
- `MappingEntityTests`: Tests de mappers

## 📁 Almacenamiento de Archivos

Las imágenes de productos se almacenan localmente en:
```
aureline-images/{productId}/
```

Configuración en `application.properties`:
```properties
app.upload.dir=aureline-images
```

## 🛠️ Casos de Uso Principales

- `SaveProductUseCase`: Crear/actualizar producto
- `GetActiveProductsUseCase`: Listar productos activos
- `GetProductByIdUseCase`: Obtener producto por ID
- `DeleteProductUseCase`: Eliminar producto
- `SaveProductImageUseCase`: Subir imagen de producto
- `DeleteProductImageUseCase`: Eliminar imagen

## 📝 Endpoints Principales

```http
GET    /api/products           # Listar productos activos
GET    /api/products/{id}      # Obtener producto
POST   /api/products           # Crear producto
PUT    /api/products/{id}      # Actualizar producto
DELETE /api/products/{id}      # Eliminar producto
POST   /api/products/{id}/images  # Subir imagen
DELETE /api/products/{id}/images  # Eliminar imagen
```
