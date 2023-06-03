import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
//import com.mysql.cj.jdbc.Driver;


public class DatabaseDemo {

// Datos de conexión a la base de datos
    static final String DB_URL = "jdbc:mysql://localhost:3306/productos";
    static final String USER = "root";
    static final String PASS = "";

    public Connection conectar(){
        Connection conn = null;
		
        try {
            // Paso 1: Registrar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Paso 2: Abrir la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            
        
		}catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("Error con la clase: " + e.getMessage());
        }
		return conn;
	}
	
	public void consultar(Connection conn, String producto){
			Statement stmt = null;
			try{
			
			// Paso 3: Crear la consulta SQL
            String nombreProd = producto;  // Valor proporcionado por el usuario
            String sql = "SELECT * FROM productos WHERE nombre = '" + nombreProd + "' ";

            // Paso 4: Ejecutar la consulta
            System.out.println("Ejecutando consulta...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Paso 5: Procesar los resultados
            if (rs.next()) {
                System.out.println("los datos del producto son, -Nombre:" + rs.getString("nombre") +  " -el codigo es:" + rs.getInt("codigo") + " -unidades en stock:  " + rs.getInt("inventario") );
            } else {
                System.out.println("El producto no se encuentra en la base de datos.");
            }

            // Paso 6: Cerrar los recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Manejo de errores de JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Manejo de errores generales
            e.printStackTrace();
        } finally {
            // Cerrar los recursos en un bloque "finally" para asegurarse de que se cierren incluso si ocurre una excepción
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // No se puede hacer mucho más aquí
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
	}
	
	
   public void ingresar(Connection conn, String nombre, double precio, int inventario, int codigo) {
        try {
            String sql = "INSERT INTO productos (nombre, precio, inventario, codigo) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Paso 3: Crear la consulta SQL parametrizada
            // Paso 4: Preparar la consulta
            System.out.println("Insertando datos...");
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, inventario);
            statement.setInt(4, codigo);
            

            // Paso 5: Ejecutar la consulta
            statement.executeUpdate();
            System.out.println("Los datos del producto han sido insertados correctamente.");
            // Paso 6: Cerrar los recursos
            
            statement.close();
            conn.close();
        } catch (SQLException se) {
            // Manejo de errores de JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Manejo de errores generales
            e.printStackTrace();
        }
    }
	 public void eliminar (Connection conn, int codigo){
       try {
			String sql = "SELECT * FROM productos WHERE codigo = " + codigo + " ";
			System.out.println("Ejecutando consulta...");
			Statement stmt = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
				sql = "DELETE FROM productos WHERE codigo = ? ";
				PreparedStatement statement = conn.prepareStatement(sql);           
				System.out.println("Eliminando datos...");        
				statement.setInt(1, codigo);
            // Paso 5: Ejecutar la consulta
				statement.executeUpdate();
				System.out.println("Los datos del producto han sido eliminados correctamente.");
				statement.close();
				
			} else {
                System.out.println("El producto no se encuentra en la base de datos.");
            }                 
            conn.close();
        } catch (SQLException se) {
            // Manejo de errores de JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Manejo de errores generales
            e.printStackTrace();
        }
}
}
	
        
