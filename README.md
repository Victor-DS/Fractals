# Fractals
Implementations of Fractal drawing algorithms in Java.

## Usage
Just create a new instance of your desired set, and generate the pixels.

Example:
```
Mandelbrot mandelbrot = new Mandelbrot.Builder(width, height)
  .zoom(1050)
  .maxNumberOfIterations(700)
  .xAxis(600)
  .yAxis(1000)
  .interiorColor(Color.BLACK)
  .build();
        
mandelbrot.generatePixels();
```

That will return you a 2D array of ```Colors```, one for each pixel of your desired height and width area, so you can paint it wherever you'd like.

## Screens
<img src="http://i.imgur.com/Qz660qz.png" width="200">

## LICENSE
```
The MIT License (MIT)

Copyright (c) 2016 Victor Santiago

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
