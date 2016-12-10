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
package fractaldrawings;

import fractaldrawings.model.fractal.Mandelbrot;
import fractaldrawings.model.fractal.Multibrot;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author 41357205
 */
public class FractalDrawings {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int height = 720;
        final int width = 720;
        
        Multibrot multibrot = new Multibrot(width, height, 4);
        multibrot.setZoom(100);
        multibrot.generatePixels();
        
        //Save Fractal image...
        File output = new File("samples/sample_"+ new Date().getTime() +".png");
        try {
            ImageIO.write(multibrot.getImage(), "png", output);
        } catch (IOException ex) {
            Logger.getLogger(FractalDrawings.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Show Fractal in a Panel...
//        DrawingPanel drawing = new DrawingPanel(mandelbrot);
        
//        JFrame frame = new JFrame("Mandelbrot");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(width, height));
//        frame.getContentPane().add(drawing);
//        frame.pack();
//        frame.setVisible(true);

    }
    
}
