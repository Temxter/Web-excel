package servlets;

import dao.DaoPeriodEntity;
import model.PeriodEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FileListServlet extends HttpServlet {

    private DaoPeriodEntity daoPeriodEntity;

    @Override
    public void init() throws ServletException {
        super.init();
        daoPeriodEntity = new DaoPeriodEntity();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PeriodEntity> periodEntityList = daoPeriodEntity.getAll();
        req.setAttribute("files", periodEntityList);
        req.getRequestDispatcher("/WEB-INF/pages/fileList.jsp").forward(req, resp);
    }
}
