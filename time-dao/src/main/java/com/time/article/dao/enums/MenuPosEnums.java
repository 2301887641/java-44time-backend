package com.time.article.dao.enums;

import lombok.Getter;

@Getter
public enum MenuPosEnums {
    /*导航*/
    NAV_LEFT("左边导航"),NAV_TOP("顶部菜单"),MODEL_NAV("模块导航"),MODEL_OPERATION("模块操作");
    private String name;
    MenuPosEnums(String name) {
        this.name = name;
    }
}
