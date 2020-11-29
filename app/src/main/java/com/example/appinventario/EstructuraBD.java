package com.example.appinventario;

/**
 * Clase encargada de la definición de la estructura de la base de datos SQLite
 */
public class EstructuraBD {
    /**
     * Constructor por defecto
     */
    private EstructuraBD() {}

    //Declaración de constantes que contienen la información correspondiente a la estructura de la base de datos
    public static final String TABLE_NAME = "pedidos";
    public static final String COLUMNA1= "id";
    public static final String COLUMNA2= "nombre";
    public static final String COLUMNA3= "descripcion";
    public static final String COLUMNA4= "cantidad";
    public static final String COLUMNA5= "precio";

    //Declaración de constante que contiene la sentencia SQL para la creación de la tabla "pedidos"
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraBD.TABLE_NAME + " (" +
                    EstructuraBD.COLUMNA1 + " INTEGER PRIMARY KEY," +
                    EstructuraBD.COLUMNA2 + " TEXT," +
                    EstructuraBD.COLUMNA3 + " TEXT," +
                    EstructuraBD.COLUMNA4 + " TEXT," +
                    EstructuraBD.COLUMNA5 + " TEXT)";

    //Declaración de constante que contiene la sentencia SQL para eliminar la tabla "pedidos" de la BD
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBD.TABLE_NAME;

    //Declaración de constante que contiene la sentencia SQL para eliminar los registros de la tabla "pedidos" de la BD
    public static final String SQL_DELETE_REGISTERS =
            "DELETE FROM " + EstructuraBD.TABLE_NAME;
}//Fin Class
