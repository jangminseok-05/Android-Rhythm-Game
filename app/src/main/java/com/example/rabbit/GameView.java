package com.example.rabbit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.media.MediaPlayer;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("ALL")
public class GameView extends View {

    Bitmap background, ground, rabbit; //background, ground, rabbit은 사진 이미지 활용
    Rect rectBackground, rectGround;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 25; //화면은 0.1초 간격 update
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float TEXT_SIZE = 120;
    int points = 0; //시작 점수는 0
    int life = 3; //주어진 기회 = 3
    static int dWidth, dHeight; //디스플레이 너비, 높이
    Random random;
    float rabbitX, rabbitY; //토끼 이미지의 (x,y)좌표값
    float oldX; //
    float oldRabbitX; //
    ArrayList<Note> notes; //노트 리스트
    ArrayList<Explosion> explosions;
    MediaPlayer mediaPlayer;


    int timer;
    //테스트
    Paint timeTest = new Paint();


    ArrayList<Note_info> SheetOfMusic;//악보(변수)


    public GameView(Context context) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.background_down);
        rabbit = BitmapFactory.decodeResource(getResources(), R.drawable.santa_xs);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        rectBackground = new Rect(0, 0, dWidth, dHeight);
        rectGround = new Rect(0, dHeight - ground.getHeight(), dWidth, dHeight);
        handler = new Handler();
        runnable = this::invalidate;
        textPaint.setColor(Color.rgb(255, 165, 0));
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTypeface(ResourcesCompat.getFont(context, R.font.bestfriend));
        healthPaint.setColor(Color.GREEN);
        random = new Random();
        rabbitX = dWidth / 2 - rabbit.getWidth() / 2;
        rabbitY = dHeight - ground.getHeight() - rabbit.getHeight();
        notes = new ArrayList<>();
        explosions = new ArrayList<>();

        SheetOfMusic s = new SheetOfMusic();//악보클래스의객체생성(이름겹친건 바꾸기 조금귀찮아서 그냥둠)
        s.setSheetOfMusic_1();
        SheetOfMusic = s.getSheetOfMusic_1();

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.jingglebell);
        mediaPlayer.start();
        timer = 0;
    }


    //기본적인 메카니즘은
    //notes는 실제 화면에 그려지고있는것들이고(PART2)
    //notes에 추가(PART1),삭제(PART3)를 해준다



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rectBackground, null);
//        canvas.drawBitmap(ground, null, rectGround, null);
        canvas.drawBitmap(rabbit, rabbitX, rabbitY, null);
        timer += 32;//시간카운트 기본적으로 50(0.05초)에한번 화면을 다시 그림
        canvas.drawText(Integer.toString(timer), 800,TEXT_SIZE, textPaint);
        //PART1
        //노트정보를 담은 아이템을 꺼내서 이 정보를 바탕으로 노트를 생성및notes에추가
       // ArrayList<Note_info> toremove_ = new ArrayList<>();
        for (Note_info item : SheetOfMusic) {
            if (item.startTime >= timer + 50){
                break;
            }
            if (item.isused == 0 && item.startTime <= timer) {
                Note note = new Note(context, item.noteX, item.noteY, item.noteVelocity, item.type);
                notes.add(note);
                item.isused = 1;
                //toremove_.add(item);
            }
        }
