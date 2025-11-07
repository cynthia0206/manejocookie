package models;
/*

 */
public class Producto {
    // declaramos las variables de mi objeto producto
    private Long idProducto;
    private String nombre;
    private String categoria;
    private Double precio;
    // declaramos un constructor vacio, permite ejecutar nuestra clase producto
    public Producto (){

    }
    //Implementamos un contructor que incialice los parametros de producto
    public Producto(Long idProducto, String nombre, String categoria, Double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }
    // generamos los metodos get and setter,
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public Long getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}

