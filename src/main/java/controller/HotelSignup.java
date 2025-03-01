package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Hotel;

@WebServlet("/hotel-signup")
public class HotelSignup extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("hotel-Signup.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String address = req.getParameter("address");

		MyDao dao = new MyDao();

		List<Hotel> list = dao.findHotelByEmail(email);

		if (list.isEmpty()) {
			Hotel hotel = new Hotel();
			hotel.setAddress(address);
			hotel.setEmail(email);
			hotel.setMobile(mobile);
			hotel.setName(name);
			hotel.setPassword(AES.encrypt(password, "123"));

			dao.saveHotel(hotel);

			resp.getWriter().print("<h1 align='center' style='color:green'>Account Created Success</h1>");
			req.getRequestDispatcher("hotel-Login.html").include(req, resp);
		} else {
			resp.getWriter().print(
					"<h1 align='center' style='color:red'>Account Already Exists with Email - " + email + "</h1>");
			req.getRequestDispatcher("hotel-Signup.html").include(req, resp);
		}
	}
}
