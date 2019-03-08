package com.xmj.springbootdemo.util;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;

/**
 * Description:开启摄像头
 * Author: xieMengJie
 * CreateDate: 2019/3/4 18:27
 */
public class ShowVedio {

    public static void main(String[] args) {
        Webcam webcam = Webcam.getDefault();

        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel panel = new WebcamPanel(webcam);

        panel.setFPSDisplayed(true);

        panel.setDisplayDebugInfo(true);

        panel.setImageSizeDisplayed(true);

        panel.setMirrored(true);

        JFrame window = new JFrame("Test webcam panel");

        window.add(panel);

        window.setResizable(true);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.pack();
        //true为显示摄像头窗口.false隐藏
        window.setVisible(false);
    }

}
