package com.example.Backend.ml.inference;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class OpenCVTest {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat mat = Mat.eye(3, 3, 0);
        System.out.println("OpenCV loaded successfully");
        System.out.println(mat.dump());
    }
}