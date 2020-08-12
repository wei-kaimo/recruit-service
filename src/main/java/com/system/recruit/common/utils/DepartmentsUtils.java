package com.system.recruit.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.system.recruit.entity.HrDepartment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Weikaimo
 * @Date: 2019/8/23 0023 15:53
 */
public class DepartmentsUtils {
        /**
         * 针对二级菜单
         * treeMenuList:( ). <br/>
         * @author lishang
         * @param sourceList
         * @return
         */
        public static List<HrDepartment> treeDepartmentList(List<HrDepartment> sourceList){
            List<HrDepartment> targetList=new ArrayList<>();
            if (sourceList==null) {
                return null;
            }
            List<HrDepartment> pmenus=new ArrayList<>();
            for (HrDepartment department : sourceList) {
                if("0".equals(department.getParentDepartmentId())||department==null){
                    pmenus.add(department);
                }
            }
            sourceList.removeAll(pmenus);
            for (int i = 0; i < pmenus.size(); i++) {
                HrDepartment pmenu=pmenus.get(i);
                List<HrDepartment> cmenus=new ArrayList<>();
                for (HrDepartment hrDepartment : sourceList) {
                    if(pmenu.getDepartmentId().equals(hrDepartment.getParentDepartmentId())){
                        cmenus.add(hrDepartment);
                    }
                }
                pmenu.setChildren(cmenus);
                sourceList.removeAll(cmenus);
            }
            return targetList;
        }

        public static List<HrDepartment> treeRoot(List<HrDepartment> sourceList){


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
        public static HrDepartment treeRoot(List<HrDepartment> sourceList,HrDepartment rootMenu)
        {
            if (sourceList == null)
            {
                return null;
            }
            List<HrDepartment> childList=new ArrayList<>();
            for (HrDepartment hrDepartment : sourceList) {
                if(rootMenu.getDepartmentId().equals(hrDepartment.getParentDepartmentId())){
                    HrDepartment menuChild = treeRoot(sourceList, hrDepartment);
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
            List<HrDepartment> sourceList=new ArrayList<>();

            HrDepartment menu=new HrDepartment();
            menu.setParentDepartmentId(0);
            menu.setDepartmentId(1);
            menu.setDepartmentName("菜单一级");
            sourceList.add(menu);

            HrDepartment menu2=new HrDepartment();
            menu2.setParentDepartmentId(1);
            menu2.setDepartmentId(101);
            menu2.setDepartmentName("菜单二级1");
            sourceList.add(menu2);

            HrDepartment menu3=new HrDepartment();
            menu3.setParentDepartmentId(101);
            menu3.setDepartmentId(10101);
            menu3.setDepartmentName("菜单三级");
            sourceList.add(menu3);

            HrDepartment menu4=new HrDepartment();
            menu4.setParentDepartmentId(10101);
            menu4.setDepartmentId(1010101);
            menu4.setDepartmentName("菜单四级");
            sourceList.add(menu4);

            HrDepartment menu5=new HrDepartment();
            menu5.setParentDepartmentId(1);
            menu5.setDepartmentId(102);
            menu5.setDepartmentName("菜单二级2");
            sourceList.add(menu5);

            HrDepartment children = treeRoot(sourceList, menu);
            System.out.println(JSONObject.toJSON(children));
        }

}
