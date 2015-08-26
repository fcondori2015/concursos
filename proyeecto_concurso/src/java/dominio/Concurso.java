package dominio;
// Generated 26/08/2015 11:54:39 by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Concurso generated by hbm2java
 */
public class Concurso  implements java.io.Serializable {


     private int idConcurso;
     private Resolucion resolucion;
     private Date fechaConcurso;
     private String resultado;
     private String resolucionNumeroConcurso;

    public Concurso() {
    }

	
    public Concurso(int idConcurso, String resultado) {
        this.idConcurso = idConcurso;
        this.resultado = resultado;
    }
    public Concurso(int idConcurso, Resolucion resolucion, Date fechaConcurso, String resultado, String resolucionNumeroConcurso) {
       this.idConcurso = idConcurso;
       this.resolucion = resolucion;
       this.fechaConcurso = fechaConcurso;
       this.resultado = resultado;
       this.resolucionNumeroConcurso = resolucionNumeroConcurso;
    }
   
    public int getIdConcurso() {
        return this.idConcurso;
    }
    
    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }
    public Resolucion getResolucion() {
        return this.resolucion;
    }
    
    public void setResolucion(Resolucion resolucion) {
        this.resolucion = resolucion;
    }
    public Date getFechaConcurso() {
        return this.fechaConcurso;
    }
    
    public void setFechaConcurso(Date fechaConcurso) {
        this.fechaConcurso = fechaConcurso;
    }
    public String getResultado() {
        return this.resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public String getResolucionNumeroConcurso() {
        return this.resolucionNumeroConcurso;
    }
    
    public void setResolucionNumeroConcurso(String resolucionNumeroConcurso) {
        this.resolucionNumeroConcurso = resolucionNumeroConcurso;
    }




}


