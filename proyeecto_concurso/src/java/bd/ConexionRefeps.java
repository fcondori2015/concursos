/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import dominio.Localidad;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
import hibernate.dao.impl.LocalidadDaoImpl;
import hibernate.dao.LocalidadDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dominio.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author favio
 */
public class ConexionRefeps {

    public List<Persona> buscarProfesionalRefepsNombreCompleto(String nombreCompleto) throws SQLException {

        LocalidadDao daoLocalidad = new LocalidadDaoImpl();
        List<Persona> listaPersona = new ArrayList<Persona>();

        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/siisaDB";
        String user = "nmlosada";
        String password = "siisa1234";
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user, password);
            stmt = con.createStatement();

            String consultaSQL = "SELECT * FROM \"vw_profesionalDatosPersonales\" WHERE nombre like upper('%" + nombreCompleto + "%')OR similarity(nombre,'" + nombreCompleto + "')>=0.50;";
            ResultSet rs = stmt.executeQuery(consultaSQL);

            int contadorDeRegistros = 0;

            while (rs.next()) {
                //  System.out.println(contadorDeRegistros + " - CODIGO: " + rs.getLong("codigo_de_profesional") + " - Nombre Completo: " + rs.getString("nombrecompleto") + " - dNI: " + rs.getString("numero_documento"));
                contadorDeRegistros = contadorDeRegistros + 1;
                Persona persona = new Persona();
                persona.setIdPersona(contadorDeRegistros);
                persona.setDni(Integer.parseInt(rs.getString("numero_documento")));
                persona.setApellido(rs.getString("apellido"));
                persona.setNombres(rs.getString("nombre"));
                persona.setCuil(rs.getLong("cuil"));
                persona.setSexo(rs.getString("sexo"));
                persona.setFechaDeNacimiento(rs.getDate("fecha_de_nacimiento"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setTelefono(rs.getString("tel1"));
                persona.setEmail(rs.getString("email"));

//                Localidad localidadEncontrada = daoLocalidad.getLocalidadPorCodigo(rs.getLong("localidad_nacimiento"));
//                if (localidadEncontrada.getCodigoLocalidad() != 0) {
//                    persona.setLocalidadByLocalidadNacimiento(localidadEncontrada);
//                }
                listaPersona.add(persona);

            }

        } catch (SQLException exSQL) {
            exSQL.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            stmt.close();
            con.close();
        }

        return listaPersona;

    }

    public List<Persona> buscarProfesionalRefepsApellido(String apellido) throws SQLException {

        LocalidadDao daoLocalidad = new LocalidadDaoImpl();
        List<Persona> listaPersona = new ArrayList<Persona>();

        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/siisaDB";
        String user = "nmlosada";
        String password = "siisa1234";
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user, password);
            stmt = con.createStatement();

            String consultaSQL = "SELECT * FROM \"vw_profesionalDatosPersonales\" WHERE apellido like upper('%" + apellido + "%')OR similarity(apellido,'" + apellido + "')>=0.50;";
            ResultSet rs = stmt.executeQuery(consultaSQL);

            int contadorDeRegistros = 0;

            while (rs.next()) {
                //  System.out.println(contadorDeRegistros + " - CODIGO: " + rs.getLong("codigo_de_profesional") + " - Nombre Completo: " + rs.getString("nombrecompleto") + " - dNI: " + rs.getString("numero_documento"));
                contadorDeRegistros = contadorDeRegistros + 1;
                Persona persona = new Persona();
                persona.setIdPersona(contadorDeRegistros);
                persona.setDni(Integer.parseInt(rs.getString("numero_documento")));
                persona.setApellido(rs.getString("apellido"));
                persona.setNombres(rs.getString("nombre"));
                persona.setCuil(rs.getLong("cuil"));
                persona.setSexo(rs.getString("sexo"));
                persona.setFechaDeNacimiento(rs.getDate("fecha_de_nacimiento"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setTelefono(rs.getString("tel1"));
                persona.setEmail(rs.getString("email"));

//                Localidad localidadEncontrada = daoLocalidad.getLocalidadPorCodigo(rs.getLong("localidad_nacimiento"));
//                if (localidadEncontrada.getCodigoLocalidad() != 0) {
//                    persona.setLocalidadByLocalidadNacimiento(localidadEncontrada);
//                }
                listaPersona.add(persona);

            }

        } catch (SQLException exSQL) {
            exSQL.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            stmt.close();
            con.close();
        }

        return listaPersona;

    }

    public Persona buscarProfesionalRefepsDni(String dni) throws SQLException {

        LocalidadDao daoLocalidad = new LocalidadDaoImpl();
        Persona personaEntidad = new Persona();
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/siisaDB";
        String user = "nmlosada";
        String password = "siisa1234";
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user, password);
            stmt = con.createStatement();

            String consultaSQL = "SELECT * FROM \"vw_profesionalDatosPersonales\" WHERE (numero_documento='" + dni + "')";
            ResultSet rs = stmt.executeQuery(consultaSQL);

            int contadorDeRegistros = 0;

            //  System.out.println(contadorDeRegistros + " - CODIGO: " + rs.getLong("codigo_de_profesional") + " - Nombre Completo: " + rs.getString("nombrecompleto") + " - dNI: " + rs.getString("numero_documento"));
          while(rs.next()){
            personaEntidad.setDni(Integer.parseInt(rs.getString("numero_documento")));
            personaEntidad.setApellido(rs.getString("apellido"));
            personaEntidad.setNombres(rs.getString("nombre"));
            personaEntidad.setCuil(rs.getLong("cuil"));
            personaEntidad.setSexo(rs.getString("sexo"));
            personaEntidad.setFechaDeNacimiento(rs.getDate("fecha_de_nacimiento"));
            personaEntidad.setDireccion(rs.getString("direccion"));
            personaEntidad.setTelefono(rs.getString("tel1"));
            personaEntidad.setEmail(rs.getString("email"));
          }
        } catch (SQLException exSQL) {
            exSQL.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            stmt.close();
            con.close();
        }
        System.out.println("registro encontrado"+personaEntidad.toString());
        return personaEntidad;
    }

}
