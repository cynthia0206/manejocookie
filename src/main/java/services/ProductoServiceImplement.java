package services;

import models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImplement implements ProductoService {
    @Override
    public List<Producto> listar(){
        return Arrays.asList(new Producto(1L, "Laptop", "computacion", 250.25),
        new Producto (2L, "Refrigeradora", "cocina", 745.13),
        new Producto (3L, "cama", "dormitorio", 350.12));

    }

}
