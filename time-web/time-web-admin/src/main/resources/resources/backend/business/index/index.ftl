<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台首页</title>
    <link rel="stylesheet" href="/css/common/common.css">
    <link rel="stylesheet" href="/js/plugin/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/plugin/fontawesome/css/fontawesome.css">
    <link rel="stylesheet" href="/css/business/index.css">
    <link rel="stylesheet" href="/css/common/sidebar.css">
</head>
<body>
<div class="main-wrapper">
    <header class="top-bar">
        <nav class="nav nav-bar navbar-expand-md">
            <!-- logo -->
            <div class="nav-bar-header">
                <a class="navbar-brand" href="/">
                    <b>
                        <img src="/img/common/logo-dark-icon.png" alt="图标" class="light-logo">
                    </b>
                    <span>
                             <img src="/img/common/logo-dark-text.png" alt="图标" class="light-logo">
                        </span>
                </a>
            </div>
            <!-- End Logo -->
            <div class="navbar-collapse">
                <ul class="navbar-nav mr-auto mt-md-0">
                    <li class="nav-item">
                        <a class="nav-link" href="javascript:void(0)">
                            <i class="fa fa-bars"></i>
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="javascript:void(0)">
                            <i class="fa fa-bell"></i>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="javascript:void(0)">
                            <img src="/img/common/avator.jpg" alt="user" class="profile-pic">
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="left-sidebar">
        <div class="user-profile">
            <div class="profile-img">
                <img src="/img/common/avator.jpg" alt="头像">
            </div>
            <div class="profile-text">
                <span class="profile-text-username">admin</span>
            </div>
        </div>
        <!--定义可滑动区域-->
        <div class="scroll-sidebar">
            <nav class="sidebar-nav">
                <ul class="sidebar-nav-ul">
                    <li class="sidebar-item">
                        <a class="sidebar-link sidebar-active sidebar-link-has-arrow" href="#">
                            <div class="sidebar-link-left">
                                <span class="sidebar-link-left-i">
                                    <i class="fa fa-cogs" aria-hidden="true"></i>
                                </span>
                                <span class="sidebar-link-menu">Dashboards</span>
                            </div>
                            <span class="sidebar-link-right-i">
                            <i class="fa fa-angle-right sidebar-arrow"></i>
                            </span>
                        </a>
                        <ul class="first-level">
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="#">
                                    <div class="sidebar-link-left">
                                        <i class="mdi mdi-adjust"></i>
                                        <span class="menu">Classic</span>
                                    </div>
                                    </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="#">
                                    <i class="mdi mdi-adjust"></i>
                                    <span class="menu">Analytical</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </aside>
</div>
</body>
<script src="/js/plugin/jquery/jq.js"></script>
<script src="/js/plugin/bootstrap/js/popper.min.js"></script>
<script src="/js/plugin/bootstrap/js/bootstrap.min.js"></script>
</html>