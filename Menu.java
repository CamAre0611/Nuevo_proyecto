import java.util.Scanner;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static DatabaseDemo databaseDemo;
        
	public static void main(String[] args) {
        databaseDemo = new DatabaseDemo();

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();
			scanner.nextLine();
            ejecutarOpcion(opcion);
        } while (opcion != 0);

    }

    private static void mostrarMenu() {
        System.out.println("----------- Menú -----------");
        System.out.println("[1] Ingresar datos");
        System.out.println("[2] Consultar datos");
        System.out.println("[3] Eliminar datos");
        System.out.println("[0] Salir");
        System.out.println("----------------------------");
    }

    private static int obtenerOpcion() {
        System.out.print("Ingrese una opción: ");
        return scanner.nextInt();
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                ingresarDatos();
                break;
            case 2:
                consultarDatos();
                break;
            case 3:
                eliminarDatos();
                break;
            case 0:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    private static void ingresarDatos() {
    try {
        System.out.println("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese precio del producto: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.println("Ingrese cantidad en inventario del producto: ");
        int inventario = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese código del producto: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        DatabaseDemo databaseDemo = new DatabaseDemo();
        Connection conn = databaseDemo.conectar();
        databaseDemo.ingresar(conn, nombre, precio, inventario, codigo);
    } catch (Exception e ) {
        System.out.println("Error con la clase: " + e.getMessage());
    } 
}
    private static void consultarDatos() {
        System.out.println("ingrese producto a consultar");
	String producto = scanner.nextLine();
	Connection conn = databaseDemo.conectar();
	databaseDemo.consultar(conn, producto);
    }

    private static void eliminarDatos() {
		System.out.println("Ingrese codigo del producto a elimiar");
    int codigo = Integer.parseInt(scanner.nextLine());
    Connection conn = databaseDemo.conectar();
	databaseDemo.eliminar(conn, codigo);	
        
    }
}