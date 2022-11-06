/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Validation.Validate;
import dao.ITestProjectDAO;
import dao.impl.TestProjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CodeTracker;
import model.IssueTracker;
import model.TestProject;

/**
 *
 * @author Admin
 */
public class AdminEditTestProjectController extends HttpServlet {

    ITestProjectDAO testProjectDao = new TestProjectDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminEditAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminEditAccount at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        //get username param from jsp
        String name = request.getParameter("name");
        TestProject testProject = testProjectDao.getTestProjectByName(name);

        List<IssueTracker> listI = testProjectDao.listIssueTracker();
        List<CodeTracker> listC = testProjectDao.listCodeTracker();

        request.setAttribute("listI", listI);
        request.setAttribute("listC", listC);

        //set attribute and send to jsp
        request.setAttribute("testProject", testProject);
        request.getRequestDispatcher("AdminEditTestProject.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        //get account properites from jsp
        String name = request.getParameter("name");
        String prefix = request.getParameter("prefix");
        String description = request.getParameter("description");
        String issueTracker = request.getParameter("issueTracker");
        String codeTracker = request.getParameter("codeTracker");
        Boolean isActive = Boolean.valueOf(request.getParameter("isActive"));
        Boolean isPublic = Boolean.valueOf(request.getParameter("isPublic"));

        //set popeties into new account
        TestProject testProject = new TestProject();
        testProject.setName(name);
        testProject.setPrefix(prefix);
        testProject.setDescription(description);
        testProject.setIsActive(isActive);
        testProject.setIsPublic(isPublic);

        IssueTracker i = new IssueTracker();
        i.setId(issueTracker);
        testProject.setIssueTracker(i);

        CodeTracker c = new CodeTracker();
        c.setId(codeTracker);
        testProject.setCodeTracker(c);

        //check constraint and store in mess
        String mess = "";
        if (Validate.checkUserName(name) == false) {
            mess = "Name sai";
        } else {

            //edit account
            boolean check = testProjectDao.editTestProject(testProject);

            //get edit status through check variable
            if (check == true) {
                String successMessage = "Edit successfully!";
                request.setAttribute("successMessage", successMessage);

            } else {
                String failMessage = "Edit failed!";
                request.setAttribute("failMessage", failMessage);
            }
        }

        request.setAttribute("mess", mess);

        //set properties and getRequestDispatcher to AccountDetail.jsp
        request.setAttribute("testProject", testProject);
        request.getRequestDispatcher("AdminEditTestProject.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
