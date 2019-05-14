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
public class BezierDraw {

    private static final int MAX_POINTS = 3;

    private static BezierDraw INSTANCE;

    private Canvas canvas;

    private BezierDraw() {
    }

    public static BezierDraw getInstance(Canvas canvas) {
        INSTANCE = new BezierDraw();
        INSTANCE.canvas = canvas;
        return INSTANCE;
    }

    public Cursor getCursor() {
        return new Cursor(Cursor.CROSSHAIR_CURSOR);
    }

    public MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {

            BezierCurve bc = null;
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

                if (e.getButton() == 1) {
                    if (bc == null) {
                        bc = new BezierCurve();   
                    } else if (bc.getDegree() == MAX_POINTS) {
                        ControlPoint cp = bc.getControlPoints().get(MAX_POINTS - 1);
                        bc = new BezierCurve();
                        bc.addPoint(cp);
                    } else if (bc.getDegree()==MAX_POINTS -1 && canvas.isControlPoint(e.getX(), e.getY()) != null){
                        ControlPoint cp = null;
                        for(BezierCurve b : canvas.getShapes()){
                            for(ControlPoint c: b.getControlPoints()){
                                if(c != controlPoint && c.getArea().contains(e.getX(), e.getY())){
                                    bc.addPoint(c.getX(), c.getY());
                                    bc = null;
                                    recompute();
                                    return;
                                }
                            }
                        }    
                    }
                    bc.addPoint(e.getX(), e.getY());
                    recompute();
                }

            }

            public void recompute() {
                if (bc != null) {
                    if (!canvas.containsShape(bc)) {
                        canvas.addShape(bc);
                    }
                    canvas.repaint();
                }
            }

        };

    }

}
