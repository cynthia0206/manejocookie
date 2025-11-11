package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();

        if (cookieOptional.isPresent()) {
            // Página de bienvenida con estilos
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("<title>Bienvenido</title>");

                // Estilos solo para la bienvenida
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background: linear-gradient(to right, #f5e6ff, #ffe6f0); display: flex; flex-direction: column; align-items: center; padding: 50px; }");
                out.println("h1 { color: #8a2be2; }");
                out.println("p { font-size: 18px; color: #c71585; }");
                out.println("a { color: #c71585; text-decoration: none; font-weight: bold; }");
                out.println("a:hover { text-decoration: underline; color: #8a2be2; }");
                out.println("</style>");

                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido, " + cookieOptional.get() + "!</h1>");
                out.println("<p>Has iniciado sesión correctamente.</p>");
                out.println("<a href='" + req.getContextPath() + "/index.html'>Ir al inicio</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // Redirige al login.jsp sin estilos
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 5); // 5 minutos
            resp.addCookie(cookie);

            // Redirige a la página de bienvenida
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos, no tienes acceso. Revisa los datos de usuario y contraseña.");
        }
    }
}
