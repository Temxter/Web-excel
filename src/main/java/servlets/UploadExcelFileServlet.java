package servlets;

import com.oreilly.servlet.MultipartRequest;
import services.ExcelFileHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UploadExcelFileServlet extends HttpServlet {

    private final String saveDirectory = "loaded_files";

    @Override
    public void init() throws ServletException {
        super.init();
        new File(saveDirectory).mkdir();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/uploadFile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int megabyte = 1048576;
        MultipartRequest multipartRequest = new MultipartRequest(req, saveDirectory, 100 * megabyte);
        File loadDir = new File(saveDirectory);
        List<File> filesToProcessing = Arrays.stream(loadDir.listFiles())
                .filter(file -> file.isFile())
                .collect(Collectors.toList());
        String param = "";
        for (File file : filesToProcessing) {
            try {
                ExcelFileHandler.saveFileToDatabase(file.getName());
                param = "message=Файл загружен и обработан успешно!";
            } catch (Exception e) {
                System.err.println("Error save file to database: " + e.getMessage());
                param = "error=Ошибка: файл не может быть обработан!";
            }
        }
        resp.sendRedirect(String.format("%s/upload?%s",
                req.getContextPath(), param));
    }
}
