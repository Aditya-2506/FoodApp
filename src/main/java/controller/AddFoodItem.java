package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.MyDao;
import dto.FoodItem;
import dto.Hotel;

@MultipartConfig
@WebServlet("/add-food-item")
public class AddFoodItem extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("add-Food-Item.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Hotel hotel = (Hotel) session.getAttribute("hotel");

		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		String type = req.getParameter("type");
		int stock = Integer.parseInt(req.getParameter("stock"));

		Part part=req.getPart("image");
		byte[] image = new byte[part.getInputStream().available()];
		part.getInputStream().read(image);

		FoodItem foodItem = new FoodItem();
		foodItem.setImage(image);
		foodItem.setName(name);
		foodItem.setPrice(price);
		foodItem.setStock(stock);
		foodItem.setType(type);
		foodItem.setHotel(hotel);

		MyDao dao = new MyDao();
		dao.saveFoodItem(foodItem);

		resp.getWriter().print("<h1 style='color:green'>Food Item Added Success</h1>");
		req.getRequestDispatcher("hotel-Home.html").include(req, resp);

	}
}
