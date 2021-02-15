package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import model.SiteEV;
import model.SiteEVLogic;

@WebServlet("/SmithIndex")
public class SmithIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		SiteEV siteEV = (SiteEV)application.getAttribute("siteEV");
		
		if (siteEV == null) {
			siteEV = new SiteEV();
		}
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		SiteEVLogic siteEVLogic = new SiteEVLogic();
		if (action != null && action.equals("like")) {
			siteEVLogic.like(siteEV);
		} else if (action != null && action.equals("dislike")) {
			siteEVLogic.dislike(siteEV);
		}
		
		application.setAttribute("siteEV", siteEV);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/smithIndex.jsp");
		dispatcher.forward(request, response);
	}
}
