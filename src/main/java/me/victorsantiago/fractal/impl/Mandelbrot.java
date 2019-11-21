/*
 * The MIT License
 *
 * Copyright 2016 Victor Santiago.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package me.victorsantiago.fractal.impl;

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.Color;

import lombok.Builder;
import lombok.Getter;

import me.victorsantiago.fractal.Fractal;

/**
 * Implementation of a Mandelbrot set.
 *
 * @author Victor Santiago
 */
public class Mandelbrot implements Fractal {

    // TODO: Replace magic numbers with more meaningful variables.

    private final int height, width, numberOfInteractions;
    private final double zoom, horizontalAlignment, verticalAlignment;
    private final Color interiorColor;

    @Getter
    private final Color[][] pixels;

    @Builder(toBuilder = true)
    public Mandelbrot(int height, int width, int numberOfInteractions, Color interiorColor, double zoom,
                      double horizontalAlignment, double verticalAlignment) {
        checkArgument(height > 0, "Height should be bigger than zero!");
        checkArgument(width > 0, "Width should be bigger than zero!");

        this.height = height;
        this.width = width;
        this.numberOfInteractions = numberOfInteractions;
        this.interiorColor = interiorColor;
        this.zoom = zoom;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.pixels = this.generateImage();
    }

    private Color[][] generateImage() {
        Color[][] mandelbrotSetPixels = new Color[height][width];
        final double actualZoomValue = 100.0 / (100.0 + zoom);

        final double horizontalDelta = 4.00 / width * actualZoomValue;
        final double verticalDelta = 4.00 / height * actualZoomValue;

        double x0, y0, currentHorizontalValue, currentVerticalValue, temporaryValue;
        int currentIteration;

        for(int column = 0; column < mandelbrotSetPixels.length; column++) {
            for(int line = 0; line < mandelbrotSetPixels[column].length; line++) {
                x0 = (column - horizontalAlignment) * horizontalDelta;
                y0 = (line - verticalAlignment) * verticalDelta;

                currentHorizontalValue = 0;
                currentVerticalValue = 0;
                currentIteration = 0;

                while((currentHorizontalValue*currentHorizontalValue + currentVerticalValue*currentVerticalValue) <= 4
                        && currentIteration < numberOfInteractions) {
                    temporaryValue = getHorizontalDrawingValue(currentHorizontalValue, currentVerticalValue, x0);

                    currentVerticalValue = getVerticalDrawingValue(currentHorizontalValue, currentVerticalValue, y0);
                    currentHorizontalValue = temporaryValue;

                    currentIteration++;
                }

                if(currentIteration < numberOfInteractions) {
                    mandelbrotSetPixels[column][line] = getExteriorColor(currentIteration);
                } else {
                    mandelbrotSetPixels[column][line] = interiorColor;
                }
            }
        }

        return mandelbrotSetPixels;
    }

    private Color getExteriorColor(int iteration) {
        return new Color((200 % iteration) + 50, 0, 0);
    }

    protected double getHorizontalDrawingValue(double x, double y, double x0) {
        return x*x - y*y + x0;
    }

    protected double getVerticalDrawingValue(double x, double y, double y0) {
        return 2*x*y + y0;
    }
}
