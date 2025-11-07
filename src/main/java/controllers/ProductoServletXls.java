package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import models.Producto;
import services.ProductoService;
import services.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/producto.html")
public class ProductoServletXls extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Lista de productos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de productos</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Categoría</th>");
            out.println("<th>Precio</th>");
            out.println("</tr>");

            productos.forEach(p->{
                out.println("<tr>");
                out.println("<td>" + p.getIdProducto()+ "</td>");
                out.println("<td>" + p.getNombre()+ "</td>");
                out.println("<td>" + p.getCategoria()+ "</td>");
                out.println("<td>" + p.getPrecio()+ "</td>");

            });


            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}


