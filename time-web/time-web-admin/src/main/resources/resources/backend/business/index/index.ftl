<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台首页</title>
    <link rel="stylesheet" href="/css/common/common.css">
    <link rel="stylesheet" href="/js/plugin/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/plugin/fontawesome/css/fontawesome.css">
    <link rel="stylesheet" href="/css/business/index.css">
    <link rel="stylesheet" href="/css/plugin/monster/treeMenu.css">
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
    <div class="left-sidebar">
        <div class="user-profile">
            <div class="profile-img">
                <img src="/img/common/avator.jpg" alt="头像">
            </div>
            <div class="profile-text">
                <span class="profile-text-username">admin</span>
            </div>
        </div>
        <div id="sidebar"></div>
    </div>
</div>
</body>
<script src="/js/plugin/jquery/jq.js"></script>
<script src="/js/plugin/bootstrap/js/popper.min.js"></script>
<script src="/js/plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="/js/framework/treeMenu.js"></script>
</html>