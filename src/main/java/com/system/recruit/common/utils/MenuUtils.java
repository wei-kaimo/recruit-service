package com.system.recruit.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.system.recruit.entity.HrMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Weikaimo
 * @Date: 2019/8/23 0023 15:53
 */
public class MenuUtils {
        /**
         * 针对二级菜单
         * treeMenuList:( ). <br/>
         * @author lishang
         * @param sourceList
         * @return
         */
        public static List<HrMenu> treeMenuList(List<HrMenu> sourceList){
            List<HrMenu> targetList=new ArrayList<>();
            if (sourceList==null) {
                return null;
            }
            List<HrMenu> pmenus=new ArrayList<>();
            for (HrMenu menu : sourceList) {
                if("0".equals(menu.getParentId())||menu==null){
                    pmenus.add(menu);
                }
            }
            sourceList.removeAll(pmenus);
            for (int i = 0; i < pmenus.size(); i++) {
                HrMenu pmenu=pmenus.get(i);
                List<HrMenu> cmenus=new ArrayList<>();
                for (HrMenu menu : sourceList) {
                    if(pmenu.getMenuId().equals(menu.getParentId())){
                        cmenus.add(menu);
                    }
                }
                pmenu.setChildren(cmenus);
                sourceList.removeAll(cmenus);
            }
            return targetList;
        }

        public static List<HrMenu> treeRoot(List<HrMenu> sourceList){


            return sourceList;
        }


        /**
         * 递归获取菜单
         * treeRoot:( ). <br/>
         * @author lishang
         * @param sourceList
         * @param rootMenu
         * @return
         */
        public static HrMenu treeRoot(List<HrMenu> sourceList,HrMenu rootMenu)
        {
            if (sourceList == null)
            {
                return null;
            }
            List<HrMenu> childList=new ArrayList<>();
            for (HrMenu menu : sourceList) {
                if(rootMenu.getMenuId().equals(menu.getParentId())){
                    HrMenu menuChild = treeRoot(sourceList, menu);
                    childList.add(menuChild);
                }
            }
            if(childList.size()==0){
                return rootMenu;
            }
            rootMenu.setChildren(childList);
            return rootMenu;
        }


        public static void main(String[] args) {
            List<HrMenu> sourceList=new ArrayList<>();

            HrMenu menu=new HrMenu();
            menu.setParentId(0);
            menu.setMenuId(1);
            menu.setMenuName("菜单一级");
            sourceList.add(menu);

            HrMenu menu2=new HrMenu();
            menu2.setParentId(1);
            menu2.setMenuId(101);
            menu2.setMenuName("菜单二级1");
            sourceList.add(menu2);

            HrMenu menu3=new HrMenu();
            menu3.setParentId(101);
            menu3.setMenuId(10101);
            menu3.setMenuName("菜单三级");
            sourceList.add(menu3);

            HrMenu menu4=new HrMenu();
            menu4.setParentId(10101);
            menu4.setMenuId(1010101);
            menu4.setMenuName("菜单四级");
            sourceList.add(menu4);

            HrMenu menu5=new HrMenu();
            menu5.setParentId(1);
            menu5.setMenuId(102);
            menu5.setMenuName("菜单二级2");
            sourceList.add(menu5);

            HrMenu children = treeRoot(sourceList, menu);
            System.out.println(JSONObject.toJSON(children));
        }

}
