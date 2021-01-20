package edu.bcm.hgsc.fhir;

import edu.bcm.hgsc.fhir.services.FileUploadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 10 * 5)
public class FileUploadServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("------------ FileUploadServlet ------------");

        String projectDir = this.getServletContext().getRealPath(File.separator);
        String UPLOAD_DIRECTORY = projectDir + "/WEB-INF/uploadFiles/";
        Part filePart = request.getPart("filename");
        String fileName = getSubmittedFileName(filePart);

        if (request.getContentLength() > 0) {
            File file = new File(UPLOAD_DIRECTORY, fileName);
            file.createNewFile();
            try (InputStream inputStream = filePart.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(file)) {
                byte temp[] = new byte[1024];
                int size = -1;
                // Read 1kb each time
                while ((size = inputStream.read(temp)) != -1) {
                    outputStream.write(temp, 0, size);
                }
                logger.info("File load success.");
            } catch (IOException e) {
                logger.error("File load fail.", e);
                request.getRequestDispatcher("/fail.jsp").forward(request, response);
            }

            FileUploadServiceImpl fileUploadServiceImpl = new FileUploadServiceImpl();
            ArrayList<String> resp = fileUploadServiceImpl.createFhirResourcesInTest(file);
            request.setAttribute("resultURLArr", resp);
        }
        //Go to a new page with all created FHIR resource URLs
        request.getRequestDispatcher("/success.jsp").forward(request, response);
    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