//        for(Note_info item: toremove_){
//            SheetOfMusic.remove(item);
//        }

        //만약 악보가 길어지면
        //여기서 매번 썻던아이템을 지우면 프로그램이 좀더빨리지긴할듯?

        //PART2
        //notes을 화면에 그리기
        for (int i = 0; i < notes.size(); i++) { //현재 노트위치 전부 탐색
            //노트 그리기
//            if (frame < 3) notes.get(i).setPosition(track[frame++]);
            canvas.drawBitmap(notes.get(i).getNote(0), notes.get(i).noteX, notes.get(i).noteY, null);

//            if (frame<10)notes.get(i).noteFrame++; //노트 모양 변경
//            if (notes.get(i).noteFrame > 2){
//                notes.get(i).noteFrame = 0;
//            }

            notes.get(i).noteY += notes.get(i).noteVelocity;// 속도만큼 위치 이동 -> 속도 구현

            //바닥과 충돌 확인 (노트 y좌표 + 노트 이미지 높이 >= 화면 높이 - 땅 이미지 높이)? 아래방향+
            if (notes.get(i).noteY + notes.get(i).getNoteHeight() >= dHeight - ground.getHeight()) {
                //points-=10;
                life--;
                notes.get(i).resetNoteisExistence();
                if (life == 0) {
                    mediaPlayer.pause();
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("points", points);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }

            }
        }

        for (int i = 0; i < notes.size(); i++) { //현재 노트위치 전부 탐색
            // rabbit과 충돌 확인, 가로방향 오른쪽 + 및 세로방향 아래쪽 +
            double modify = 0.8; //실제 보이는 이미지 판정과 값의 오차만큼 수정, 이미지 크기 줄일수록 판정 빠듯해짐
            if (notes.get(i).noteX + notes.get(i).getNoteWidth() >= rabbitX// 노트 오른쪽 > 토끼 왼쪽
                    && notes.get(i).noteX <= rabbitX + rabbit.getWidth()// 노트 왼쪽 < 토끼 오른쪽
                    && notes.get(i).noteY + notes.get(i).getNoteWidth() * modify >= rabbitY // 노트 아래쪽 > 토끼 위쪽
            ) {//&& notes.get(i).noteY + notes.get(i).getNoteWidth() <= rabbitY + rabbit.getHeight()){
                //life--;
                points += 10;

                notes.get(i).resetNoteisExistence();

                Explosion explosion = new Explosion(context);
                explosion.explosionX = (int) rabbitX;
                explosion.explosionY = (int) rabbitY; //토끼 머리로 폭발 위치 초기화
                explosions.add(explosion);

                //notes.get(i).resetPosition();//충돌한 노트 위치 초기화
//                if (frame<track.length) notes.get(i).setPosition(track[frame++]);
//                else notes.get(i).resetPosition();
            }
        }

        for (int i = 0; i < explosions.size(); i++) {
            canvas.drawBitmap(explosions.get(i).getExplosion(explosions.get(i).explosionFrame), explosions.get(i).explosionX,
                    explosions.get(i).explosionY, null);
            explosions.get(i).explosionFrame++;
            if (explosions.get(i).explosionFrame > 3) {
                explosions.remove(i);
            }
        }
//
//        if (life == 2) {
//            rabbit = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit1);
//            healthPaint.setColor(Color.YELLOW);
//        } else if (life == 1) {
//            healthPaint.setColor(Color.RED);
//        }

        //PART3
        //notes에 바닥충동or토끼부딛침에의해서 없어진것들을 회수
        ArrayList<Note> toremove = new ArrayList<>();
        for(Note note:notes){
            if (note.isExistence == 0){
                toremove.add(note);
            }
        }

        for (Note note : toremove) {
            System.out.println("here");
            notes.remove(note);
        }



        canvas.drawRect(-200, 30, dWidth - 200 + 60 * life, 80, healthPaint);
        canvas.drawText("" + points, 20, TEXT_SIZE, textPaint);
//        if(points>100){
//            rabbit = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit2);
//
//        }


        handler.postDelayed(runnable, UPDATE_MILLIS);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if (touchY >= rabbitY) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                oldX = event.getX();
                oldRabbitX = rabbitX;
            }
            if (action == MotionEvent.ACTION_MOVE) {
                float shift = oldX - touchX;
                float newRabbitX = oldRabbitX - shift;
                if (newRabbitX <= 0)
                    rabbitX = 0;
                else if (newRabbitX >= dWidth - rabbit.getWidth())
                    rabbitX = dWidth - rabbit.getWidth();
                else
                    rabbitX = newRabbitX;
            }
        }
        return true;
    }

}