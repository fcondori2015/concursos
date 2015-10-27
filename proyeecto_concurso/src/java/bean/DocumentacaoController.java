/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import java.io.InputStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author favio
 */
@ManagedBean(name = "beanDocumentacion")
@SessionScoped
public class DocumentacaoController implements Serializable {

    private StreamedContent file;
    private FacesContext ctx;
    private ServletContext request;
    private File archCSV;
    private FileInputStream fisArch;
    private byte[] bytes;
    private int leer = 0;
    private String nombreArchivo;
    private DefaultStreamedContent dowload;

    public DefaultStreamedContent getDowload() throws Exception {
        return dowload;
    }

    public void setDowload(DefaultStreamedContent dowload) {
        this.dowload = dowload;
    }

    public FacesContext getCtx() {
        return ctx;
    }

    public void setCtx(FacesContext ctx) {
        this.ctx = ctx;
    }

    public ServletContext getRequest() {
        return request;
    }

    public void setRequest(ServletContext request) {
        this.request = request;
    }

    public File getAchCSV() {
        return archCSV;
    }

    public void setAchCSV(File achCSV) {
        this.archCSV = achCSV;
    }

    public FileInputStream getFisArch() {
        return fisArch;
    }

    public void setFisArch(FileInputStream fisArch) {
        this.fisArch = fisArch;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public File getArchCSV() {
        return archCSV;
    }

    public void setArchCSV(File archCSV) {
        this.archCSV = archCSV;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getLeer() {
        return leer;
    }

    public void setLeer(int leer) {
        this.leer = leer;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public DocumentacaoController() throws FileNotFoundException, IOException {

      //  System.out.println("CARGA EL ARCHIVO-->> beanDocumentaicon");
     //   InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/home/favio/Descargas/foto.jpg");
       //   InputStream stream = new FileInputStream("/home/favio/Descargas/foto.jpg");
      //  file = new DefaultStreamedContent(stream, "image/jpg", "foto.jpg");
//        ctx = FacesContext.getCurrentInstance();
//        request = (ServletContext) ctx.getExternalContext().getContext();
//        archCSV = new File("/home/favio/Descargas/Expedientes.csv/");
//        fisArch = new FileInputStream(archCSV);
//        bytes = new byte[1000];
//
//        if (!ctx.getResponseComplete()) {
//
//            nombreArchivo = archCSV.getName();

//            String contentType = "application/csv";
//            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
//
//            response.setContentType(contentType);
//            response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo + "\"");
//            ServletOutputStream out = response.getOutputStream();
//
//            System.out.println("leer " + leer);
//            while ((leer = fisArch.read(bytes)) != -1) {
//                out.write(bytes, 0, leer);
//            }
//
//            out.flush();
//            out.close();
//            System.out.println("\nDescargado...\n");
//            ctx.responseComplete();
//        }
    //    System.out.println("Nombre del archivo creado :" + file.getName());

    }

    public void cargandoArchivo() throws FileNotFoundException {

////        System.out.println("CARGA EL ARCHIVO-->> beanDocumentaicon");
////
////        InputStream stream = new FileInputStream("/home/favio/Descargas/foto.jpg");
////        file = new DefaultStreamedContent(stream, "image/jpg","foto.jpg");
//        File file2 = new File("/home/favio/Descargas/foto.jpg");
//        InputStream input = new FileInputStream(file2);
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        setDowload(new DefaultStreamedContent(input, externalContext.getMimeType(file2.getName()), file2.getName()));
//        System.out.println("Nombre del archivo creado :" + file2.getName()+"--->beanDocumnentacion CargandoArchivo");

    }

}
