package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlUsuario;
import controles.ControlAdministrador;
import controles.ControlPersona;

  public class InterfazUsuarioN extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlUsuario cu,cu2;
  ControlAdministrador ca;
  ControlPersona cp;
  String cuentaPer, cuentaPri, cuentaPriA, cuentaUsu, contra;
  int priviU;

   public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
    thisResponse = response;
    thisRequest = request;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();
    //Pagina Web de respuesta
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<META http-equiv=Content-Type content=\"text/html\">");
    out.println("<link rel=stylesheet href=css/bootstrap.min.css>");
    out.println("<link rel=stylesheet href=style.css>");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<TITLE>Viculacion Academica</TITLE>");
    out.println("<h2>Viculacion Academica</h2>");
    out.println("<h3>Dar de alta un ususario</h3>");
    //out.println("<p>Esta opci�n no esta disponible por el momento.</p>");

  String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      IniciarUsuarioNuevo();
    }else if(operacion.equals("usuarioNuevo")){
       validarDatos();
    }
  }

 public void IniciarUsuarioNuevo(){

  out.println("<p>Ingrese los datos para dar de alta un usario</p>");
  out.println("<form method=\"GET\" action=\"UsuarioN\">");
  out.println("<input type=\"hidden\" name=\"operacion\" value=\"usuarioNuevo\"/>");
  out.println("<div class=row>");
  out.println("<div class=\"col-xs-2\">");
  out.println("<p><input type=\"text\" name=\"cuentaPrivi\"class=\"form-control\"placeholder=\"Ingrese su ID\"></p>");
  out.println("</div>");
  out.println("</div>");
  out.println("<div class=row>");
  out.println("<div class=\"col-xs-2\">");
  out.println("<p><input type=\"text\" name=\"cuentaPersona\"class=\"form-control\"placeholder=\"Ingrese ID de la persona\"></p>");
  out.println("</div>");
  out.println("</div>");
  out.println("<div class=row>");
  out.println("<div class=\"col-xs-2\">");
  out.println("<p><input type=\"text\" name=\"cuentaUsuario\"class=\"form-control\"placeholder=\"Ingrese ID de nuevo usario\"></p>");
  out.println("</div>");
  out.println("</div>");
  out.println("<div class=row>");
  out.println("<div class=\"col-xs-2\">");
  out.println("<p><input type=\"text\" name=\"contraU\"class=\"form-control\"placeholder=\"Ingrese contrasena\"></p>");
  out.println("</div>");
  out.println("</div>");
  out.println("<div class=row>");
  out.println("<div class=\"col-xs-2\">");
  out.println("<p><input type=\"text\" name=\"privi\"class=\"form-control\"placeholder=\" Privilegio (si = 1, no = 0)\"></p>");
  out.println("</div>");
  out.println("</div>");
  out.println("<button class=\"btn btn-info \"type=\"submit\">Enviar</button>");
  out.println("</form>");

  out.println("<form method=\"GET\" action=\"menuAdmin.html\">");
  out.println("<button class=\"btn btn-danger \"type=\"submit\">Cancelar</button>");
  out.println("</form>");

  out.println("</BODY>");
  out.println("</HTML>");
}

public void validarDatos(){
    ca = new ControlAdministrador();
    cu2 = new ControlUsuario();
    cu = new ControlUsuario();
    cp = new ControlPersona();
    cuentaPri = thisRequest.getParameter("cuentaPrivi").trim();
    cuentaPriA = thisRequest.getParameter("cuentaPrivi").trim();
    cuentaUsu = thisRequest.getParameter("cuentaUsuario").trim();
    cuentaPer = thisRequest.getParameter("cuentaPersona").trim();
    contra = thisRequest.getParameter("contraU").trim();
    priviU= Integer.parseInt(thisRequest.getParameter("privi").trim());

    if(!cu.validarIDUsuario(cuentaUsu) && cp.validarIDPersona(cuentaPer)&& ca.validarIDAdministrador(cuentaPriA)){
      out.println("<p>Usuario registrado exitosamente</p>");
      out.println("<p>ID de usuario: " + cuentaUsu + " </p>");
      out.println("<p>Contrasena de usuario: " + contra + " </p>");
      if(ca.validarIDAdministrador(cuentaPriA)){
          out.println("<p>Agregado por: " + cuentaPriA + " </p>");
      }

      if(ca.validarIDAdministrador(cuentaPriA)){
        cu.agregarUsuarioDB(cuentaPer, cuentaUsu, cuentaPriA, contra, priviU);
      }
      else{
        cu.agregarUsuarioDB(cuentaPer, cuentaUsu, cuentaPri, contra, priviU);
      }

      out.println("<form method=\"GET\" action=\"menuAdmin.html\">");
      out.println("<button class=\"btn btn-danger \"type=\"submit\">Cancelar</button>");

    }
    else{
      if(cu.validarIDUsuario(cuentaUsu)){
        out.println("<p>Usuario ya existe</p>");
      }
      if(!cp.validarIDPersona(cuentaPer)){
        out.println("<p>Persona no existe no se puede dar de alta como usuario</p>");
      }
      if(!(ca.validarIDAdministrador(cuentaPriA)|| cu2.getPrivilegio(cuentaPri))){
        out.println("<p>ID no autorizado para dar de alta usuarios</p>");
      }
      IniciarUsuarioNuevo();
    }
    out.println("</form>");
    out.println("</BODY>");
    out.println("</HTML>");

  }
}
