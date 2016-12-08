/*
 * The MIT License
 *
 * Copyright 2016 victor.
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

/**
 *
 * @author victor
 */
public class Multibrot extends Mandelbrot{
    
    private final String ILLEGAL_Z = "Z Value HAS to be either -1 "
                    + "or bigger than 2. For Z = 2, use regular Mandelbrot.";
    
    private int zValue;

    public Multibrot(int width, int height, int zValue) 
            throws IllegalArgumentException {
        super(width, height);
        
        if(zValue != -1 && zValue <= 2) 
            throw new IllegalArgumentException(ILLEGAL_Z);
        
        this.zValue = zValue;
    }
    
    @Override
    protected double getXDrawingValue(double x, double y, double x0) {
        switch(zValue) {
            case -1:
                return 0.0;
            case 3:
                return x*x*x - 3*x*y*y + x0;
            case 5:
                return Math.pow(x, 5) - 10*x*x*x*y*y + 5*x*y*y*y*y + x0;
            default:
                return Math.pow((x*x+y*y), (zValue/2)) * 
                        Math.cos(zValue * Math.atan2(y, x)) + x0;
        }
    }
    
    @Override
    protected double getYDrawingValue(double x, double y, double y0) {
        switch(zValue) {
            case -1:
                return 0.0;
            case 3:
                return 3*x*x*y - y*y*y + y0;
            case 5:
                return 5*Math.pow(x, 4)*y - 10*x*x*y*y*y + Math.pow(y, 5) + y0;
            default:
                return Math.pow((x*x+y*y), (zValue/2)) * 
                        Math.sin(zValue * Math.atan2(y, x)) + y0;
        }
    }

    public int getzValue() {
        return zValue;
    }

    public void setzValue(int zValue) {
        if(zValue != 1 && zValue <= 2)
            throw new IllegalArgumentException(ILLEGAL_Z);
        
        this.zValue = zValue;
    }
 
}
