let menu = [
    {id: 1, level: 1, name: '工作台', icon: "fa fa-desktop", type: "link", url: "/detail/quickstart", parentId: 0},
    {
        id: 2,
        level: 1,
        name: '内容管理',
        type: "button",
        isExpanded: false,
        isSelected: false,
        icon: "fa fa-file-text-o",
        parentId: 0,
        subMenu: [
            {
                id: 6, level: 2, name: '简介', parentId: 2, type: "link", url: "/detail/tutorial", subMenu: [
                    {
                        id: 7, level: 2, name: '简介333', parentId: 6, type: "link", url: "/detail/tutorial"
                    },
                    {
                        id: 8,
                        level: 2,
                        name: '简介2',
                        icon: "fa fa-car",
                        parentId: 6,
                        type: "link",
                        url: "/detail/tutorial",
                        subMenu: [
                            {
                                id: 9,
                                level: 2,
                                name: '简介3',
                                parentId: 8,
                                type: "link",
                                icon: "fa fa-car",
                                url: "/detail/tutorial",
                                subMenu: [
                                    {
                                        id: 10,
                                        level: 2,
                                        name: '简介4',
                                        parentId: 9,
                                        type: "link",
                                        icon: "fa fa-car",
                                        url: "/detail/tutorial",
                                        subMenu: [
                                            {
                                                id: 11,
                                                level: 2,
                                                name: '简介5',
                                                parentId: 10,
                                                type: "link",
                                                icon: "fa fa-car",
                                                url: "/detail/tutorial",
                                                subMenu: [
                                                    {
                                                        id: 12,
                                                        level: 2,
                                                        parentId: 11,
                                                        name: '简介6',
                                                        type: "link",
                                                        icon: "fa fa-car",
                                                        url: "/detail/tutorial",
                                                        subMenu: [
                                                            {
                                                                id: 13,
                                                                parentId: 12,
                                                                level: 2,
                                                                name: '简介7',
                                                                icon: "fa fa-car",
                                                                type: "link",
                                                                url: "/detail/tutorial",
                                                            }
                                                        ]
                                                    }
                                                ]
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        ]
    },
    {
        id: 3,
        level: 1,
        name: '核心知识',
        type: "button",
        isExpanded: false,
        icon: "fa fa-car",
        isSelected: false,
        parentId: 0,
        subMenu: [
            {id: 13, level: 2, name: '架构', icon: "fa fa-car", parentId: 3, type: "link", url: "/detail/architecture"},
            {
                id: 14,
                level: 2,
                name: '模板与数据绑定',
                type: "button",
                parentId: 3,
                isExpanded: false,
                icon: "fa fa-car",
                isSelected: false,
                subMenu: [
                    {
                        id: 15,
                        level: 3,
                        name: '显示数据',
                        icon: "fa fa-car",
                        parentId: 14,
                        type: "link",
                        url: "/detail/displaying-data"
                    }
                ]
            },
            {
                id: 16,
                level: 2,
                name: '表单',
                type: "button",
                isExpanded: false,
                icon: "fa fa-car",
                parentId: 3,
                isSelected: false,
                subMenu: [
                    {id: 17, name: '用户输入', icon: "fa fa-car", parentId: 16, type: "link", url: "/detail/user-input"}
                ]
            },
            {
                id: 18,
                level: 2,
                name: '引用启动',
                icon: "fa fa-car",
                parentId: 3,
                type: "link",
                url: "/detail/bootstrapping"
            },
            {
                id: 19,
                level: 2,
                name: 'NgModules',
                type: "button",
                isExpanded: false,
                parentId: 18,
                icon: "fa fa-car",
                isSelected: false,
                subMenu: [
                    {
                        id: 20,
                        parentId: 19,
                        icon: "fa fa-car",
                        level: 3,
                        name: 'NgModule',
                        type: "link",
                        url: "/detail/ngmodule"
                    },
                ]
            },
            {
                id: 21,
                level: 2,
                name: '依赖注入',
                type: "button",
                isExpanded: false,
                icon: "fa fa-car",
                parentId: 3,
                isSelected: false,
                subMenu: [
                    {
                        id: 22,
                        level: 3,
                        icon: "fa fa-car",
                        parentId: 21,
                        name: '依赖注入',
                        type: "link",
                        url: "/detail/dependency-injection"
                    },
                ]
            },
            {id: 23, level: 2, parentId: 3, icon: "fa fa-car", name: 'HttpClient', type: "link", url: "/detail/http"},
        ]
    },
    {
        id: 4,
        level: 1,
        name: '其它技术',
        type: "button",
        isExpanded: false,
        isSelected: false,
        icon: "fa fa-car",
        parentId: 0,
        subMenu: [
            {id: 24, level: 2, parentId: 4, icon: "fa fa-car", name: '国际化（i18n）', type: "link", url: "/detail/i18n"},
            {
                id: 25,
                level: 2,
                name: '环境设置与部署',
                type: "button",
                parentId: 4,
                isExpanded: false,
                icon: "fa fa-car",
                isSelected: false,
                subMenu: [
                    {
                        id: 26,
                        parentId: 25,
                        icon: "fa fa-car",
                        level: 3,
                        name: '搭建本地开发环境',
                        type: "link",
                        url: "/detail/setup"
                    },
                ]
            },
            {
                id: 27,
                level: 2,
                name: '升级',
                icon: "fa fa-car",
                type: "button",
                parentId: 4,
                isExpanded: false,
                isSelected: false,
                subMenu: [
                    {
                        id: 28,
                        parentId: 27,
                        icon: "fa fa-car",
                        level: 3,
                        name: '从 AngularJS 升级',
                        type: "link",
                        url: "/detail/upgrade"
                    },
                ]
            },
            {
                id: 29,
                parentId: 4,
                level: 2,
                name: 'Visual Studio 2015 快速上手',
                icon: "fa fa-car",
                type: "link",
                url: "/detail/visual-studio-2015"
            },
        ]
    },
    {id: 5, level: 1, name: 'API 参考手册', icon: "fa fa-car", type: "link", parentId: 0, url: "/detail/api"}
];

function TreeMenu(config) {
    if (typeof $ === 'undefined') {
        throw Error("not find jQuery...")
    }
    if (!config instanceof Object) {
        throw Error("argument error")
    }
    Object.assign(this.configure = {}, TreeMenu.configure, config);
    this.init();
}

//Html骨骼
TreeMenu.framework = {
    //头
    head: '<aside class="monster-treeMenu">',
    //nav
    nav: '<nav class="monster-treeMenu-nav">',
    //滑动区域
    scroll: '<ul class="monster-treeMenu-nav-ul monster-treeMenu-scroll">',
    //默认icon
    defaultIconClass: 'fa fa-circle-o-notch',
    //默认箭头Class
    defaultArrowClass: " monster-treeMenu-link-arrow",
    //默认激活的tree类名
    defaultActiveTreeLinkClass: 'monster-treeMenu-link-active',
    //默认子类激活的tree类名
    defaultActiveTreeChildLinkClass: 'monster-treeMenu-childLink-active',
    //icon内容
    iconHtml: function (icon) {
        return '<i class="' + icon + '" aria-hidden="true"></i>'
    },
    //获取左侧icon内容
    leftIcon: function (icon, configIcon) {
        return !!icon ? this.iconHtml(icon) : !!configIcon ? this.iconHtml(configIcon) : "";
    },
    //带有子级的ul结构
    linkUl: '<ul class="monster-treeMenu-link-child monster-treeMenu-nav-ul monster-hide">',
    //li标签
    linkLi: '<li class="monster-treeMenu-item">',
    //a标签
    linkA: function (icon, name, hasChild, level) {
        return '<a class="monster-treeMenu-link' + " monster-treeMenu-link-level" + level +
            (!!hasChild ? TreeMenu.framework.defaultArrowClass : "") + '" href="#">' + icon +
            '<span class="monster-treeMenu-linkName">'
            + name + '</span></a>';
    }
}
//配置
TreeMenu.configure = {
    //默认选择器
    selector: "body",
    //json数据 默认空数组
    data: [],
    //类型 默认1:菜单 2:树形控件
    type: 1,
    //遍历方式 默认1:递归  其余:迭代+参数
    traversal: 1,
    //显示方式: 默认1:直接替换 其余:插入
    display: 1,
    //默认icon图标
    icon: TreeMenu.framework.defaultIconClass,
    //动画持续时间
    duration: 200,
}
//结构
TreeMenu.structure = {id: "id", level: "level", name: "name", type: "type", url: "url", subMenu: "subMenu"};
TreeMenu.prototype = {
    constructor: TreeMenu,
    //初始化
    init: function () {
        if (this.configure.type === 1) {
            if (this.configure.traversal === 1) {
                let content = $(TreeMenu.framework.scroll);
                this.recursion(this.configure.data, content, 0);
                this.build(content);
                this.html.appendTo($(this.configure.selector)).unwrap();
            } else {
                this.iterators(this.configure.data);
            }
        }
    },
    //最终构建
    build: function (content) {
        let nav = $(TreeMenu.framework.nav);
        let aside = $(TreeMenu.framework.head).append(nav)
        nav.append(content)
        this.html = aside;
    },
    abc: function (func) {
        let value;
        let active = false;
        let arr = [];
        return function callback() {
            arr.push(arguments);
            if (!active) {
                active = true;
                while (arr.length) {
                    value = func.apply(this, arr.shift());
                }
                active = false;
                return value;
            }
        };
    },
    state: function () {
    },
    //带有子菜单的绑定事件
    submenuBind: function (element) {
        let that = this;
        element.on("click", function (e) {
            e.stopPropagation();
            that.submenuClickCallback($(this));
        })
    },
    //带有子菜单的点击事件回调
    submenuClickCallback: function (self) {
        //不存在上一个元素 顶级菜单
        if (!this.previousClickElement) {
            this.previousClickElement = self;
            this.topElement = self
            self.addClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            self.next(".monster-treeMenu-link-child").stop(false, true).slideDown(this.configure.duration);
            return
        }
        //上一个元素是当前元素
        if (this.previousClickElement.is(self)) {
            if (!self.hasClass(TreeMenu.framework.defaultActiveTreeLinkClass)) {
                self.addClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            } else {
                self.removeClass(TreeMenu.framework.defaultActiveTreeLinkClass)
            }
            self.next(".monster-treeMenu-link-child").stop(false, true).slideToggle(this.configure.duration);
            return
        }
        //含有祖孙关系 被点击的是之前点击的后代
        if ($.contains(this.previousClickElement.parent()[0], self[0])) {
            this.previousClickElement = self;
            self.addClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            self.next(".monster-treeMenu-link-child").stop(false, true).slideToggle(this.configure.duration)
            return
        }
        //含有祖孙关系 之前点击的是被点击的后代
        if ($.contains(self.parent()[0], this.previousClickElement[0])) {
            //如果是一级菜单的话
            if (!self.hasClass(TreeMenu.framework.defaultActiveTreeLinkClass)) {
                self.addClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            } else {
                self.removeClass(TreeMenu.framework.defaultActiveTreeLinkClass)
            }
            //清除所有的下级
            $("." + TreeMenu.framework.defaultActiveTreeLinkClass, self.next(".monster-treeMenu-link-child")).removeClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            self.next(".monster-treeMenu-link-child").stop(false, true).slideUp(this.configure.duration, function () {
                $(".monster-treeMenu-link-child", self.next(".monster-treeMenu-link-child")).hide()
            });
            this.previousClickElement.removeClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            this.previousClickElement = self;
            return
        }
        //顶级菜单的点击
        if (self.hasClass("monster-treeMenu-link-level1")) {
            let topElement = this.topElement
            topElement.removeClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            topElement.next(".monster-treeMenu-link-child").stop(false, true).slideUp(this.configure.duration, function () {
                $(".monster-treeMenu-link-child", topElement.next(".monster-treeMenu-link-child")).hide()
            });
            //清除白色选择
            $("." + TreeMenu.framework.defaultActiveTreeLinkClass, topElement.next(".monster-treeMenu-link-child")).removeClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            $(topElement.next(".monster-treeMenu-link-child")).removeClass("monster-treeMenu-childLink-active")
            this.topElement = self;
            self.addClass(TreeMenu.framework.defaultActiveTreeLinkClass);
            this.previousClickElement = self;
            self.next(".monster-treeMenu-link-child").stop(false, true).slideToggle(this.configure.duration);
            return
        }
        //后代同级点击
        this.previousClickElement.next(".monster-treeMenu-link-child").stop(false, true).slideUp(this.configure.duration)
        this.previousClickElement.removeClass(TreeMenu.framework.defaultActiveTreeLinkClass);
        self.next(".monster-treeMenu-link-child").stop(false, true).slideToggle(this.configure.duration)
        self.addClass(TreeMenu.framework.defaultActiveTreeLinkClass);
        this.previousClickElement = self;
    },
    //递归解析json
    recursion: function (data, parent, level) {
        ++level;
        for (let i of data) {
            let icon = TreeMenu.framework.leftIcon(i.icon, this.configure.icon),
                li = $(TreeMenu.framework.linkLi);
            if (i[TreeMenu.structure.subMenu]) {
                let ul = $(TreeMenu.framework.linkUl),
                    a = $(TreeMenu.framework.linkA(icon, i.name, true, level));
                this.submenuBind(a);
                li.append(a).append(ul);
                parent.append(li);
                this.recursion(i[TreeMenu.structure.subMenu], ul, level);
            } else {
                li.append($(TreeMenu.framework.linkA(icon, i.name, false, level)));
                parent.append(li);
            }
        }
    },
    iterators: function (data) {
        // let that = this, callback = this.recursion(function (obj) {
        //     for(let i of obj){
        //         if (i[that.structure.subMenu]) {
        //             that.html+='<li class="sidebar-item">\n' +
        //                 '<a class="sidebar-link sidebar-link-active sidebar-link-has-arrow" href="#">\n' +
        //                 '<i class="fa fa-cogs" aria-hidden="true">xxx</i>\n' +
        //                 `<span class="sidebar-link-menu">${i.name}</span>\n` +
        //                 '<i>000</i>\n' +
        //                 '</a>\n' +
        //                 '<ul class="sidebar-nav-ul-child">';
        //             callback(i[that.structure.subMenu]);
        //             that.html+="</li></ul>";
        //         }else{
        //             that.html+='<li class="sidebar-item">\n' +
        //                 '<a class="sidebar-link" href="#">\n' +
        //                 '<i class="mdi mdi-adjust">xxx</i>\n' +
        //                 `<span class="menu">${i.name}</span>\n` +
        //                 '</a>\n' +
        //                 '</li>'
        //         }
        //     }
        // });
        //     callback(this.data)
    }
}
new TreeMenu({selector: "#sidebar", data: menu})
