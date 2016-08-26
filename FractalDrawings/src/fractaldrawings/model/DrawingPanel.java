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
package fractaldrawings.model;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author 41357205
 */
public class DrawingPanel extends JPanel {

    private Fractal fractal;
    
    public DrawingPanel(Fractal fractal) {
        super();
        
        this.fractal = fractal;
        
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        ArrayList<Pixel> pixels = fractal.getPixels();
        
        Pixel currentPixel = null;
        
        while(!pixels.isEmpty()) {
            currentPixel = pixels.get(0);
            
            g.setColor(currentPixel.getColor());
            g.drawLine(currentPixel.getPixelX(), currentPixel.getPixelY(), 
                    currentPixel.getPixelX(), currentPixel.getPixelY());

            pixels.remove(0);
        }
    }
    
    
    
}
