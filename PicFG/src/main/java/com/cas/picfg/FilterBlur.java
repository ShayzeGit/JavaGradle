package com.cas.picfg;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;

public class FilterBlur extends Filter {
    @Override
    public Mat process(Mat image) {
        int size = 21;
        Mat result = image.clone();
        GaussianBlur(image, result, new Size(size, size), 0);
        return result;
    }
}
