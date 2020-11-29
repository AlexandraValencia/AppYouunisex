package com.example.appinventario;

/**
 * ESTA CLASE CONTIENE LOS ATRIBUTOS PARA LOS PRODUCTOS
 * ADEMÁS DE LA DEFINICIÓN DE PRODUCTOS SEGÚN EL TIPO
 */
public class Producto {
//DECLARACIÓN DE VARIABLES
    private String nombre;
    private String descripcion;
    private int imagenID;
    private int precio ;

//DECLARACIÓN DE ARREGLO CONSTANTE QUE CONTIENE PRODUCTOS DEL TIPO CERVEZAS
    public final static Producto hombre[]={
            new Producto("Camisa","Camisa para lucir elegante, tipo Polo casual",R.drawable.camisa,15),
            new Producto("Pantalon", "Pantalon para caballero, para toda ocasion",R.drawable.pantalon,25),
            new Producto("Short","Short para caballero, ideal para dias calurosos o para ir a la playa",R.drawable.shorts,20)
    };
//DECLARACIÓN DE ARREGLO CONSTANTE QUE CONTIENE PRODUCTOS DEL TIPO PIZZAS
    public final static Producto mujer[]={
            new Producto("Blusa","Blusa elegante para dama, con costura de alta calidad",R.drawable.blusa,25),
            new Producto("Jeans","Jeans casuales, comodos e ideales para salir de paseo",R.drawable.jeans,20),
            new Producto("Vestido","Vestido para lucir elegante",R.drawable.vestido,35)
    };

    /**
     * Constructor por defecto
     */
    public Producto() {
    }

    /**
     *CONSTRUCTOR CON PARÁMETROS
     */
    public Producto(String nombre, String descripcion, int imagenID, int precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenID = imagenID;
        this.precio = precio;
    }

    //GETTERS Y SETTERS
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenID() {
        return imagenID;
    }

    public void setImagenID(int imagenID) {
        this.imagenID = imagenID;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String toString() {
        return nombre;
    }

    public static Producto[] getCervezas() {
        return hombre;
    }

    public static Producto[] getPizzas() {
        return mujer;
    }
}



