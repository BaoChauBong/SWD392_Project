/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Validation.Validate;
import dao.impl.AccountDAO;
import dao.IAccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Tạ Văn Tân
 */
public class SignUpController extends HttpServlet {

    IAccountDAO accountDAO = new AccountDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); //có thể nhận dữ liệu là tiếng việt

        //lấy dữ liệu từ request khi người dùng gửi lên
        String user = request.getParameter("username").trim();
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        String email = request.getParameter("email").trim();
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        if (!pass.equals(repass)) {
            //đăng ký không thành công lưu lại dữ liệu ở trang để đỡ phải nhập lại
            request.setAttribute("username", user);
            request.setAttribute("firstname", firstname);
            request.setAttribute("password", pass);
            request.setAttribute("repassword", repass);
            request.setAttribute("email", email);
            request.setAttribute("lastname", lastname);
            //mật khẩu nhập lại ko giống nhau gửi 1 câu thông báo lỗi rồi chuyển đến trang register
            request.setAttribute("mess", "Mật khẩu không trùng khớp với nhau");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else {
            Account a = accountDAO.getAccountByUsername(user);
            if (a == null) {
                if (Validate.checkFullName(firstname) == false) {
                    request.setAttribute("username", user);
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("password", pass);
                    request.setAttribute("repassword", repass);
                    request.setAttribute("email", email);
                    request.setAttribute("lastname", lastname);
                    //kiểm tra đầu vào cho fullname
                    request.setAttribute("mess", "Thông tin firstname không hợp lệ! ");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                } else if (Validate.checkUserName(user) == false) {
                    request.setAttribute("username", user);
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("password", pass);
                    request.setAttribute("repassword", repass);
                    request.setAttribute("email", email);
                    request.setAttribute("lastname", lastname);
                    //kiểm tra đầu vào cho User name
                    request.setAttribute("mess", "Username phải có ít nhất 6 ký tự không bao gồm ký tự đặc biệt !! ");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);

                } else if (Validate.checkEmail(email) == false) {
                   request.setAttribute("username", user);
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("password", pass);
                    request.setAttribute("repassword", repass);
                    request.setAttribute("email", email);
                    request.setAttribute("lastname", lastname);
                    //kiểm tra đầu vào cho Email
                    request.setAttribute("mess", "Vui lòng nhập email có dạng example@gmail.com ");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);

                } else if (Validate.checkPassword(pass) == false) {
                    request.setAttribute("username", user);
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("password", pass);
                    request.setAttribute("repassword", repass);
                    request.setAttribute("email", email);
                    request.setAttribute("lastname", lastname);
                    //kiểm tra đầu vào cho password
                    request.setAttribute("mess", "Password phải có ít nhất 6 đến 8 ký tự và có ít nhất 1 ky"
                            + " tự chữ thường, chữ hoa, số và ký tự đặc biệt !! ");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);

                } else {

                    //kiểm tra biến account a ko tồn tại cho phép được đăng ký
                    accountDAO.insertAccount(user, pass, firstname, lastname, email, 3);
                    request.setAttribute("messSuccess", "Register succesfull!");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                }
            } else {
                    request.setAttribute("username", user);
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("password", pass);
                    request.setAttribute("repassword", repass);
                    request.setAttribute("email", email);
                    request.setAttribute("lastname", lastname);
                //đăng ký ko thành công đẩy về trang signup vì tài khoản đã tồn tại
                request.setAttribute("mess", "Tài khoản đã tồn tại!!");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
