/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dat.lib.NoDuplicatesList;
import bus.BezierCurve.ControlPoint;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author dpf
 */
public class Canvas extends JPanel{

    private static final Color DEFAULT_BG_COLOR = Color.WHITE;
    
    private boolean antialiasing;
    private boolean showControl;
    
    private MouseAdapter mouseAdapter;
    private List<BezierCurve> shapes;

    public Canvas() {
        this.antialiasing = true;
        this.showControl = true;
        this.mouseAdapter = null;
        init();
    }
    
    public final void init(){
        this.setBackground(DEFAULT_BG_COLOR);
        this.shapes = new NoDuplicatesList<>();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (antialiasing) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        shapes.stream().forEach((BezierCurve shape) -> {
            drawShape(g2d, shape.getBezierShape());
            if(showControl){
                drawShape(g2d, shape.getPolygonShape());
                drawShape(g2d, shape.getPointsShape());
            }
        });
    }

    private void drawShape(Graphics2D g2d, StyledShape shape) {
        g2d.setPaint(shape.getColor());
        g2d.setStroke(shape.getStroke());
        g2d.draw(shape.getShape());
    }

    public void addShape(BezierCurve shape) {
        shapes.add(shape);
    }
    
    public void removeShape(BezierCurve shape) {
        shapes.remove(shape);
    }
    
    public boolean containsShape(BezierCurve shape){
        return shapes.contains(shape);
    }
    
    public void setMouseAdapter(MouseAdapter mouseAdapter, Cursor cursor){
        if (this.mouseAdapter != null) {
            this.removeMouseListener(this.mouseAdapter);
            this.removeMouseMotionListener(this.mouseAdapter);
        }
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
        this.mouseAdapter = mouseAdapter;
        this.setCursor(cursor);
    }
    
    public ControlPoint isControlPoint(double x, double y){
        ControlPoint controlPoint = null;
        for(BezierCurve bc : shapes){
            controlPoint = bc.isControlPoint(x, y);
            if(controlPoint != null){
                break;
            }
        }
        return controlPoint;
    }
    
    public List<BezierCurve> getShapes(){
        return shapes;
    }
    
    public void loadShapes(List<BezierCurve> shapes){
        this.shapes = shapes;
    }

    public boolean antialiasing() {
        return antialiasing;
    }

    public void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
    }

    public boolean showControl() {
        return showControl;
    }

    public void setShowControl(boolean showControl) {
        this.showControl = showControl;
    }
    
    

}
