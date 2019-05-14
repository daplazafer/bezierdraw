/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;

/**
 *
 * @author dpf
 */
public final class StyledShape {
    
    public enum Style{
        
        basic(new BasicStroke(1)),
        dashed(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        
        private final BasicStroke stroke;

        private Style(BasicStroke stroke) {
            this.stroke = stroke;
        }

        public BasicStroke getStroke() {
            return stroke;
        }
        
    }
    
    private final static Color DEFAULT_DRAW_COLOR = Color.BLACK;
    private final static Style DEFAULT_STYLE = Style.basic;
    
    private final Shape shape;
    private final Color color;
    private final BasicStroke stroke;
    
    public StyledShape(Shape shape){
        this(shape, DEFAULT_DRAW_COLOR, DEFAULT_STYLE);
    }
    
    public StyledShape(Shape shape, Style style){
        this(shape, DEFAULT_DRAW_COLOR, style);
    }

    public StyledShape(Shape shape, Color color, Style style) {
        this.shape = shape;
        this.color = color;
        this.stroke = style.getStroke();
    }

    public Shape getShape() {
        return shape;
    }
    
    public Color getColor() {
        return color;
    }

    public BasicStroke getStroke() {
        return stroke;
    }
    
}
