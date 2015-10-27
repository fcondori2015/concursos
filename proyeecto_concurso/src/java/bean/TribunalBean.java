/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Persona;
import dominio.TribunalJurado;
import dominio.Tribunal;
import dominio.Institucion;
import dominio.Establecimiento;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import bd.ConexionRefeps;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import hibernate.dao.PersonaDao;
import hibernate.dao.impl.PersonaDaoImpl;
import javax.faces.bean.ManagedProperty;
import dominio.Resolucion;
import hibernate.dao.EstablecimientoDao;
import hibernate.dao.InstitucionDao;
import hibernate.dao.PostulanteDao;
import hibernate.dao.impl.EstablecimientoDaoImpl;
import hibernate.dao.impl.InstitucionDaoImpl;
import hibernate.dao.impl.PostulanteDaoImpl;
import java.sql.SQLException;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanTribunal")
@ViewScoped
public class TribunalBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TribunalJurado juradoNuevo, juradoSeleccionado;
    private Tribunal tribunalNuevo;
    private String categoriaJurado, buscado, criterio;
    private Persona personaBuscada;
    private boolean datosValidos, banderaBtn, banBDSiisa;
    private List<Persona> listaResultadoBusquedaPersona, listaPersonas;
    private List<TribunalJurado> listaJuradoNuevos;
    private Resolucion resolucionSeleccionada;
    private List<Tribunal> listaTribunales;
    private int idResolucionSeleccionada;

    @ManagedProperty("#{beanResolucion}")
    private ResolucionBean beanResolucion;

    /**
     * Creates a new instance of TribunalBean
     */
    public TribunalBean() {

        TribunalJuradoDao tribunalJuradoDao = new TribunalJuradoDaoImpl();
        listaPersonas = new ArrayList<Persona>();
        listaResultadoBusquedaPersona = new ArrayList<Persona>();
        datosValidos = false;
        juradoNuevo = new TribunalJurado(tribunalJuradoDao.generarNuevoIdJurado(), new Institucion(), new Persona(), new Establecimiento(), new Tribunal(), "", true, "");
        banderaBtn = false;
        banBDSiisa = false;
        listaJuradoNuevos = new ArrayList<>();
        listaTribunales = new ArrayList<>();
        tribunalNuevo = new Tribunal();
        resolucionSeleccionada = new Resolucion();
    }

    public List<Tribunal> getListaTribunales() {
        return listaTribunales;
    }

    public void setListaTribunales(List<Tribunal> listaTribunales) {
        this.listaTribunales = listaTribunales;
    }

    //GETTERS && SETTERS
    public int getIdResolucionSeleccionada() {
        return idResolucionSeleccionada;
    }

    public void setIdResolucionSeleccionada(int idResolucionSeleccionada) {
        this.idResolucionSeleccionada = idResolucionSeleccionada;
    }

    public boolean isBanBDSiisa() {
        return banBDSiisa;
    }

    public void setBanBDSiisa(boolean banBDSiisa) {
        this.banBDSiisa = banBDSiisa;
    }

    public TribunalJurado getJuradoNuevo() {
        return juradoNuevo;
    }

    public Tribunal getTribunalNuevo() {
        return tribunalNuevo;
    }

    public void setTribunalNuevo(Tribunal tribunalNuevo) {
        this.tribunalNuevo = tribunalNuevo;
    }

    public void setJuradoNuevo(TribunalJurado juradoNuevo) {
        this.juradoNuevo = juradoNuevo;
    }

    public String getCategoriaJurado() {
        return categoriaJurado;
    }

    public void setCategoriaJurado(String categoriaJurado) {
        this.categoriaJurado = categoriaJurado;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public Persona getPersonaBuscada() {
        return personaBuscada;
    }

    public void setPersonaBuscada(Persona personaBuscada) {
        this.personaBuscada = personaBuscada;
    }

    public List<Persona> getListaResultadoBusquedaPersona() {
        return listaResultadoBusquedaPersona;
    }

    public void setListaResultadoBusquedaPersona(List<Persona> listaResultadoBusquedaPersona) {
        this.listaResultadoBusquedaPersona = listaResultadoBusquedaPersona;
    }

    public boolean isDatosValidos() {
        return datosValidos;
    }

    public void setDatosValidos(boolean datosValidos) {
        this.datosValidos = datosValidos;
    }

    public String getBuscado() {
        return buscado;
    }

    public void setBuscado(String buscado) {
        this.buscado = buscado;
    }

    public boolean isBanderaBtn() {
        return banderaBtn;
    }

    public void setBanderaBtn(boolean banderaBtn) {
        this.banderaBtn = banderaBtn;
    }

    public TribunalJurado getJuradoSeleccionado() {
        return juradoSeleccionado;
    }

    public void setJuradoSeleccionado(TribunalJurado juradoSeleccionado) {
        this.juradoSeleccionado = juradoSeleccionado;
    }

    public List<TribunalJurado> getListaJuradoNuevos() {
        return listaJuradoNuevos;
    }

    public void setListaJuradoNuevos(List<TribunalJurado> listaJuradoNuevos) {
        this.listaJuradoNuevos = listaJuradoNuevos;
    }

    public ResolucionBean getBeanResolucion() {
        return beanResolucion;
    }

    public void setBeanResolucion(ResolucionBean beanResolucion) {
        this.beanResolucion = beanResolucion;
    }

    public Resolucion getResolucionSeleccionada() {
        return resolucionSeleccionada;
    }

    public void setResolucionSeleccionada(Resolucion resolucionSeleccionada) {
        this.resolucionSeleccionada = resolucionSeleccionada;
    }

    //METODOS
    public void quitarJurado(TribunalJurado jurado) {
        System.out.println("TribunalBean.quitarJurado() => " + jurado.toString());
        for (TribunalJurado j : listaJuradoNuevos) {
            if (j.getIdTribunalJurado() == jurado.getIdTribunalJurado()) {
                listaJuradoNuevos.remove(jurado);
                break;
            }
        }
        if (listaJuradoNuevos.isEmpty()) {
            datosValidos = false;
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void onResolucionSelect() {
        System.out.println("ENTRO AL METODO ONRESOLUCIONSELECT");

        for (Resolucion resolucion : beanResolucion.getListaResoluciones()) {

            System.out.println("Id Resolicion: " + resolucion.getIdResolucion() + " Numero de resolucion: " + resolucion.getNumeroResolucion() + " Tribunal: " + resolucion.getTribunal().getIdTribunal());

        }
        for (Resolucion resolucion : beanResolucion.getListaResoluciones()) {
            if (resolucion.getIdResolucion() == idResolucionSeleccionada) {

                setResolucionSeleccionada(resolucion);
                break;
            }

        }
        System.out.println("RESOLUCION SELECCIONADA->>>>" + resolucionSeleccionada.getNumeroResolucion() + " ID Resolucion: " + resolucionSeleccionada.getIdResolucion() + " ID TRIBUNAL " + resolucionSeleccionada.getTribunal().getIdTribunal());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("tblTribunal");
    }

    public void buscarPersonaREFEPS() {
        banBDSiisa = false;
        ConexionRefeps conexionRefeps = new ConexionRefeps();
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            switch (criterio) {
                case "dni": {
                    if (conexionRefeps.buscarProfesionalRefepsDni(buscado).getDni() != null) {
                        banBDSiisa = true;
                        juradoNuevo.setPersona(conexionRefeps.buscarProfesionalRefepsDni(buscado));
                        System.out.println("beanTribunal->El DNI " + conexionRefeps.buscarProfesionalRefepsDni(buscado).getDni() + " Se encontró en la BDSIISA");
                        nuevoMensajeInfo("Registro de Concursos de Salud - JURADO ", "Se a encontrado el profesional con DNI :" + conexionRefeps.buscarProfesionalRefepsDni(buscado).getDni() + ".");

                    } else {
                        banBDSiisa = false;
                        juradoNuevo.setPersona(conexionRefeps.buscarProfesionalRefepsDni(buscado));
                        System.out.println("beanTribunal-> El DNI " + buscado + " No se encontró en la BDSIISA");
                    }
                    break;

                }

                case "nombre": {
                    listaResultadoBusquedaPersona = conexionRefeps.buscarProfesionalRefepsNombreCompleto(buscado);
                    if (listaResultadoBusquedaPersona.size() != 0) {
                        banBDSiisa = true;
                        System.out.println("beanTribunal->Se encontró en la BDSIISA");
                        context.execute("PF('dlgSeleccionJuradoRefeps').show();");
                        context.update("dlgSeleccionJuradoRefeps");
                    } else {
                        banBDSiisa = false;
                        System.out.println("beanTribunal-> No se encontró en la BDSIISA");
                    }
                    break;
                }

                case "apellido": {
                    listaResultadoBusquedaPersona = conexionRefeps.buscarProfesionalRefepsApellido(buscado);
                    if (listaResultadoBusquedaPersona.size() != 0) {
                        banBDSiisa = true;
                        System.out.println("beanTribunal->Se encontró en la BDSIISA");
                        context.execute("PF('dlgSeleccionJuradoRefeps').show();");
                        context.update("dlgSeleccionJuradoRefeps");
                    } else {
                        banBDSiisa = false;
                        System.out.println("beanTribunal-> No se encontró en la BDSIISA");
                    }
                    break;
                }
            }

        } catch (NullPointerException exNulo) {
            System.out.println("Nombre buscado: " + buscado);
            System.out.println(exNulo.getLocalizedMessage() + "\n" + exNulo.getMessage());

        } catch (Exception exGeneral) {
            exGeneral.printStackTrace();
            nuevoMensajeAlerta("Error al intentar buscar una persona" + buscado, exGeneral.getMessage());
        }
    }

    public void validarBuscador() {
        if (buscado.isEmpty()) {
            banderaBtn = false;

        } else {
            banderaBtn = true;
        }
    }

    public void buscarPorCriterio() throws SQLException {
        PersonaDao personaDao = new PersonaDaoImpl();
        Persona personaEncontrada = new Persona();
        RequestContext context = RequestContext.getCurrentInstance();
        try {

            switch (criterio) {
                case "dni": {
                    buscarPersonaREFEPS();
                    if (!banBDSiisa) {
                        System.out.println("beanTribunal-->buscarPorCriterio DNI BD Concurso Personas.");
                        personaEncontrada = personaDao.buscarPorDni(Integer.parseInt(buscado));
                        if (personaEncontrada != null) {
                            juradoNuevo.setPersona(personaEncontrada);
                            nuevoMensajeInfo("Registro de Concursos de Salud - JURADO", "Se a encontrado la persona con el DNI :" + buscado + ".");
                        } else {
                            juradoNuevo.setPersona(personaEncontrada);
                            nuevoMensajeAlerta("Registro de Concursos de Salud - JURADO", "No se a encontrado la persona con el DNI :" + buscado + ".");
                        }

                    }
                    break;
                }
                case "nombre": {
                    buscarPersonaREFEPS();
                    if (!banBDSiisa) {
                        listaResultadoBusquedaPersona = personaDao.buscarPorNombre(buscado);

                        if (listaResultadoBusquedaPersona != null) {
                            context.execute("PF('dlgSeleccionJuradoRefeps').show();");
                            context.update("dlgSeleccionJuradoRefeps");
                        } else {
                            juradoNuevo.setPersona(personaEncontrada);
                            nuevoMensajeAlerta("Registro de Concursos de Salud - JURADO", "No se a encontrado la persona con el Nombre :" + buscado + ".");
                        }

                    }
                    break;
                }
                case "apellido": {
                    buscarPersonaREFEPS();
                    if (!banBDSiisa) {
                        listaResultadoBusquedaPersona = personaDao.buscarPorApellido(buscado);

                        if (listaResultadoBusquedaPersona != null) {
                            context.execute("PF('dlgSeleccionJuradoRefeps').show();");
                            context.update("dlgSeleccionJuradoRefeps");
                        } else {
                            juradoNuevo.setPersona(personaEncontrada);
                            nuevoMensajeAlerta("Registro de Concursos de Salud - JURADO", "No se a encontrado la persona con el Apellido :" + buscado + ".");
                        }

                    }
                    break;
                }
            }
        } catch (NumberFormatException exFormato) {
            nuevoMensajeAlerta("Registro de Concursos de Salud - Error", "Ingrese DNI valido para buscar");
        }
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public void seleccionarPersona(SelectEvent event) {
        Persona personaSelec = (Persona) event.getObject();
        PersonaDao personaDao = new PersonaDaoImpl();
        Persona persAux = personaDao.buscarPorDni(personaSelec.getDni());
        if (!listaJuradoNuevos.isEmpty()) {
            if (persAux == null) {
                personaSelec.setIdPersona(personaDao.generarIdNuevoPersona());
                System.out.println("TribunalBean.seleccionarPersona() => " + personaSelec.toString());
            } else {
                personaSelec = persAux;
            }
        } else {
            //En caso de que sea el primer registro de la lista verificamos su
            //existencia en la BD
            if (persAux == null) {
                personaSelec.setIdPersona(personaDao.generarIdNuevoPersona());
            } else {
                personaSelec = persAux;
            }

        }
        juradoNuevo.setPersona(personaSelec);
        System.out.println("TribunalBean.seleccionarPersona() => Se a seleccionado la " + juradoNuevo.getPersona().toString());

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgSeleccionJuradoRefeps').hide();");

    }

    public void seleccionarInstitucion() {
        InstitucionDao institucionDao = new InstitucionDaoImpl();
        if (juradoNuevo.getInstitucion().getIdInstitucion() != 0 || juradoNuevo.getEstablecimiento().getIdEstablecimiento() != 0) {
            juradoNuevo.setInstitucion(institucionDao.getInstitucion(juradoNuevo.getInstitucion().getIdInstitucion()));
            System.out.println("TribunalBean.seleccionarInstitucion(" + juradoNuevo.getInstitucion().getIdInstitucion() + ") => Se a seleccionado el Instituto (" + juradoNuevo.getInstitucion().getIdInstitucion() + ") " + juradoNuevo.getInstitucion().getNombreInstitucion());
        } else {
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Seleccione una institucion a la que representa");
        }
    }

    public void seleccionarEstablecimiento() {
        EstablecimientoDao establecDao = new EstablecimientoDaoImpl();
        if (juradoNuevo.getInstitucion().getIdInstitucion() != 0 || juradoNuevo.getEstablecimiento().getIdEstablecimiento() != 0) {
            juradoNuevo.setEstablecimiento(establecDao.getEstablecimientoById(juradoNuevo.getEstablecimiento().getIdEstablecimiento()));
            System.out.println("TribunalBean.seleccionarEstablecimiento(" + juradoNuevo.getEstablecimiento().getIdEstablecimiento() + ") => Se a seleccionado el Establecimiento (" + juradoNuevo.getEstablecimiento().getIdEstablecimiento() + ") " + juradoNuevo.getEstablecimiento().getNombre());
        } else {
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Seleccione un establecimiento al que representa");
        }
    }

    public void guardarJuradoNuevo() {

        try {
            TribunalJuradoDao jurDao = new TribunalJuradoDaoImpl();
            PersonaDao persDao = new PersonaDaoImpl();

            //Controla por el Dni si existe la persona cargada en la bd concurso.
            if (!persDao.existeDniPersona(juradoNuevo.getPersona())) {

                //en caso de que no exista lo seteamos y guardamos
                Persona personaNueva = juradoNuevo.getPersona();
                personaNueva.setIdPersona(persDao.generarIdNuevoPersona());
                personaNueva.setApellido(juradoNuevo.getPersona().getApellido().toUpperCase());
                personaNueva.setNombres(juradoNuevo.getPersona().getNombres().toUpperCase());
                personaNueva.setDireccion(juradoNuevo.getPersona().getDireccion().toUpperCase());
                persDao.insertar(personaNueva);
            }

            System.out.println("beanTribunalBean====>Jurado en Resolucion con id :" + resolucionSeleccionada.getIdResolucion() + " con tribunal id " + resolucionSeleccionada.getTribunal().getIdTribunal());

            //Agrega el jurado nuevo a la lista de jurados.
            if (!listaJuradoNuevos.contains(juradoNuevo)) {

                //Seteamos el establecimiento e institucion
                EstablecimientoDao establecimientoDao = new EstablecimientoDaoImpl();
                juradoNuevo.setEstablecimiento(establecimientoDao.getEstablecimientoByCodigoSiisa(juradoNuevo.getEstablecimiento().getCodigoSiisa()));

                InstitucionDao institucionDao = new InstitucionDaoImpl();
                juradoNuevo.setInstitucion(institucionDao.getInstitucion(juradoNuevo.getInstitucion().getIdInstitucion()));

                juradoNuevo.setTribunal(resolucionSeleccionada.getTribunal());

                //Guardamos
                System.out.println("TribunalBean.guardarJuradoNuevo() => Guardando " + juradoNuevo.toString());
                listaJuradoNuevos.add(juradoNuevo);
            }

            //Inicializa el jurado Nuevo y el seleccionado
            juradoSeleccionado = new TribunalJurado();
            juradoNuevo = new TribunalJurado(jurDao.generarNuevoIdJurado() + listaJuradoNuevos.size(), new Institucion(), new Persona(persDao.generarIdNuevoPersona(), "M"), new Establecimiento(), new Tribunal(), "", false, "");

            buscado = "";
        } catch (Exception ex1) {
            nuevoMensajeAlerta("Error! al guardar el jurado", ex1.getMessage());
            ex1.printStackTrace();
        }

    }

    public void limpiarDialogoNuevoJurado() {
        System.out.println("beanTribunal -> limpiarDialogoNuevoJurado--");
        PersonaDao persDao = new PersonaDaoImpl();
        buscado = "";
        juradoSeleccionado = new TribunalJurado();
        juradoNuevo = new TribunalJurado(juradoNuevo.getIdTribunalJurado() + 1, new Institucion(), new Persona(persDao.generarIdNuevoPersona(), "M"), new Establecimiento(), resolucionSeleccionada.getTribunal(), "", false, "");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgNuevoJurado').show()");
        context.update("dlgNuevoJurado");
    }

    public void guardarTribunalNuevo2() {

        try {
            PostulanteDao postulanteDao = new PostulanteDaoImpl();
            TribunalJuradoDao tribDao = new TribunalJuradoDaoImpl();
            //Seteamos la cantidad de jurados para el nuevoTribunal

            for (Resolucion resolucion : beanResolucion.getListaResoluciones()) {

                tribunalNuevo.setIdTribunal(resolucion.getTribunal().getIdTribunal());

                listaTribunales.add(tribunalNuevo);

                tribunalNuevo = new Tribunal();
            }

            int cont = 0;
            for (TribunalJurado jur : listaJuradoNuevos) {

                jur.setIdTribunalJurado(tribDao.generarNuevoIdJurado() + cont);
                cont++;
            }

            //Determina la cantidad de jurados por tribunal.
            for (Tribunal tribunal : listaTribunales) {
                int contJurados = 0;
                for (TribunalJurado jurado : listaJuradoNuevos) {

                    if (jurado.getTribunal().getIdTribunal() == tribunal.getIdTribunal()) {

                        contJurados++;
                    }
                }
                tribunal.setCantidadMiembros((short)contJurados);
            }

            setListaFinalJurados(listaJuradoNuevos);
            setListaFinalTribunales(listaTribunales);
//            for (Resolucion resolucion : getListaFinalResoluciones()) {
//                if (resolucionSeleccionada.getIdResolucion() == resolucion.getIdResolucion()) {
//                    tribunalNuevo = resolucion.getTribunal();
//                    tribunalNuevo.setCantidadMiembros(Short.parseShort(String.valueOf(getListaJuradoNuevos().size())));
//                    break;
//                }
//            }
//
//            //seteamos el tribunal en la resolucion Seleccionada
//            //Tengo que cambiar la forma de recorrer la lista en memoria de resolucion
//            resolucionSeleccionada.setTribunal(tribunalNuevo);
//            for (Resolucion resolucion : getListaFinalResoluciones()) {
//                if (resolucionSeleccionada.getNumeroResolucion().equalsIgnoreCase(resolucion.getNumeroResolucion())) {
//                    //Primero obtenemos el elemento resolucion de la lista y despues
//                    //le seteamos el tribunal
//                    getListaFinalResoluciones().get(getListaFinalResoluciones().indexOf(resolucion)).setTribunal(tribunalNuevo);
//
//                    break;
//                }
//            }

            //getListaFinalResoluciones().get(resolucionSeleccionada.getIdResolucion()).setTribunal(tribunalNuevo);
            //Guardamos el tribunal en la lista final de tribunales
//            getListaFinalTribunales().add(tribunalNuevo);
//            for (TribunalJurado jurado : listaJuradoNuevos) {
//                jurado.setTribunal(tribunalNuevo);
////                if (getListaFinalJurados().isEmpty()) {
////                    jurado.setIdTribunalJurado(postulanteDao.generarIdNuevoPostulante());
////                } else {
////                    jurado.setIdTribunalJurado(getListaFinalJurados().get(getListaFinalJurados().size() - 1).getIdTribunalJurado() + 1);
////                }
//                getListaFinalJurados().add(jurado);
//            }
            datosValidos = true;
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Se a guardado el tribunal con los postulantes seleccionados.");
            pasarVistaDePestania();
        } catch (Exception exGeneral) {
            nuevoMensajeAlerta("Error! al guardar el tribunal nuevo", exGeneral.getMessage());
            exGeneral.printStackTrace();
        }
    }

}
