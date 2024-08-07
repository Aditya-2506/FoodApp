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

@WebServlet("/customer-signup")
public class CustomerSignup extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("customer-Signup.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		long mno = Long.parseLong(req.getParameter("mobile"));
		String address = req.getParameter("address");

		MyDao dao = new MyDao();

		List<Customer> list = dao.findCustomerByEmail(email);
		
		if (list.isEmpty()) {
			Customer customer=new Customer();
			customer.setAddress(address);
			customer.setEmail(email);
			customer.setMobile(mno);
			customer.setName(name);
			customer.setPassword(password);
			
			dao.saveCustomer(customer);
			
			resp.getWriter().print("<h1 align='center' style='color:green;'>Your account created successfully</h1>");
			req.getRequestDispatcher("customer-Login.html").include(req, resp);
		} else {
			resp.setContentType("customer-Signup.js");
//			resp.getWriter().print("<h1 align='center' style='color:red;'>Account already exists with email - " + email + "</h1>");
			req.getRequestDispatcher("customer-Signup.html").include(req, resp);
		}

	}
}
