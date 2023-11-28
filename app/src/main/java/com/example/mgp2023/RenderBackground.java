package com.example.mgp2023;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class RenderBackground implements EntityBase {
    private Bitmap bmp = null; //bmp is the name of the Bitmap to load.

	//7. Render a scrolling background
    private boolean isDone = false;
    private float xPos = 0;
    private float yPos = 0;
    int ScreenWidth, ScreenHeight; // Screen is based on Pixel hence we use a pixel.
    private Bitmap scaledbmp = null; // Stored the scaled version which is scaled based on Screen width and height.
   
    @Override
    public boolean IsDone(){
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone){
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view){
        //Load image from the resource
        bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.gamescene);

        //Screen Size
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight, true);
    }

    @Override
    public void Update(float _dt){
        //Scrolling background horizontally.
        xPos -= _dt * 500; // To deal with the speed of the scrolling.
        if(xPos < -ScreenWidth){
            xPos = 0;
        }
        //Check if xPos is decreased - screenwidth
        //if so, set xPos to 0, then can start to render the next image.
        //Scrollable background.
    }

    @Override
    public void Render(Canvas _canvas){
        //We draw 2 images of the same kind.
        //Once the 1st image reached 0 based on scrolling from my right to my left.
        //draw the next image.
        //Draw = render
        _canvas.drawBitmap(scaledbmp, xPos, yPos, null);
        _canvas.drawBitmap(scaledbmp, xPos + ScreenWidth, yPos, null);
        //xPos will change and yPos which is set as 0 will be no change.
    }

    @Override
    public boolean IsInit(){
        return bmp != null;
    }

    @Override
    public int GetRenderLayer(){
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){return ENTITY_TYPE.ENT_DEFAULT;}

//    @Override
//    public String GetType() {
//        return null;
//    }

    public static RenderBackground Create(){
        RenderBackground result = new RenderBackground();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }
}
