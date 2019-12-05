package com.cas.picfg;

import org.bytedeco.opencv.opencv_core.Mat;

public abstract class Filter {
    public abstract Mat process(Mat image);
}
