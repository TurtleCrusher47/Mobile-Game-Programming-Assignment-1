package com.example.mgp2023;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


// Created by TanSiewLan2021

public class EntityManager
{
    public final static EntityManager Instance = new EntityManager();
    private LinkedList<EntityBase> entityList = new LinkedList<EntityBase>();
    private SurfaceView view = null;

    private EntityManager()
    {
    }

    public void Init(SurfaceView _view)
    {
        view = _view;
    }

    public void Update(float _dt)
    {
        LinkedList<EntityBase> removalList = new LinkedList<EntityBase>();

        // Update all
        for(int i = 0; i < entityList.size(); ++i)
        {
            // Lets check if is init, initialize if not
            if (!entityList.get(i).IsInit())
            {
                entityList.get(i).Init(view);
            }

            entityList.get(i).Update(_dt);

            // Check if need to clean up
            if (entityList.get(i).IsDone()) {
                // Done! Time to add to the removal list
                removalList.add(entityList.get(i));
            }
        }

        // Remove all entities that are done
        for (EntityBase currEntity : removalList) {
            entityList.remove(currEntity);
        }
        removalList.clear(); // Clean up of removal list

        // Collision Check
        for (int i = 0; i < entityList.size(); ++i)
        {
            EntityBase currEntity = entityList.get(i);

            if (currEntity instanceof ICollidableCircle)
            {
                ICollidableCircle first = (ICollidableCircle) currEntity;

                for (int j = i + 1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    // Circle to circle
                    if (otherEntity instanceof ICollidableCircle)
                    {
                        ICollidableCircle second = (ICollidableCircle) otherEntity;

                        if (Collision.CircleToCircle(first.GetPosX(), first.GetPosY(), first.GetRadius(), second.GetPosX(), second.GetPosY(), second.GetRadius()))
                        {
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                    //Circle to box
                    else if (otherEntity instanceof ICollidableBox)
                    {
                        ICollidableBox second = (ICollidableBox) otherEntity;

                        if (Collision.CircleToBox(first.GetPosX(), first.GetPosY(), first.GetRadius(), second.GetPosX(), second.GetPosY(), second.GetWidth(), second.GetHeight()))
                        {
                            //System.out.println("radius " + first.GetRadius());
                            //System.out.println("width " + second.GetWidth() + "height " + second.GetHeight());
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                }
            }
            // Box to box
            else if (currEntity instanceof ICollidableBox)
            {
                ICollidableBox first = (ICollidableBox) currEntity;

                for (int j = i+1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    if (otherEntity instanceof ICollidableBox)
                    {
                        ICollidableBox second = (ICollidableBox) otherEntity;

                        if (Collision.BoxToBox(first.GetPosX(), first.GetPosY(), first.GetWidth(), first.GetHeight(), second.GetPosX(), second.GetPosY(), second.GetWidth(), second.GetHeight()))
                        {
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                    // Box to circle
                    else if (otherEntity instanceof ICollidableCircle)
                    {
                        ICollidableCircle second = (ICollidableCircle) otherEntity;

                        if (Collision.CircleToBox(second.GetPosX(), second.GetPosY(), second.GetRadius(), first.GetPosX(), first.GetPosY(), first.GetWidth(), first.GetHeight()))
                        {
                            //System.out.println("radius " + second.GetRadius());
                            //System.out.println("width " + first.GetWidth() + "height " + first.GetHeight());
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                }
            }

            // Check if need to clean up
            if (currEntity.IsDone()) {
                removalList.add(currEntity);
            }
        }

        // Remove all entities that are done
        for (EntityBase currEntity : removalList) {
            entityList.remove(currEntity);
        }
        removalList.clear();
    }

    public void Render(Canvas _canvas)
    {
      
        // Use the new "rendering layer" to sort the render order
        Collections.sort(entityList, new Comparator<EntityBase>() {
            @Override
            public int compare(EntityBase o1, EntityBase o2) {
                return o1.GetRenderLayer() - o2.GetRenderLayer();
            }
        });

        for(int i = 0; i <entityList.size(); ++i)
        {
            entityList.get(i).Render(_canvas);
        }
    }

    public void AddEntity(EntityBase _newEntity, EntityBase.ENTITY_TYPE entity_type)
    {
        entityList.add(_newEntity);
    }

    public void Clean()
    {
        entityList.clear();
    }
}


