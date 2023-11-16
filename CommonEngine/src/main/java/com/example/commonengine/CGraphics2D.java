package com.example.commonengine;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import javax.swing.JWindow;

import jdk.javadoc.internal.doclets.formats.html.markup.Text;

public interface CGraphics2D {

    //mover lo dibujado
    public void translate(int x, int y);
    //escalar lo dibujado
    public void scale(float x,float y);

    //dibuja el texto dado
    void drawText(String text, int x, int y);
    //dibuja la imagen dada
    void drawImage(Cimage image, int x, int y,int width, int height);

    //cambia el color usado usando ARGB
    public void setColor(int color);

    //Dibuja las formas correspondiendes a partir de los paramentros dados
    //La forma de representarse difiere en desktop y android
    public void fillRectangle(int cx, int cy, int width, int height);
    public void fillRoundRectangle(int cx, int cy, int width, int height, int arc,int s);
    public void drawRectangle(int cx, int cy, int width, int height);
    public void drawRoundRectangle(int cx, int cy, int width, int height, int arc,int s);
    public void drawLine(int initX, int initY, int endX, int endY);
    //void drawCircle(float cx, float cy, float radius){
    public void drawCircle(int cx, int cy, int width, int height);
    //void fillCircle(float cx, float cy, float radius){
    public void fillCircle(int cx, int cy, int width, int height);


    //Limpia la pantalla con un rectángulo blanco
    public void clear();

    //crea la imagen a partir de la ruta dada
    public Cimage createImage(String route);

    //Crea la fuente de texto con un tamaño y elección de estar en negrita y/o cursiva
    public CFont createFont(String fontname,int size,boolean bold,boolean italics);

    public void setFont(CFont fnt );






}
