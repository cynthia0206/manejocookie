package servlet;
/*
 * AUTOR: CYNTHIA CUNEZ
 * FECHA: 2/11/2025
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import services.ProductoService;
import services.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();

        // Verificar si el usuario está logueado (cookie "username")
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();

        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");

        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment; filename=productos.xls");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
        }

        try (PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("<title>Listado de Productos</title>");

                // Estilos en lila y rosa
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background: linear-gradient(to right, #f5e6ff, #ffe6f0); padding: 20px; }");
                out.println("h1 { color: #8a2be2; text-align: center; }");
                out.println("table { width: 80%; border-collapse: collapse; margin: 20px auto; background-color: #f9e6ff; }");
                out.println("th, td { border: 1px solid #d19cd1; padding: 10px; text-align: center; }");
                out.println("th { background-color: #d19cd1; color: white; }");
                out.println("tr:nth-child(even) { background-color: #f2cce5; }");
                out.println("a { text-decoration: none; color: #c71585; font-weight: bold; margin-right: 10px; }");
                out.println("a:hover { text-decoration: underline; color: #8a2be2; }");
                out.println("</style>");

                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Listado de productos</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/productos.xls'>Exportar a Excel</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Volver al inicio</a></p>");
            }

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Tipo</th>");
            out.println("<th>Precio</th>");
            out.println("</tr>");

            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getIdProducto() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getCategoria() + "</td>");

                // Si no está logueado, no mostramos el precio
                if (cookieOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                } else {
                    out.println("<td><i>Solo visible para usuarios logueados</i></td>");
                }

                out.println("</tr>");
            });

            out.println("</table>");

            if (!esXls) {
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}

