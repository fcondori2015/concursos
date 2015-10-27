/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import dominio.Concurso;
import dominio.Establecimiento;
import dominio.Expediente;
import dominio.Institucion;
import dominio.Persona;
import dominio.Postulante;
import dominio.Resolucion;
import dominio.Tribunal;
import dominio.TribunalJurado;
import dominio.UnidadDeOrganizacion;
import hibernate.dao.CargoDao;
import hibernate.dao.ExpedienteDao;
import hibernate.dao.ProfesionDao;
import hibernate.dao.ResolucionDao;
import hibernate.dao.TribunalDao;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.impl.CargoDaoImpl;
import hibernate.dao.impl.ExpedienteDaoImpl;
import hibernate.dao.impl.ProfesionDaoImpl;
import hibernate.dao.impl.ResolucionDaoImpl;
import hibernate.dao.impl.TribunalDaoImpl;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanResultado")
@ViewScoped
public class ResultadoBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Concurso nuevoConcurso;
    @ManagedProperty("#{beanPostulante}")
    private PostulantesBean beanPostulantes;
    @ManagedProperty("#{beanResolucion}")
    private ResolucionBean beanResolucion;
    @ManagedProperty("#{beanCargo}")
    private CargoBean beanCargo;
    @ManagedProperty("#{beanTribunal}")
    private TribunalBean beanTribunal;
    @ManagedProperty("#{beanExpediente}")
    private ExpedienteBean beanExpediente;

    public ResultadoBean() {

    }

    public void inicializarObjetosParaNuevoExpediente() {

        System.out.println("beanResultado ----->>> guardarEinicializar");
        ExpedienteDao expedienteDao = new ExpedienteDaoImpl();
        ResolucionDao resolucionDao = new ResolucionDaoImpl();
        TribunalDao tribunalDao = new TribunalDaoImpl();
        ProfesionDao profDao = new ProfesionDaoImpl();
        CargoDao cargoDao = new CargoDaoImpl();
        TribunalJuradoDao tribunalJuradoDao = new TribunalJuradoDaoImpl();
        beanTribunal.getListaJuradoNuevos().clear();
        beanPostulantes.getListaPostulantes().clear();
        beanCargo.getListaCargos().clear();
        beanResolucion.getListaResoluciones().clear();
        beanTribunal.getListaTribunales().clear();
        beanExpediente.setDatosValidos(false);
        beanResolucion.setDatosValidos(false);
        beanCargo.setDatosValidos(false);
        beanTribunal.setDatosValidos(false);
        beanPostulantes.setDatosValidos(false);

        beanExpediente.setExpedienteNuevo(new Expediente(expedienteDao.generarNuevoIdExpediente(), new UnidadDeOrganizacion(), 0, "", "", 0, ""));
        beanResolucion.setResolucionNueva(new Resolucion(resolucionDao.generarNuevoIdResolucion()));
        beanResolucion.getResolucionNueva().setTribunal(new Tribunal(tribunalDao.generarNuevoIdTribunal()));

        
       
        beanCargo.getCargoNuevo().setIdCargo(cargoDao.generarNuevoIdCargo());
        beanCargo.getCargoNuevo().setResolucion(new Resolucion());
        beanCargo.getCargoNuevo().setEsDesierto(true);
        beanTribunal.setTribunalNuevo(new Tribunal());
        beanTribunal.setJuradoNuevo(new TribunalJurado(tribunalJuradoDao.generarNuevoIdJurado(), new Institucion(), new Persona(), new Establecimiento(), beanTribunal.getTribunalNuevo(), "", true, ""));
        beanPostulantes.setNuevoPostulante(new Postulante());

    }

    public PostulantesBean getBeanPostulantes() {
        return beanPostulantes;
    }

    public void setBeanPostulantes(PostulantesBean beanPostulantes) {
        this.beanPostulantes = beanPostulantes;
    }

    public Concurso getNuevoConcurso() {
        return nuevoConcurso;
    }

    public void setNuevoConcurso(Concurso nuevoConcurso) {
        this.nuevoConcurso = nuevoConcurso;
    }

    public ResolucionBean getBeanResolucion() {
        return beanResolucion;
    }

    public void setBeanResolucion(ResolucionBean beanResolucion) {
        this.beanResolucion = beanResolucion;
    }

    public TribunalBean getBeanTribunal() {
        return beanTribunal;
    }

    public void setBeanTribunal(TribunalBean beanTribunal) {
        this.beanTribunal = beanTribunal;
    }

    public CargoBean getBeanCargo() {
        return beanCargo;
    }

    public void setBeanCargo(CargoBean beanCargo) {
        this.beanCargo = beanCargo;
    }

    public ExpedienteBean getBeanExpediente() {
        return beanExpediente;
    }

    public void setBeanExpediente(ExpedienteBean beanExpediente) {
        this.beanExpediente = beanExpediente;
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
