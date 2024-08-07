package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.FoodItem;

@WebServlet("/delete-food-item")
public class DeleteFoodItem extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MyDao dao = new MyDao();
		
		int id = Integer.parseInt(req.getParameter("id"));
		FoodItem foodItem = dao.fetchFoodById(id);
		
		dao.deleteFoodItem(foodItem);

		resp.getWriter().print("<h1 style='color:green' align='center'>Deleted Success</h1>");
		req.getRequestDispatcher("view-food-item").include(req, resp);
	}
}
