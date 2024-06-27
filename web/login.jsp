<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up Form by Colorlib</title>

        <!-- Font Icon -->
        <link rel="stylesheet"
              href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="account_assets/css/style.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="main">

                <!-- Sing in  Form -->
                <section class="sign-in">
                    <div class="container">
                        <div class="signin-content">
                            <div class="signin-image">
                                <figure>
                                    <img src="account_assets/images/signin-image.jpg" alt="sing up image">
                                </figure>
                                <a href="registration.jsp" class="signup-image-link">Create an account</a>
                            </div>

                            <div class="signin-form">
                                <h2 class="form-title">Sign up</h2>
                            <c:set var="cookie" value="${pageContext.request.cookies}"/>
                            <form method="post" action="login" class="register-form"
                                  id="login-form">
                                <div class="form-group">
                                    <label for="username"><i
                                            class="zmdi zmdi-account material-icons-name"></i></label> 
                                    <input type="text" name="username" id="username" value="${cookie.userC.value}" required="" placeholder="Username" />
                                </div>
                                <div class="form-group">
                                    <label for="password"><i class="zmdi zmdi-lock"></i></label> <input
                                        type="password" name="password" value="${cookie.passC.value}" id="password" required=""
                                        placeholder="Password" />
                                </div>
                                <div class="form-group">
                                    <label style="color: red" for="status" class="label-agree-term">
                                        <span><span></span></span>${requestScope.msg}</label>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="remember-me" ${(cookie.rememC != null ? 'checked':'')}  id="remember-me"
                                           class="agree-term" /> 
                                    <label for="remember-me" class="label-agree-term">
                                        <span><span></span></span>Remember me</label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin"
                                           class="form-submit" value="Log in" />
                                </div>
                            </form>
                            <div class="social-login">
                                <span class="social-label">Or login with</span>
                                <ul class="socials">
                                    <li><a href="https://www.facebook.com/dialog/oauth?client_id=424867356602928&redirect_uri=http://localhost:9999/BookStore/login-facebook"><i
                                                class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                    <li><a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/BookStore/login-google&response_type=code
                                           &client_id=629582591715-1e0d8imvap58f0f1pg4193dequu98fkd.apps.googleusercontent.com&approval_prompt=force"><i
                                                class="display-flex-center zmdi zmdi-google"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>
    </body>
</html>