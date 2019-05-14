/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import bus.BezierCurve.ControlPoint;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author dpf
 */
public class BezierMove {


    private static BezierMove INSTANCE;

    private Canvas canvas;

    private BezierMove() {
    }

    public static BezierMove getInstance(Canvas canvas) {
        INSTANCE = new BezierMove();
        INSTANCE.canvas = canvas;
        return INSTANCE;
    }

    public Cursor getCursor() {
        return new Cursor(Cursor.CROSSHAIR_CURSOR);
    }

    public MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {

            ControlPoint controlPoint = null;

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    controlPoint = canvas.isControlPoint(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (controlPoint != null) {
                    controlPoint.moveTo(e.getX(), e.getY());
                    recompute();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (controlPoint != null) {
                    if(canvas.isControlPoint(e.getX(), e.getY()) != null){
                        ControlPoint cp = canvas.isControlPoint(e.getX(), e.getY());
                        controlPoint.moveTo(cp.getX(), cp.getY());
                    }
                    recompute();
                    controlPoint = null;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            public void recompute() {
                canvas.repaint();
            }

        };

    }

}
