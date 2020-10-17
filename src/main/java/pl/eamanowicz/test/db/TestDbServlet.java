package pl.eamanowicz.test.db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {

    private static  final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String user = "postgres";
        String pass = "niezgadniesz1";

        String jdbcUrl = "jdbc:postgresql://localhost/web_customer_tracker";
        String driver = "org.postgresql.Driver";

        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to db: " + jdbcUrl);

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);

            out.println("SUCCESS");
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


}
