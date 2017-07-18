<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>jQeury.steps Demos</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-step/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-step/css/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-step/css/jquery.steps.css">
        <script src="${pageContext.request.contextPath }/js/jquery-step/lib/modernizr-2.6.2.min.js"></script>
        <script src="${pageContext.request.contextPath }/js/jquery-step/lib/jquery-1.9.1.min.js"></script>
        <script src="${pageContext.request.contextPath }/js/jquery-step/lib/jquery.cookie-1.3.1.js"></script>
        <script src="${pageContext.request.contextPath }/js/jquery-step/build/jquery.steps.js"></script>
    </head>
    <body>

        <div class="content">
            <h1>Basic Demo</h1>

            <script>
                $(function ()
                {
                    $("#wizard").steps({
                        headerTag: "h2",
                        bodyTag: "section",
                        transitionEffect: "slideLeft"
                    });
                });
            </script>

            <div id="wizard">
                <h2>第一步</h2>
                <section>
                    <p>第一步数据</p>
                </section>

                <h2>第二步</h2>
                <section>
                    <p>第二步数据</p>
                </section>

                <h2>第三步</h2>
                <section>
                    <p>第三步数据</p>
                </section>

            </div>
        </div>
    </body>
</html>