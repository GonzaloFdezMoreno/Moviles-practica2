package com.example.desktopengine;

import com.example.commonengine.CFont;
import com.example.commonengine.CGraphics2D;
import com.example.commonengine.Cimage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import jdk.javadoc.internal.doclets.formats.html.markup.Text;
import sun.font.TrueTypeFont;


public class DesktopGraphics2D implements CGraphics2D {

    private Graphics2D grph;
    private BufferStrategy bufferStrategy;
    JFrame frm;
    Color clr;
    String rou="./Assets/";
    int posx,posy=0;
    float hei,wid;
    float scaleFactor;
    AffineTransform atr;
    int top, bottom,left,right;
    boolean vertical=false;

    DesktopGraphics2D(float w,float h,JFrame frame){

        hei=h;
        wid=w;
        //save();

        frm=frame;
        //definir insets
        top= frm.getInsets().top;
        bottom= frm.getInsets().bottom;
        left= frm.getInsets().left;
        right= frm.getInsets().right;
        this.bufferStrategy =frame.getBufferStrategy();
        grph=(Graphics2D) this.bufferStrategy.getDrawGraphics();

        //guardar transform original
        atr= grph.getTransform();



    }

    @Override
    public void translate(int x, int y){

        grph.translate(x,y);


    }
    @Override
    public void scale(float x,float y){

        grph.scale(x,y);

    }



    @Override
    public void drawText(String text, int x, int y) {
            grph.drawString(text,x,y);
    }


    @Override
    public void drawImage(Cimage image, int x, int y,int width,int height) {
        DesktopImage dImage=(DesktopImage) image;

        //grph.drawImage(dImage.getImage(),x,y,null);
        grph.drawImage(dImage.getImage(),x,y,width,height,null);

    }


    public void setPaintMode(){
        grph.setPaintMode();
    }
    @Override
    public void setColor(int color){
        this.clr=new Color(color,false);
        grph.setColor(this.clr);

    }
    @Override
    public void fillRectangle(int cx, int cy, int width, int height){
        grph.fillRect(cx, cy, width, height);

    }
    @Override
    public void fillRoundRectangle(int cx, int cy, int width, int height, int arc,int s){
        grph.fillRoundRect(cx,cy,width,height,arc,s);
    }
    @Override
    public void drawRectangle(int cx, int cy, int width, int height){
        grph.drawRect(cx, cy, width, height);
    }
    @Override
    public void drawRoundRectangle(int cx, int cy, int width, int height, int arc,int s){
        grph.drawRoundRect(cx,cy,width,height,arc,s);
    }
    @Override
    public void drawLine(int initX, int initY, int endX, int endY){
        grph.drawLine(initX, initY, endX, endY);
    }
    @Override
    //void drawCircle(float cx, float cy, float radius){
    public void drawCircle(int cx, int cy, int width, int height){
        grph.drawOval(cx, cy, width, height);
    }
    @Override
    //void fillCircle(float cx, float cy, float radius){
    public void fillCircle(int cx, int cy, int width, int height){
        grph.fillOval(cx, cy, width, height);
    }

    @Override
    public void clear() {


        setColor(0xFFFFFF);
        grph.fillRect(0, 0, this.frm.getWidth(), this.frm.getHeight());
        setColor(0x000000);

    }

    //Pre renderizado de la escena, donde limpiamos la pantalla
    // y reescalamos para que se ajuste
    public void preparaFrame(){
        grph=(Graphics2D) this.bufferStrategy.getDrawGraphics();
        grph.setTransform(atr);
        clear();

        if(frm.getWidth()/wid>=frm.getHeight()/hei){
            scaleFactor=frm.getHeight()/hei;

            vertical=false;
        }
        else{
            scaleFactor=frm.getWidth()/wid;

            vertical=true;
        }
        scale(scaleFactor,scaleFactor);

        posx=(int)(frm.getWidth()/2-wid*scaleFactor/2);
        posy=(int)(frm.getHeight()/2-(int)hei*scaleFactor/2);

        if(vertical){

            translate(left,posy-bottom);

        }
        else{
            translate(posx,top);
        }

        //translate(posx,posy);


    }

    //post renderizado
    public void terminaFrame(){

        grph.dispose();

    }

    @Override
    public Cimage createImage(String route) {
        Image nImg=null;
        try{
        nImg =  ImageIO.read(new File(this.rou+route));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return new DesktopImage(nImg);
    }


    @Override
    public CFont createFont(String fontname, int size, boolean bold, boolean italics) {
       
        return new DesktopFont(fontname ,size,bold,italics);
    }

    @Override
    public void setFont(CFont fnt) {
        DesktopFont jFont=(DesktopFont) fnt;
        this.grph.setFont(jFont.GetFont());

    }


    //Transformar los eventos a la inversa de
    //lo escalado para coincidir con las dimensiones logicas
    public int refactorX(int x) {
        if(vertical){

            return (int)(x/scaleFactor)-left;

        }
        else  return (int)(x/scaleFactor)-posx;
    }
    public int refactorY(int y) {
        if(vertical){
            return (int)(y/scaleFactor)-posy+bottom;
        }
        else
            return (int)(y/scaleFactor)-top;
    }

    //Comprobaciones
    public boolean conRest(){return this.bufferStrategy.contentsRestored();}
    public boolean conLost(){return this.bufferStrategy.contentsLost();}

    //muestra el buffer
    public void show(){this.bufferStrategy.show();}



}
