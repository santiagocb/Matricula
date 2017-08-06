

package com.udea.controlador;

import com.udea.logica.MatriculaFacadeLocal;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author santiago
 */
@WebServlet("/images/*")
public class ImageBean extends HttpServlet{

    @EJB
    private MatriculaFacadeLocal matriculaFacade;  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_estudiante = String.valueOf(request.getPathInfo().substring(1)); // Gets string that goes after "/images/".
   
        response.setContentType("image/jpg");

            OutputStream out = response.getOutputStream();

            out.write(matriculaFacade.findbyIDE(id_estudiante).getImagen());

            out.close();
    }
    
    
    
    /*public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            return new DefaultStreamedContent(new ByteArrayInputStream(matriculaFacade.findbyIDE(id).getImagen()));
        }
    }*/
}
