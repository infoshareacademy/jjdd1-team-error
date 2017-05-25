package com.infoshareacademy.jjdd1.teamerror;

import com.infoshareacademy.jjdd1.teamerror.dataBase.SavingAdminBase;
import com.infoshareacademy.jjdd1.teamerror.file_loader.FilesContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(WelcomeServlet.class);
    private HttpSession session;
    private FilesContent filesContent;

    @Inject
    SavingAdminBase savingAdminBase;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        HttpSession session = req.getSession(true);

        String emailInput = req.getParameter("emailInput");
        if (emailInput != null) {
            savingAdminBase.addAdmin(emailInput);
        }

        String emailRemover = req.getParameter("emailRemover");
        if (emailRemover != null ) {
            for (String s: savingAdminBase.getListOfAdmins()) {
                if(emailRemover.equals(s)){
                    savingAdminBase.removeAdmin(emailRemover);
                }
            }
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(req, resp);
    }

}
