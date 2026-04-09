package com.demovete.veterinariabackend;

import com.demovete.veterinariabackend.model.Animal;
import com.demovete.veterinariabackend.model.Owner;
import com.demovete.veterinariabackend.repository.AnimalRepository;
import com.demovete.veterinariabackend.repository.OwnerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

//Una clase con @Entity equivale a una table de base de datos
//Un objeto equivale a una fila en una tabla de base de datos
//RECORDEMOS las Interfaces son abstractas, no se inicializan. No podemos tener un new()
@SpringBootApplication
public class VeterinariaBackendApplication {

    public static void main(String[] args) {
        //Nos guardamos el Spring en una varuable y asi puedo pedirle algo.
        var context = SpringApplication.run(VeterinariaBackendApplication.class, args);
        //Esto normalemnte lo hariamos en un controlador o servicio

        //Estamos creando una variable(animalRepository) del tipo Animal Repository
        //Necesitamos tener una variable declarada, que no sea null porque si no no nos guardaria nada,
        // inyeccion de dependencia (Luego lo vemos)
        //Le dice a Spring que nos de
        //Spring le llama Bean a los OBJETOS en memoria

        //Esto obtiene los repositorios para poder hacer operaciones de base de datos con ellos.
        AnimalRepository animalRepository = context.getBean(AnimalRepository.class);
        OwnerRepository ownerRepository = context.getBean(OwnerRepository.class);


        // Podemos crear un animal: new
        //Tiene que tener el constructor para poder pasarle valores.
        Animal objCloe = new Animal("Cloe", 7, 4.5);
        Animal objBlackito = new Animal("Blackito", 10, 3.2);
        // Guardar el animal en base de datos usando el repositorio: .save(), va estar vacio de momento
        //Esto lo escribimos asi en java pero lo traduce a SQL en la terminal veremos un INSERT

        //objCloe.setName("Cloe");
        //objCloe.setColor("Gris");
        //objCloe.setPeso(4.5);
        //objCloe.setEdad(7);
        //objCloe.setEspecie("Gato");
        //objCloe.setRaza("Europeo");
        //objCloe.setGenero("Hembra");


        animalRepository.save(objCloe);
        animalRepository.save(objBlackito);
        //Si queremos hacer varios objetos e insertarlos seria con el  metodo .saveAll()

        Owner objOwner = new Owner(null,"Barbara", "Urbano", 30, "123456H");
        Owner objOwner2 = new Owner(null,"Patricia", "Moreno", 53, "654321F");
        ownerRepository.save(objOwner);
        ownerRepository.save(objOwner2);

        //Para mirar los datos por la terminal
        //System.out.println("Animal guardado en base de datos: " + objCloe);
        //System.out.println("Owner guardado en base de datos: " + objOwner);


        //obtener todos los animales de base de datos que equivale a SELECT * from animal, con finAll()
        //animalRepository.findAll(); si lo dejo asi no lo estoy guardando en una variable, si quiero imprimirlos
        //voy a tener que guardarlo (forEach es un bucle for, que le pasa una funcion a otra funcion, lo veremos despues)

        //De momento lo haremos diferente.
        List<Animal> animals = animalRepository.findAll();
        //Esto lo sacaremos por el HTML, ahora lo estamos viendo aqui.
        //System.out.println(animals); cuando usabamos array veiamos el tamanio con .lenght(), pero en las
        //listas es con .size()

//        for(int i = 0; i < animals.size(); i++){
//            //como es una lista no va con corchetes, si no que usa un get()
//            System.out.println(animals.get(i));
//        }
        // esta es la mejor forma. Le dices que quieres iterar la lista de animales uno por uno.


        //se llama bucle forEach
        for(Animal animal : animals){
            System.out.println(animal);
        }

        //Imprimir los animales obtenidos con un bucle for.


        List<Owner> owners = ownerRepository.findAll();
        //System.out.println(owners);

        for(Owner owner : owners){
            System.out.println(owner);
        }

    }

}
