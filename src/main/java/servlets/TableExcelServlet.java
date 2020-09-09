package servlets;

import dao.DaoPeriodEntity;
import model.ClassName;
import model.PeriodEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TableExcelServlet extends HttpServlet {

    private DaoPeriodEntity daoPeriodEntity;

    @Override
    public void init() throws ServletException {
        super.init();
        daoPeriodEntity = new DaoPeriodEntity();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        int fileId = Integer.parseInt(path.substring(1));
        PeriodEntity periodEntity = daoPeriodEntity.get(fileId);
        req.setAttribute("className", new ClassName());
        req.setAttribute("periodEntity", periodEntity);
        req.getRequestDispatcher("/WEB-INF/pages/tableExcel.jsp").forward(req, resp);
    }
}
