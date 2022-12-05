package com.example.rabbit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Note {
    Bitmap[] note = new Bitmap[4];
    int notetype = 0; //노트의 모양
    int noteX, noteY, noteVelocity; //노트의 (x,y) 좌표값 및 속도
    int isExistence = 1;
    Random random;



    public Note(Context context,int noteX,int noteY,int noteVelocity ,int notetype){
        note[0]= BitmapFactory.decodeResource(context.getResources(), R.drawable.box_full);
        note[1]= BitmapFactory.decodeResource(context.getResources(), R.drawable.box_bottom);
        note[2]= BitmapFactory.decodeResource(context.getResources(), R.drawable.box_middle);
        note[3]= BitmapFactory.decodeResource(context.getResources(), R.drawable.box_top);
        this.noteX = noteX;
        this.noteY = noteY;
        this.noteVelocity = noteVelocity;
        this.notetype = notetype;
    }

    public Bitmap getNote(int noteFrame){
        return note[this.notetype];
    }

    public int getNoteWidth(){
        return note[this.notetype].getWidth();
    } //노트 이미지의 너비

    public int getNoteHeight(){
        return note[this.notetype].getHeight();
    } //노트 이미지의 높이

    public void resetNoteisExistence(){
        this.isExistence = 0;
        return;
    }


    public void resetPosition(){// 후에 노트 위치를 직접 찍기 전까진 임시로 랜덤위치
       noteX=random.nextInt(GameView.dWidth - getNoteWidth()); // 오른쪽 방향 +
       noteY= -200 + random.nextInt(600) * -1;
       noteVelocity = 50;// + random.nextInt(16);

    }

    public void setPosition(Note_info Member){
//        noteX = trackMember[0];
//        noteY = trackMember[1];
//        noteVelocity=50;
    }
}
