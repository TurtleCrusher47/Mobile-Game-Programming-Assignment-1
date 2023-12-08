package com.example.mgp2023;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class HeartUIEntity implements EntityBase{

    private boolean isDone = false;
    private boolean isInit = false;

    private boolean isShown = true;

    private Bitmap bmpP = null;

    public int xPos, yPos;
    @Override
    public boolean IsDone() { return isDone; }

    @Override
    public void SetIsDone(boolean _isDone) { isDone = _isDone; }

    @Override
    public void Init(SurfaceView _view) {

        Bitmap bitmap = ResourceManager.Instance.GetBitmap(R.drawable.heart);
        bmpP = Bitmap.createScaledBitmap(bitmap, 100, 100, true);

    }

    @Override
    public void Update(float _dt) {

    }

    @Override
    public void Render(Canvas _canvas) {

        if (isShown)
        {
            _canvas.drawBitmap(bmpP, xPos - bmpP.getWidth() *0.5f, yPos -bmpP.getHeight() *0.5f, null);
        }

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.UI_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    public static HeartUIEntity Create(int _xPos, int _yPos)
    {
        HeartUIEntity result = new HeartUIEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_HEART);
        result.xPos = _xPos;
        result.yPos = _yPos;
        return result;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_HEART;
    }

    @Override
    public String GetType() {
        return "HeartEntity";
    }

    public boolean GetIsShown() { return isShown;}
    public void SetIsShown(boolean _isShown) {
        isShown = _isShown;
    }
}
