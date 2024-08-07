package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;

@WebServlet("/customer-login")
public class CustomerLogin extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("customer-Login.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String Password = req.getParameter("password");

		MyDao dao = new MyDao();

		List<Customer> list = dao.findCustomerByEmail(email);

		if (list.isEmpty()) {
			resp.getWriter()
					.print("<h1 align='center' style='color:red; position:relative; top:15%;'>Invalid Email</h1>");
			req.getRequestDispatcher("customer-Login.html").include(req, resp);
		} else if (Password.equals(AES.decrypt(list.get(0).getPassword(), "123"))) {
			req.getSession().setAttribute("customer", list.get(0));
			resp.getWriter().print("<h1 align='center' style='color:green;' class='fadeOut'>Login Success</h1>");
			req.getRequestDispatcher("customer-Home.html").include(req, resp);
		} else {
			resp.getWriter().print("<h1 align='center' style='color:red;'>Invalid Password</h1>");
			req.getRequestDispatcher("customer-Login.html").include(req, resp);
		}
	}
}
