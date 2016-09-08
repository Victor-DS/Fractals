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
import java.awt.Color;

/**
 *
 * @author 41357205
 */
public class Mandelbrot implements Fractal {
    
    private int height, width, maxNumberOfIterations;
    private Color pixels[][], interiorColor;
    private double zoom, xAlign, yAlign;

    public Mandelbrot(int width, int height) 
            throws IllegalArgumentException {
        if(height <=0 || width <= 0)
            throw new IllegalArgumentException("Invalid height or width!");
        
        this.height = height;
        this.width = width;
        
        this.zoom = 0.0;
        this.maxNumberOfIterations = 400;
        this.xAlign = width/2;
        this.yAlign = height/2;
        
        interiorColor = new Color(0, 0, 0);
        
        pixels = new Color[width][height];
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

    public Color getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(Color interiorColor) {
        this.interiorColor = interiorColor;
    }
    
    private void draw() {        
        final double realZoom = 100.0 / (100.0 + zoom);
                                
        final double xDelta = 4.00 / width * realZoom;
        final double yDelta = 4.00 / height * realZoom;
                
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
                    pixels[line][column] = new Color(25, 25, ((currentIteration % 200) + 50));
                else //Interior
                    pixels[line][column] = interiorColor;
            }
        }
    }

    @Override
    public Color[][] getPixels() {
        return this.pixels;
    }

    @Override
    public Color[][] generatePixels() {
        draw();
        
        return getPixels();
    }
    
    
}
