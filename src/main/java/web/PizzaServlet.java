package web;

import domain.Order;
import domain.Pizza;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.OrderService;
import service.PizzaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Roman Usik
 */
public class PizzaServlet extends HttpServlet {

    private ConfigurableApplicationContext repositoryContext;
    private ConfigurableApplicationContext context;
    @Override
    public void init() throws ServletException {
        repositoryContext = new ClassPathXmlApplicationContext("repositoryContext.xml");
        context = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, false);

        context.setParent(repositoryContext);
        context.refresh();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

                        /* TODO output your page here. You may use following sample code. */

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SimpleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PizzaServlet at " + request.getRequestURI() + "</h1>");

            PizzaService pizzaService = context.getBean(PizzaService.class);
            List<Pizza> pizzas = pizzaService.getAllPizzas();
            for (Pizza pizza : pizzas) {
                out.println("<h3>" + pizza + "</h3>");
            }

            OrderService orderService = context.getBean(OrderService.class);
            String pizzass = request.getParameter("id");
            out.println("<h3>" + pizzass + "</h3>");

            if (pizzass != null) {
                out.println("<h2>orders</h2>");
                Order order = orderService.placeNewOrder(null, SimpleServlet.parseIntArrayString(pizzass));
                out.println("<h2>" + order + "</h2>");
                out.println("<h2>orders</h2>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        repositoryContext.close();
        context.close();
    }
}
