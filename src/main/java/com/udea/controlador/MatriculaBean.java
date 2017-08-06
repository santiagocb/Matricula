/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controlador;

import com.udea.logica.MatriculaFacadeLocal;
import com.udea.modelo.Matricula;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author santiago
 */
public class MatriculaBean {

    @EJB
    private MatriculaFacadeLocal matriculaFacade;

    private String id_estudiante;
    private String tipo_id;
    private String nombre;
    private String email;
    private String materias;
    private String [] materiasArreglo;    
    private UIComponent boton;
    private byte[] imagen;
    private boolean deshabilitar = true;
    private UploadedFile file;
    private StreamedContent imagenF;
    
    
    public MatriculaBean() {              
    }
    
    public void ArregloAString(String [] materiasArreglo){
        int i = 0;
        this.materias = "";
        while(i < materiasArreglo.length){
            this.materias = this.materias + materiasArreglo[i];
            i++;
        } 
    }
    
    public int getMatricula(int id){
        return matriculaFacade.find(id).getId();
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getNombre() {
        return nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public UIComponent getBoton() {
        return boton;
    }

    public void setBoton(UIComponent boton) {
        this.boton = boton;
    }

    public String[] getMateriasArreglo() {
        return materiasArreglo;
    }

    public void setMateriasArreglo(String[] materiasArreglo) {
        this.materiasArreglo = materiasArreglo;
    }

    public boolean isDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(boolean deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }        

    public StreamedContent getImagenF() {
        return imagenF;
    }

    public void setImagenF(StreamedContent imagenF) {
        this.imagenF = imagenF;
    }
        
    public String guardar() throws IOException{
        Matricula p = new Matricula();
        p.setIdEstudiante(id_estudiante);
        p.setTipoId(tipo_id);
        p.setNombre(nombre);
        p.setEmail(email);
        ArregloAString(materiasArreglo);
        p.setMaterias(materias);        
        InputStream filecontent = file.getInputstream();
        p.setImagen(IOUtils.toByteArray(filecontent));
        matriculaFacade.create(p);        
        return "MatriculaCreate";
    }
    
    //Para internacionalizacion
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    public Locale getLocale(){
        return locale;        
    }
    
    public String getLanguage(){
        return locale.getLanguage();
    }
    
    public void changeLanguage(String language){
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
    
    
    
}
