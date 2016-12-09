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
import java.awt.image.BufferedImage;

/**
 *
 * @author 41357205
 */
public class Mandelbrot implements Fractal {
    
    private final double ONE_OVER_LOG2 = 1.4426950408889634;
    
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
        
        for(int column = 0; column < pixels.length; column++) {
            for(int line = 0; line < pixels[column].length; line++) {
                x0 = (column - xAlign) * xDelta;
                y0 = (line - yAlign) * yDelta;
                
                x = 0;
                y = 0;
                currentIteration = 0;
                
                while((x*x + y*y) <= 4
                        && currentIteration < maxNumberOfIterations) {
                    xTemp = getXDrawingValue(x, y, x0);
                    
                    y = getYDrawingValue(x, y, y0);
                    x = xTemp;
                    
                    currentIteration++;
                }
                
                if(currentIteration < maxNumberOfIterations) //Exterior
                    pixels[column][line] = getExteriorColor(currentIteration);
                else //Interior
                    pixels[column][line] = interiorColor;
            }
        }
    }
    
    private Color getExteriorColor(int iteration) {
        return new Color((200 % iteration) + 50, 0, 0);
    }
    
    protected double getXDrawingValue(double x, double y, double x0) {
        return x*x - y*y + x0;
    }
    
    protected double getYDrawingValue(double x, double y, double y0) {
        return 2*x*y + y0;
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
    
    public BufferedImage getImage() {
        BufferedImage bImage = new BufferedImage(pixels.length, 
                pixels[0].length, BufferedImage.TYPE_INT_ARGB);
        
        for(int x = 0; x < pixels.length; x++)
            for(int y = 0; y < pixels[0].length; y++)
                bImage.setRGB(x, y, pixels[x][y].getRGB());
        
        return bImage;
    }
    
    public static class Builder {
        
        private Color interiorColor, pixels[][];
        private int maxNumberOfIterations, height, width;
        private double zoom, xAxis, yAxis;
        
        public Builder(final int width, final int height) 
            throws IllegalArgumentException {
            if(height <=0 || width <= 0)
                throw new IllegalArgumentException("Invalid height or width!");

            this.height = height;
            this.width = width;

            this.zoom = 0.0;
            this.maxNumberOfIterations = 400;
            this.xAxis = width/2;
            this.yAxis = height/2;

            interiorColor = new Color(0, 0, 0);

            pixels = new Color[width][height];
        }
        
        public Builder interiorColor(final Color color) {
            this.interiorColor = color;
            return this;
        }
        
        public Builder maxNumberOfIterations(final int max) {
            this.maxNumberOfIterations = max;
            return this;
        }
        
        public Builder zoom(final double zoom) {
            this.zoom = zoom;
            return this;
        }
        
        public Builder xAxis(final double x) {
            this.xAxis = x;
            return this;
        }
        
        public Builder yAxis(final double y) {
            this.yAxis = y;
            return this;
        }
        
        public Mandelbrot build() {
            Mandelbrot mandelbrot = new Mandelbrot(this.width, this.height);
            mandelbrot.setInteriorColor(interiorColor);
            mandelbrot.setMaxNumberOfIterations(maxNumberOfIterations);
            mandelbrot.setxAlign(xAxis);
            mandelbrot.setyAlign(yAxis);
            mandelbrot.setZoom(zoom);
            
            return mandelbrot;
        }
        
    }
    
}
