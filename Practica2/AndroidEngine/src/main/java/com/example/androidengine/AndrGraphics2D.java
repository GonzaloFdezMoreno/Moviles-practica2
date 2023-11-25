package com.example.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;

public class AndrGraphics2D {


    private SurfaceView sfView;
    private SurfaceHolder holder;
    private Canvas canvas;
    private Typeface font;
    private Paint paint;
    int posx,posy;
    private AssetManager aMngr;
    float hei,wid;

    private float scaleFactor;


    public AndrGraphics2D(int h, int w, SurfaceView view,Context cntxt){

        this.sfView = view;
        this.holder = this.sfView.getHolder();
        this.paint = new Paint();
        this.paint.setColor(0xFFFFFFFF);

        hei=h;
        wid=w;
        //save();
        paint.setStyle(Paint.Style.FILL);




        this.aMngr=cntxt.getAssets();


    }


    public void translate(int x, int y){

        canvas.translate(x,y);


    }
    public void scale(float x,float y){

        canvas.scale(x,y);

    }



    public void drawText(String text, int x, int y) {

        paint.setTypeface(font);

        canvas.drawText(text, x, y, paint);
    }


    public void setColor(int color) {
        paint.setColor(color);

    }


    public void drawImage(AndroidImage image, int x, int y,int width,int height) {
        AndroidImage andImg= image;
        Rect src=new Rect();
        src.left=0;
        src.top=0;
        src.bottom=andImg.getHeight();
        src.right=andImg.getWidth();
        Rect dst=new Rect();
        dst.left=x;
        dst.top=y;
        dst.bottom=y+height;
        dst.right=x+width;

        this.canvas.drawBitmap(andImg.getImage(), src, dst, this.paint);

    }

    //para los rectangulos y derivados se debe interpertar como x,y=top,left
    // y width y height como suma para obtener right y bottom

    public void fillRectangle(int cx, int cy, int width, int height) {
        this.canvas.drawRect(cx,cy,cx+width,cy+height,paint);

    }


    public void fillRoundRectangle(int cx, int cy, int width, int height, int arc, int s) {
        this.canvas.drawRoundRect(cx,cy,cx+width,cy+height,arc,s,paint);
    }


    public void drawRectangle(int cx, int cy, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        this.canvas.drawRect(cx,cy,cx+width,cy+height,paint);
        paint.setStyle(Paint.Style.FILL);

    }


    public void drawRoundRectangle(int cx, int cy, int width, int height, int arc, int s) {
        paint.setStyle(Paint.Style.STROKE);
        this.canvas.drawRoundRect(cx,cy,cx+width,cy+height,arc,s,paint);
        paint.setStyle(Paint.Style.FILL);
    }


    public void drawLine(int initX, int initY, int endX, int endY) {
        this.canvas.drawLine(initX,initY,endX,endY,paint);

    }

    //para los circulos cx y cy son el centro del circulo y para el tamaño el radio

    public void drawCircle(int cx, int cy, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        this.canvas.drawCircle(cx+width/2,cy+width/2,width/2,paint);
        paint.setStyle(Paint.Style.FILL);

    }


    public void fillCircle(int cx, int cy, int width) {
        this.canvas.drawCircle(cx+width/2,cy+width/2,width/2,paint);
    }


    public void clear() {


        paint.setColor(0xFFFFFFFF); // ARGB
        canvas.drawRect(0,0,sfView.getWidth(),sfView.getHeight(),paint);
        paint.setColor(0xFF000000); // ARGB


    }

    //prepara el renderizado limpiando la pantalla y escalandola
    public void preparaFrame() {
        while (!this.holder.getSurface().isValid());

        canvas = holder.lockCanvas();

        this.clear();
        if(sfView.getWidth()/wid>=sfView.getHeight()/hei){
            scaleFactor=sfView.getHeight()/hei;
        }
        else{
            scaleFactor=sfView.getWidth()/wid;
        }
        scale(scaleFactor,scaleFactor);

        posx=(int)(sfView.getWidth()/2-wid*scaleFactor/2);
        posy=(int)(sfView.getHeight()/2-hei*scaleFactor/2);

        translate(posx,posy/2);

    }

    //post renderizado
    public void terminaFrame() {
        this.holder.unlockCanvasAndPost(canvas);


    }

    public AndroidImage createImage(String route) {
        Bitmap btm=null;
        try{
            InputStream is=this.aMngr.open(route);
            btm = BitmapFactory.decodeStream(is);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        AndroidImage adImg=new AndroidImage(btm);


        return adImg;
    }


    public AndroidFont createFont(String fontname, int size, boolean bold, boolean italics) {

        paint.setTextSize(size);
        return new AndroidFont(fontname,aMngr,size,bold,italics);
    }


    public void setFont(AndroidFont fnt) {
        AndroidFont andft= (AndroidFont) fnt;


        font=andft.getFont();

    }

    //Transformar los eventos a la inversa de
    //lo escalado para coincidir con las dimensiones logicas
    public int refactorX(int k){
        //hacer la inversa de la traslacion y la escala
        return (int)(k/scaleFactor)-posx;

    }
    public int refactorY(int k){
        //hacer la inversa de la traslacion y la escala
        return (int)(k/scaleFactor)-posy/2;

    }


}
