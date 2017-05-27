package com.infoshareacademy.jjdd1.teamerror;

import com.google.gson.Gson;
import com.infoshareacademy.jjdd1.teamerror.dataBase.PromotedCountriesSaver;
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
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(WelcomeServlet.class);
    private HttpSession session;
    private FilesContent filesContent;

    @Inject
    SavingAdminBase savingAdminBase;

    @Inject
    PromotedCountriesSaver promotedCountriesSaver;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");

        //setting the current list of admins
        HttpSession session = req.getSession(true);
        session.setAttribute("adminList", savingAdminBase.getListOfAdmins());
        session.setAttribute("promotedCountryList", promotedCountriesSaver.getPromotedCountries());
        LOGGER.info("Admin List data : {} ", session.getAttribute("adminList"));
        LOGGER.info("Country List data : {} ", session.getAttribute("promotedCountryList"));


        //creating a JSON admin list, to later use it in JavaScript in footer.jsp
        List<String> adminList = savingAdminBase.getListOfAdmins();
        String adminListString = new Gson().toJson(adminList);
        session.setAttribute("jsonAdminList", adminListString);
        LOGGER.info("JSON string from the Admin List: {} ", adminListString);

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

        String countryInput = req.getParameter("countryInput");
        if (countryInput != null && !promotedCountriesSaver.getPromotedCountries().contains(countryInput)) {
            promotedCountriesSaver.addCountry(countryInput);
        }

        String countryRemover = req.getParameter("countryRemover");
        if (countryRemover != null ) {
            for (String s: promotedCountriesSaver.getPromotedCountries()) {
                if(countryRemover.equals(s)){
                    promotedCountriesSaver.removeCountry(countryRemover);
                }
            }
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(req, resp);
    }

}
