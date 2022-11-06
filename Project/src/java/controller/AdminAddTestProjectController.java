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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.CodeTracker;
import model.IssueTracker;
import model.TestProject;

/**
 *
 * @author HP
 */
public class AdminAddTestProjectController extends HttpServlet {
    
    ITestProjectDAO testProjectDao = new TestProjectDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminAddAccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAddAccountController at " + request.getContextPath() + "</h1>");
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
        
        List<IssueTracker> listI = testProjectDao.listIssueTracker();
        List<CodeTracker> listC = testProjectDao.listCodeTracker();
        
        request.setAttribute("listI", listI);
        request.setAttribute("listC", listC);
        
        request.getRequestDispatcher("AdminAddTestProject.jsp").forward(request, response);
        
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
        String issueTracker = request.getParameter("idIs");
        String codeTracker = request.getParameter("idCo");
        boolean isActive = Boolean.valueOf(request.getParameter("isActive"));
        boolean isPublic = Boolean.valueOf(request.getParameter("isPublic"));

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
            mess = "Username phải có ít nhất 6 ký tự không bao gồm ký tự đặc biệt";
        } else {

            //edit account
            boolean check = testProjectDao.insertTestProject1(testProject);
            //get edit status through check variable
            if (check == true) {
                String successMessage = "Add successfully!";
                request.setAttribute("successMessage", successMessage);
                
            } else {
                String failMessage = "Add failed!";
                request.setAttribute("failMessage", failMessage);
            }
        }
        
        request.setAttribute("mess", mess);

        //get properties and getRequestDispatcher to AccountDetail.jsp
        request.setAttribute("testProject", testProject);
        request.getRequestDispatcher("AdminAddTestProject.jsp").forward(request, response);
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
