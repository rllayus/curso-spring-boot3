# CURSO DE SPRING BOOT 
Java Spring Framework (Spring Framework) es una popular estructura empresarial de código abierto para crear aplicaciones
independientes de nivel de producción que se ejecutan en la máquina virtual Java (JVM).

## Authors

- [@rllayus](https://github.com/rllayus)

## Contenido
* **1. Creación de un proyecto de Spring Boot con Spring Initializr**
* **2. Configuración de conexión a una base de datos**
* **3. Creación de entidades**
* **4. Creación de Repository**
* **5. Creación de Service**
* **6. Creación de controller**
* **7. Configuración de seguridad**

## 2. Configuración de conexión a una base de datos
Para configurar el acceso a base de datos de la aplicación Spring Boot, se debe agregar al archivo application.properties
los siguientes parámetros.
``` properties
spring.datasource.url=jdbc:postgresql://[IP_DB]:5432/[DB_NAME]
spring.datasource.username=[USER_NAME]
spring.datasource.password=[PASSWORD]
```
Reemplace los valores de ente corchetes por los valores correctos de su base de dato
## 3. Creación de entidades
Spring Boot con la ayuda de Hibernate nos permite generar script para crear las tablas y como tiene conexión a la Base 
de Datos, es posible que la misma aplicación cree todos los objetos necesario en la base de datos.

Ejemplo para crear una entidad
``` java
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 60)
    private String name;
}
```
### Configuración para generar ddl
Es posible indicarle a Spring Boot que genere los ddl(script) para generar y ejecutar en el motor de base de datos, para
ello debemos agregar la siguiente propiedad al archivo de propiedades.
``` properties
spring.jpa.hibernate.ddl-auto=update
```
Esta propiedad tiene varios valores y los más importantes son:
* none.- Este valor indica a Spring Boot que no genere los ddl y por ende no genera ni actualiza la base de datos 
* create.- Crea de nuevo todos los objetos, pero primero elimina todos los datos
* create-drop.- Crea de nuevo todos los objetos, pero primero elimina todos los datos
* update.- Este valor si no hay las tablas los crea o los actualiza, y es la recomendad tanto para ambiente de desarrollo
* como para producción.

Para lograr que la entidad creada hasta el momento pueda generar las tablas a nivel de base de datos, es necesario realizar
configurar a nivel de la clase principal de Spring Boot.

``` java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.upb.modulo_01.repository")
@EntityScan("com.upb.modulo_01.entity")
@SpringBootApplication(scanBasePackages = "com.upb.modulo_01")
public class Modulo01Application {
	public static void main(String[] args) {
		SpringApplication.run(Modulo01Application.class, args);
	}
}
```
Al ejecutar la aplicación con las configuraciones e implementaciones realizadas hasta el momento, debiera genera la tabla
*company*.

