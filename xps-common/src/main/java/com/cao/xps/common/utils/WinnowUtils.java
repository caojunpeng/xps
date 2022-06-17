package com.cao.xps.common.utils;

/**
 * windows常用功能工具类
 */
public class WinnowUtils {
    private static Runtime runtime = Runtime.getRuntime();

    /**
     * 打开画图
     * @return
     */
    public static String runPicture() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\mspaint.exe");
            msg="画图打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="画图打开失败";
        }
        return msg;
    }

    /**
     * 打开截图
     */
    public static String runScreenshot() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\SnippingTool.exe");
            msg="截图打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="截图打开失败";
        }
        return msg;
     }
    /**
     * 打开计算器
     */
    public static String runCalc() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\calc.exe");
            msg="计算器打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="计算器打开失败";
        }
        return msg;
    }
    /**
     * 打开便签
     */
    public static String runStikyNot() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\StikyNot.exe");
            msg="便签打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="便签打开失败";
        }
        return msg;
    }
    /**
     * 打开录音机
     */
    public static String runSoundRecorder() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\SoundRecorder.exe");
            msg="录音机打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="录音机打开失败";
        }
        return msg;
    } /**
     * 打开记事本
     */
    public static String runNotepad() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\notepad.exe");
            msg="记事本打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="记事本打开失败";
        }
        return msg;
    } /**
     * 打开滑动关机
     */
    public static String runSlideToShutDown() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\SlideToShutDown.exe");
            msg="滑动关机打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="滑动关机打开失败";
        }
        return msg;
    } /**
     * 打开IE浏览器
     */
    public static String runiexplore() {
        String msg="";
        try{
            runtime.exec("C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
            msg="IE浏览器打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="IE浏览器打开失败";
        }
        return msg;
    } /**
     * 打开控制面板
     */
    public static String runControl() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\control.exe");
            msg="控制面板打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="控制面板打开失败";
        }
        return msg;
    } /**
     * 打开亮度和电池
     */
    public static String runMblctr() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\mblctr.exe");
            msg="亮度和电池打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="亮度和电池打开失败";
        }
        return msg;
    } /**
     * 打开系统信息
     */
    public static String runMsinfo32() {
        String msg="";
        try{
            runtime.exec("C:\\Windows\\System32\\msinfo32.exe ");
            msg="系统信息打开成功";
        }catch(Exception ie){
            System.out.println();
            msg="系统信息打开失败";
        }
        return msg;
    }



}
