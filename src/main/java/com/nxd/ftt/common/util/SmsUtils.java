package com.nxd.ftt.common.util;

public class SmsUtils {
    //赛邮
    private static String appid = "16391";
    private static String project = "";
    private static String signature = "828f4967c010df4df740243bb1926e99";
    private static String postUrlSaiYou = "https://api.mysubmail.com/message/send";
    private static String postUrlSaiYouGuoJi = "https://api.mysubmail.com/internationalsms/send";
    private static String sign = "【nxd】";

    public static void main(String[] args) {
//        SmsUtils.sendSMS("Your verification code is: 123456,keep it to yourself!","18251912734");
//        SmsUtils.sendSMS("您的验证码是：123456。请不要把验证码泄露给其他人。","18251912734");
    }

//    public static ResultModel sendSMS(String content, String mobile){
//        ResultModel result = new ResultModel();
//        if(content == null){
//            return result;
//        }
//        Map<String, String> params = new HashMap<>();
//        try {
//            params.put("appid", Const.APPID);
//            params.put("content", sign + content);
//            params.put("to", mobile);
//            params.put("signature", Const.SIGNATURE);
////            params.put("ExtNo","");//用户定义扩展码，只能为正整数
//            String response = InterfaceUtil.httpPostRequest(postUrlSaiYou, params);
//            JSONObject jsonObject = JSONObject.parseObject(response);
//            if (jsonObject.getString("status").equals("success")) {
//                result.setResult(ResultEnum.SUCCESS);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}