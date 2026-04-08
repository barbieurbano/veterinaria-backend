package com.demovete.veterinariabackend.model;
//Estos se importan cuando hemos puesto @Entity y @Id (no llevan ; al final porque son anotaciones)
import jakarta.persistence.*;
//Se importan todos los paquetes import jakarta.persistence.*; y me borraria el de .Id, .GeneratedValue..


/*
SELECT * FROM ANIMAL;
INSERT INTO animal (ID, NOMBRE, RAZA, ESPECIE) VALUES (2, 'BLACKITO', 'COMUN', 'CONEJO')
UPDATE animal SET ESPECIE='GATO' WHERE id=1
UPDATE animal SET GENERO='HEMBRA' WHERE id=1
UPDATE animal SET GENERO='MACHO' WHERE id=2
 */


@Entity
//Por si le queremos cambiar el nombre de la tabla.
@Table(name = "ANIMALES")
public class Animal {
    //Tiene mayor capacidad de almacenamiento el LONG.
    @Id
    //Para generar automaticamante el ID, se puede configurar la estrategia esta es la opcion mas comun
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Si necesitamos que sea unico el nombre es @Column(unique) CTRL+espacio te da las opciones

    private String name;
    //Si quisieramos que tenga un valor por defecto usamos @Column(columnDefinition="BOOLEAN DEFAULT true")
    @Column(columnDefinition="BOOLEAN DEFAULT true")
    private String especie;
    private Integer edad;
    private String raza;
    private String color;
    private String genero;
    private Double peso;

    //Nos faltan ver las asociaciones - relaciones entre tablas
    //(esto se hace entre las claves primarias y foraneas), Enum, Agregar fechas
}
