package com.system.recruit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/28 15:09
 */
public class Main {
        public static void main(String[] args) {
            /*String strPhone = "";
            Pattern numTest=Pattern.compile("\\d{11}");
            Matcher num=numTest.matcher("158你你71301059，大声点");
            while(num.find()) {
                System.out.println(num.group());
                strPhone=num.group();

            }
            String str = "^0\\d{2,3}-?\\d{7,8}$|^(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])\\d{8}$";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(strPhone);
            if (m.find()){
                System.out.println(strPhone);
            }*/
            String nickName = "158你你71301059，大声点,ashodh,dq241443546.4570780.15057146027";
            char[] chars = nickName.toCharArray();
            ArrayList<String> charsList = new ArrayList<>();//所有11位字符
            for(int i = 0; i < chars.length; i++){
                StringBuilder stringBuilder = new StringBuilder();
                for(int j = 0; j < 11; j++){
                    if(i + j < chars.length){
                        stringBuilder.append(chars[i + j]);
                    }
                }
                if(stringBuilder.length()==11){
                    charsList.add(stringBuilder.toString());
                }
            }
            String str = "^0\\d{2,3}-?\\d{7,8}$|^(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])\\d{8}$";
            for (String s : charsList) {
                Pattern p = Pattern.compile(str);
                Matcher m = p.matcher(s);
                if (m.find()){
                    System.out.println(s);
                }
            }


            //checkNickName("158你你71301059，大声点,ashodh,dq241443546.4570780.15057146027");
        }
    public static List<String> checkNickName(String nickName) {
        char[] chars = nickName.toCharArray();
        ArrayList<String> phoneList = new ArrayList<>();//所有11位字符
        for(int i = 0; i < chars.length; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for(int j = 0; j < 11; j++){
                if(i + j < chars.length){
                    stringBuilder.append(chars[i + j]);
                }
            }
            if(stringBuilder.length()==11){
                phoneList.add(stringBuilder.toString());
            }
        }

        List<String> regexList = new ArrayList<String>();
        /**
         * 手机号码
         * 移动：134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
         * 联通：130,131,132,152,155,156,185,186
         * 电信：133,1349,153,180,189,181(增加)
         */
        regexList.add("^1(3[0-9]|5[0-35-9]|8[025-9])\\d{8}$");
        /**
         * 中国移动：China Mobile
         * 134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
         */
        regexList.add("^1(34[0-8]|(3[5-9]|5[017-9]|8[2378])\\d)\\d{7}$");
        /**
         * 中国联通：China Unicom
         * 130,131,132,152,155,156,185,186
         */
        regexList.add("^1(3[0-2]|5[256]|8[56])\\d{8}$");
        /**
         * 中国电信：China Telecom
         * 133,1349,153,180,189,181(增加)
         */
        regexList.add("^1((33|53|8[019])[0-9]|349)\\d{7}$");
        List restString = new ArrayList();
        for(String phone : phoneList){
            for (String regex : regexList) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(phone);
                if (matcher.matches()) {
                    System.out.println(matcher.group());
                    restString.add(matcher.group());
                }
            }
        }
        return restString;
    }
}
