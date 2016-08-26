/*
 * The MIT License
 *
 * Copyright 2016 41357205.
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
package fractaldrawings.model.fractal;

import fractaldrawings.model.Fractal;
import fractaldrawings.model.Pixel;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author 41357205
 */
public class Mandelbrot implements Fractal {
    
    private int height, width, maxNumberOfIterations;
    private ArrayList<Pixel> pixels;
    private double zoom, xAlign, yAlign;

    public Mandelbrot(int height, int width) {
        this.height = height;
        this.width = width;
        
        this.zoom = 1.0;
        this.maxNumberOfIterations = 400;
        this.xAlign = width/2;
        this.yAlign = height/2;
        
        pixels = new ArrayList<Pixel>();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getMaxNumberOfIterations() {
        return maxNumberOfIterations;
    }

    public void setMaxNumberOfIterations(int maxNumberOfIterations) {
        this.maxNumberOfIterations = maxNumberOfIterations;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public double getxAlign() {
        return xAlign;
    }

    public void setxAlign(double xAlign) {
        this.xAlign = xAlign;
    }

    public double getyAlign() {
        return yAlign;
    }

    public void setyAlign(double yAlign) {
        this.yAlign = yAlign;
    }
    
        private void draw() {
        if(!pixels.isEmpty()) pixels.clear();
                                
        final double xDelta = 4.00 / width * zoom;
        final double yDelta = 4.00 / height * zoom;
                
        double x0, y0, x, y, xTemp;
        int currentIteration = 0;
        
        for(int column = 0; column < width; column++) {
            for(int line = 0; line < height; line++) {
                x0 = (column - xAlign) * xDelta;
                y0 = (line - yAlign) * yDelta;
                
                x = 0;
                y = 0;
                currentIteration = 0;
                
                while((x*x + y*y) <= 4 
                        && currentIteration < maxNumberOfIterations) {
                    xTemp = x*x - y*y + x0;
                    
                    y = 2*x*y + y0;
                    x = xTemp;
                    
                    currentIteration++;
                }
                
                if(currentIteration < maxNumberOfIterations) //Exterior
                    pixels.add(new Pixel(line, column, 
                            new Color(0, ((currentIteration % 150) + 100), 0)));
                else //Interior
                    pixels.add(new Pixel(line, column, 
                            new Color(0, 0, 0)));
            }
        }
    }


    @Override
    public ArrayList<Pixel> getPixels() {
        return this.pixels;
    }

    @Override
    public ArrayList<Pixel> generatePixels() {
        draw();
        
        return getPixels();
    }
    
    
}
