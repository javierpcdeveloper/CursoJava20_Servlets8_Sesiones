package web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SessionServlet")
public class ServletSesiones extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
        res.setContentType("text/html;charset=UTF-8");
        HttpSession sesion=req.getSession();//devuelve el objeto sesion y si es la primera vez, la crea
        String titulo=null;
        Integer contadorVisitas=(Integer)sesion.getAttribute("contadorVisitas");
        if(contadorVisitas==null){//primera vez
            contadorVisitas=1;
            titulo="Bienvenido por primera vez";
        }else{//no es la primera vez
            contadorVisitas++;
            titulo="Bienvenido nuevamente";
        }
        sesion.setAttribute("contadorVisitas", contadorVisitas);
        PrintWriter out=res.getWriter();
        out.print("<h2>"+titulo+"</h2>");
        out.print("Número de accesos al recurso:"+contadorVisitas);
        out.print("<br>");
        out.print("ID de la sesión:"+sesion.getId());
        out.close();
    }
}