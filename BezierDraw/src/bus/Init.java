/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import bus.Canvas;
import pre.CanvasFrame;

/**
 *
 * @author dpf
 */
public class Init {
    
    private static final String APP_NAME = "Bezier Curves";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CanvasFrame canvasFrame = new CanvasFrame(new Canvas());
        canvasFrame.setTitle(APP_NAME);
        canvasFrame.setVisible(true);
        
    }
    
}
