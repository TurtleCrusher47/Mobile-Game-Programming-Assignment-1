package com.example.mgp2023;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class JoystickEntity implements EntityBase {

    public JoystickEntity()
    {
        
    }
    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {

    }

    @Override
    public void Init(SurfaceView _view) {

    }

    @Override
    public void Update(float _dt) {

    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public boolean IsInit() {
        return false;
    }

    @Override
    public int GetRenderLayer() {
        return 0;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

    @Override
    public String GetType() {
        return null;
    }
}
