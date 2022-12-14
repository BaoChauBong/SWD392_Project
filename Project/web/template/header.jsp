<%-- 
    Document   : header
    Created on : 09-Feb-2022, 00:17:32
    Author     : Bảo Châu Bống
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %> 
<!DOCTYPE html>

<div class="row m-0 p-0" style="background: linear-gradient(333deg, rgba(233,197,212,1) 0%, rgba(162,195,234,1) 100%);">
    <span id="promo" class="d-lg-block d-none" style="color: #000">
        <marquee behavior="scroll" direction="left">Demo Testlink - Login + CRUD User !!!</marquee>
    </span
</div>

<nav class="navbar main-navbar navbar-expand-lg navbar-light bg-light pt-0" id="navbar1">
    <div class="container-fluid" style="background: linear-gradient(333deg, rgba(233,197,212,1) 0%, rgba(162,195,234,1) 100%);" >
        <!-- LOGO -->
        <a class="navbar-brand col-lg-2 offset-lg-2 logo" href="${pageContext.request.contextPath}/home">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png">
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <!-- SEARCHBAR -->
            <form class="d-flex searchbar" method="get" action="${pageContext.request.contextPath}/searchmovie">
                <input type="hidden" name="page" value="1"/>
                <input name="moviename" value="${movieNameInput}" class="form-control me-2" type="text" placeholder="Search movie you want ..." aria-label="Search">

                <button class="btn btn-search" type="submit"><span><i class="fas fa-search" style="font-size: 100%; "></i></span></button>
            </form>
            <ul class="navbar-nav me-auto ms-auto">                

                <!-- EXPANDED -->
                <c:if test="${sessionScope.acc !=null}">
                    <li class="nav-item dropdown d-none d-lg-block">
                        <a class="nav-link" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-bell"></i>
                            <c:if test="${sessionScope.unreadednoti > 0}">
                                <span class="position-relative translate-middle badge rounded-pill bg-danger">
                                    ${sessionScope.unreadednoti}
                                    <span class="visually-hidden">unread notifications</span>
                                </span>
                            </c:if>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li style="text-align: center;">Thông báo</li>
                                <c:forEach items="${sessionScope.notiHashMap}" var="notiHashMap" varStatus="i" >
                                <li><hr class="dropdown-divider"></li>
                                <a href="notificationview?notiId=${notiHashMap.key.notificationId}" class="dropdown-item" <c:if test="${notiHashMap.value.readed == false}"> style="background: linear-gradient(333deg, rgba(233,197,212,1) 0%, rgba(162,195,234,1) 100%);" </c:if>>
                                    <li style="height:50px; width: auto"><img style="height:100%; width: auto" src="${pageContext.request.contextPath}/assets/img/notification/${notiHashMap.key.notificationImg}">   ${notiHashMap.key.notificationTitle}</li>
                                    <li style="font-size:10px;color: blue; font-weight: bold">
                                        <c:if test="${notiHashMap.value.readed == false}">
                                            <span style="font-size: 20px; color: red">•</span>
                                        </c:if>
                                        <fmt:formatDate pattern="dd-MM-yyyy" value = "${notiHashMap.key.notificationDate}"/>
                                    </li>
                                </a>
                            </c:forEach>
                        </ul>
                    </li>

                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="Cart.jsp">
                            <i class="fas fa-shopping-cart"></i>
                            <c:if test="${total > 0}">
                                <span class="position-relative translate-middle badge rounded-pill bg-danger">
                                    1
                                </span>
                            </c:if>
                            <c:if test="${total == null}">
                                <span class="position-relative translate-middle badge rounded-pill bg-danger">
                                    0
                                </span>
                            </c:if>

                            <c:if test="${sessionScope.user !=null}">

                            </c:if>
                        </a>
                    </li>
                </c:if>
                <li class="nav-item dropdown d-none d-lg-block">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user-circle"></i>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:if test ="${sessionScope.acc !=  null}">
                            <!-- Default -->
                            <li><span class="dropdown-item-text">Hello ${acc.getUsername()}</span></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/account?username=${acc.getUsername()}">View profile</a></li>  
                            <!-- Admin -->
                            <c:if test="${sessionScope.acc.roleId ==  1}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/adminaccountlist">Manage Account</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/adminrecruitmentlist">Manage Recruitment</a></li>

                                <li><a class="dropdown-item" href="adminListMovie">Manage Movie</a></li>
                                </c:if>
                            <!-- Staff -->
                            <c:if test="${sessionScope.acc.roleId  ==  2}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Dashboard">DashBoard</a></li>
                                </c:if>
                            <!-- Marketing -->
                            <c:if test="${sessionScope.acc.roleId  ==  4}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/adminbannerlist">Manage Banner</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/adminpromotionlist">Manage Promotion</a></li>
                                </c:if>
                            <!-- My order-->
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/MyOrder">My Order</a></li>
                            <!-- Change password -->

                            <li><a class="dropdown-item" href="ChangePassword.jsp">Change password</a></li>

                            <!-- Default Logout -->
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="logout">Logout</a></li>

                        </c:if>

                        <!-- Neu khong co user -->
                        <c:if test ="${sessionScope.acc ==  null}">
                            <li><a class="dropdown-item" href="login">Login</a></li>
                            <li><a class="dropdown-item" href="Register.jsp">Register</a></li>
                            </c:if>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<%-- NAV --%>
<%-- Top Btn --%>
<button onclick="topFunction()" id="myTBTN" title="Go to top"><i class="fas fa-arrow-up"></i></button>