package com.firstshop.Servlets;

import com.firstshop.Commands.QueryBuilder;
import com.firstshop.Containers.ProductContainer;
import com.firstshop.Containers.ProductContainerBuilder;
import com.firstshop.Containers.UserContainer;
import com.firstshop.Containers.UserContainerBuilder;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@WebServlet("/ProductAddChecker.go")
public class ProductAddChecker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        if (request.getParameter("category").equals("product")) {
            result = productQuery(request);
            request.setAttribute("message", result);
            request.getRequestDispatcher("test.jsp").forward(request, response);
        } else {
            result = userQuery(request);
            request.setAttribute("message", result);
            request.getRequestDispatcher("test.jsp").forward(request, response);
        }
    }

    private String productQuery(HttpServletRequest request) {
        String name = request.getParameter("productName");
        String amount = request.getParameter("productAmount");
        String price = request.getParameter("productPrice");
        if (name == null || Objects.equals(name, "") ||
                amount == null || Objects.equals(amount, "") ||
                price == null || Objects.equals(price, "")
                ) {
            return "One or few tables are empty";
        } else {
            ProductContainer pc = new ProductContainerBuilder().
                    name(request.getParameter("productName")).
                    imageLocation("productImage").
                    amount("productAmount").
                    price("productPrice").
                    productDescription("productDescription").
                    build();
            QueryBuilder qb = new QueryBuilder();
            if (qb.createProduct(pc)) {
                return "Complete";
            } else return "Bad request";
        }
    }

    private String userQuery(HttpServletRequest request) {
        String name = request.getParameter("user_password");
        String password = request.getParameter("user_password");
        if (name == null || name == "" || password == null || password == "") {
            return "One or few tables are empty";
        } else {
            String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            UserContainer uc = new UserContainerBuilder()
                    .name(request.getParameter("user_name"))
                    .password(generatedSecuredPasswordHash)
                    .buid();
            if (new QueryBuilder().createUser(uc)) {
                return "Complete";
            } else return "Bad request";
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
