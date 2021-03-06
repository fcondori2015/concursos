/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Expediente;
import dominio.Resolucion;
import dominio.Tribunal;
import hibernate.dao.ResolucionDao;
import hibernate.dao.TribunalDao;
import hibernate.dao.impl.ResolucionDaoImpl;
import hibernate.dao.impl.TribunalDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanResolucion")
@ViewScoped
public class ResolucionBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //ATRIBUTOS
    private boolean banderaModificacionParcial = false;
    private boolean banderaProrroga = false;
    private Resolucion resolucionNueva;
    private List<Resolucion> listaResoluciones;
    private String dependenciaNumeroResolucion;
    private boolean datosValidos;//Bandera que se referencia a la vista para habilitar la pestaña siguiente
    private int anioNumeroResolucion;

    @ManagedProperty("#{beanExpediente}")
    private ExpedienteBean beanExpediente;

    //GETTERS & SETTERS
    public ExpedienteBean getBeanExpediente() {
        return beanExpediente;
    }

    public void setBeanExpediente(ExpedienteBean beanExpediente) {
        this.beanExpediente = beanExpediente;
    }

    public boolean isBanderaModificacionParcial() {
        return banderaModificacionParcial;
    }

    public void setBanderaModificacionParcial(boolean banderaModificacionParcial) {
        this.banderaModificacionParcial = banderaModificacionParcial;
    }

    public boolean isBanderaProrroga() {
        return banderaProrroga;
    }

    public void setBanderaProrroga(boolean banderaProrroga) {
        this.banderaProrroga = banderaProrroga;
    }

    public Resolucion getResolucionNueva() {
        return resolucionNueva;
    }

    public void setResolucionNueva(Resolucion resolucionNueva) {
        this.resolucionNueva = resolucionNueva;
    }

    public List<Resolucion> getListaResoluciones() {
        return listaResoluciones;
    }

    public void setListaResoluciones(List<Resolucion> listaReoluciones) {
        this.listaResoluciones = listaReoluciones;
    }

    public String getDependenciaNumeroResolucion() {
        return dependenciaNumeroResolucion;
    }

    public void setDependenciaNumeroResolucion(String dependenciaNumeroResolucion) {
        this.dependenciaNumeroResolucion = dependenciaNumeroResolucion;
    }

    public boolean isDatosValidos() {
        return datosValidos;
    }

    public void setDatosValidos(boolean datosValidos) {
        this.datosValidos = datosValidos;
    }

    public int getAnioNumeroResolucion() {
        return anioNumeroResolucion;
    }

    public void setAnioNumeroResolucion(int anioNumeroResolucion) {
        this.anioNumeroResolucion = anioNumeroResolucion;
    }

//    public List<Resolucion> obtenerTodasResoluciones() {
//        ResolucionDao resolucionDao = new ResolucionDaoImpl();
//        return resolucionDao.getAll();
//    }
    /**
     * Creates a new instance of ResolucionBean
     */
    public ResolucionBean() {
        ResolucionDao resolucionDao = new ResolucionDaoImpl();
        TribunalDao tribunalDao = new TribunalDaoImpl();

        banderaModificacionParcial = false;
        banderaProrroga = false;
        resolucionNueva = new Resolucion(resolucionDao.generarNuevoIdResolucion());
        resolucionNueva.setTribunal(new Tribunal(tribunalDao.generarNuevoIdTribunal()));
        listaResoluciones = new ArrayList<Resolucion>();
        datosValidos = false;

    }

    //METODOS
    public void cambiarEstadoModificacion() {
        if (banderaModificacionParcial) {
            banderaModificacionParcial = false;
        } else {
            banderaModificacionParcial = true;
        }
    }

    public void cambiarEstadoProrroga() {
        if (banderaProrroga) {
            banderaProrroga = false;
        } else {
            banderaProrroga = true;
        }
    }

    public void onDateSelect(SelectEvent event) {

    }

    public void inicializarResolucionNueva() {
        banderaModificacionParcial = false;
        banderaProrroga = false;

        //En caso de que se guarden mas de una resolucion, seteamos
        //que hace referencia al expediente de la resolucion
        Expediente expedienteAuxiliar = resolucionNueva.getExpediente();

        resolucionNueva = new Resolucion(resolucionNueva.getIdResolucion() + 1, new Tribunal(resolucionNueva.getTribunal().getIdTribunal() + 1));
        resolucionNueva.setExpediente(expedienteAuxiliar);
      
        
        
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("dlgNuevaResolucion");
    }

    public void guardarResolucion() {

        try {
            String numeroResolucion = resolucionNueva.getNumeroResolucion();
            resolucionNueva.setNumeroResolucion(resolucionNueva.formatearNumero(numeroResolucion) + "-" + dependenciaNumeroResolucion + "/" + anioNumeroResolucion);

            if (!listaResoluciones.contains(resolucionNueva.getNumeroResolucion())) {
                resolucionNueva.setModificacion(banderaModificacionParcial);

                resolucionNueva.setProrroga(banderaProrroga);

                resolucionNueva.setExpediente(beanExpediente.getExpedienteNuevo());
                listaResoluciones.add(resolucionNueva);


                nuevoMensajeInfo("Registro de Concursos de Salud - RESOLUCIÓN", "NºResolucion: " + resolucionNueva.getNumeroResolucion()
                        +"numero de id Expediente: "+resolucionNueva.getExpediente().getIdExpediente()+ " guardada éxitosamente");

                inicializarResolucionNueva();
            } else {
                nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "La resolución " + numeroResolucion + " ya se encuentra presente.");
            }

        } catch (Exception ex1) {
            ex1.printStackTrace();
            nuevoMensajeAlerta("Error al guardar la resolucion", " " + ex1.toString());
        }
    }

    public void guardarListaResoluciones() {

        //ResolucionDao resolucionDao = new ResolucionDaoImpl();
           try {
            int cont=0;
            //Genero los Id para cada cada resolucion
            ResolucionDao resDao= new ResolucionDaoImpl();
            TribunalDao tribDao=new TribunalDaoImpl();
            for (Resolucion resolucion: listaResoluciones){
                  cont++; 
                resolucion.setIdResolucion(resDao.obtenerUltimaResolucion().getIdResolucion()+cont);
                resolucion.setTribunal(new Tribunal(tribDao.obtenerUltimoTribunal().getIdTribunal()+cont));
            
            }
            setListaFinalResoluciones(listaResoluciones);
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Se a guardado la lista de resoluciones");
            pasarVistaDePestania();
            datosValidos = true;
            System.out.println("beanResolucion.guardarListaResolucio==>Resoluciones cargadas en la listaResoluciones :");
            
          
        } catch (HibernateException exHibernate) {
            nuevoMensajeAlerta("Error" + exHibernate.getMessage(), exHibernate.getLocalizedMessage());
        } catch (Exception exGeneral) {
            exGeneral.printStackTrace();
        }

    }

    public void quitarResolucion(Resolucion resolucionBorrar) {
        for (Resolucion resolucion : listaResoluciones) {
            if (resolucion.getIdResolucion() == resolucionBorrar.getIdResolucion()) {
                listaResoluciones.remove(resolucion);
                break;
            }
        }
        if (listaResoluciones.isEmpty()) {
            datosValidos = false;
        }
    }

}
