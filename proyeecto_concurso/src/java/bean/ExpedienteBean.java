/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Expediente;
import dominio.UnidadDeOrganizacion;
import hibernate.dao.UnidadDeOrganizacionDao;
import hibernate.dao.impl.UnidadDeOrganizacionDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanExpediente")
@ViewScoped
public class ExpedienteBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //ATRIBUTOS
    private Expediente expedienteNuevo;//Expediente que se va a guardar
    private List<UnidadDeOrganizacion> listaUnidadDeOrganizacions; //lista que se utiliza para cargar el combo con las areas

    //GETTERS & SETTERS
    public Expediente getExpedienteNuevo() {
        return expedienteNuevo;
    }

    public void setExpedienteNuevo(Expediente expedienteNuevo) {
        this.expedienteNuevo = expedienteNuevo;
    }

    public List<UnidadDeOrganizacion> getListaUnidadDeOrganizacions() {
        return listaUnidadDeOrganizacions;
    }

    public void setListaUnidadDeOrganizacions(List<UnidadDeOrganizacion> listaUnidadDeOrganizacions) {
        this.listaUnidadDeOrganizacions = listaUnidadDeOrganizacions;
    }

    /**
     * CONSTRUCTOR VACIO Creates a new instance of ExpedienteBean
     */
    public ExpedienteBean() {
        listaUnidadDeOrganizacions = new ArrayList<UnidadDeOrganizacion>();
        expedienteNuevo = new Expediente("", new UnidadDeOrganizacion(), 0, Integer.getInteger(""), "", "", Integer.getInteger(""));
        System.out.println("ExpedienteBean.ExpedienteBean() => " + expedienteNuevo.toString());
        refreshListas();
    }

    //METODOS
    public void refreshListas() {
        System.out.println("ExpedienteBean.refreshListas() => Obteniendo las unidades de Organizacion");
        UnidadDeOrganizacionDao unidadDao = new UnidadDeOrganizacionDaoImpl();
        listaUnidadDeOrganizacions = unidadDao.getAll();
    }

    /**
     *
     * Método que se ejecuta cuando se apreta el boton de guardar en la pestaña
     * de expedientes. Setea el expediente asignandole una unidad de
     * organizacion y un numero de expediente formateado
     *
     */
    public void validarExpedienteTab() {

        try {
            for (UnidadDeOrganizacion unidad : listaUnidadDeOrganizacions) {
                if (unidad.getCodigoUnidadDeOrganizacion() == expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion()) {
                    expedienteNuevo.setUnidadDeOrganizacion(unidad);
                    break;
                }
            }

            //Seteamos el numero para que tenga un formato de 6 cifras
            String numString = String.valueOf(expedienteNuevo.getNumero());
            char[] vectorCaracteres = new char[numString.length()];
            numString.getChars(0, numString.length(), vectorCaracteres, 0);

            expedienteNuevo.setNumeroExpediente(expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion() + "-" + expedienteNuevo.getNumero() + "/" + expedienteNuevo.getAnio());
            nuevoMensajeInfo("Expediente " + expedienteNuevo.getIdExpediente(), "Numero de Expediente: " + expedienteNuevo.getNumeroExpediente() + "\nSituación: " + expedienteNuevo.getSituacion() + "\nRégimen: " + expedienteNuevo.getRegimen() + "\nEstablecimiento: " + expedienteNuevo.getUnidadDeOrganizacion().getNombreUnidad());

        } catch (NullPointerException ex1) {
            nuevoMensajeAlerta("Error! " + ex1.getMessage(), ex1.getLocalizedMessage());
        }

    }

    public void guardarExpediente() {
        try {
            for (UnidadDeOrganizacion unidad : listaUnidadDeOrganizacions) {
                if (unidad.getCodigoUnidadDeOrganizacion() == expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion()) {
                    expedienteNuevo.setUnidadDeOrganizacion(unidad);
                    break;
                }
            }
            expedienteNuevo.setNumeroExpediente(expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion() + "-" + expedienteNuevo.getNumero() + "/" + expedienteNuevo.getAnio());

            nuevoMensajeInfo("Registro de Concursos de Salud - EXPEDIENTE", "Número: " + expedienteNuevo.getNumeroExpediente() + "\nRégimen: " + expedienteNuevo.getRegimen() + "\nSituación: " + expedienteNuevo.getSituacion());
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }

    }

}