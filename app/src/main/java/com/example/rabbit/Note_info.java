package com.example.rabbit;

public class Note_info {
    int startTime;
    int noteX, noteY; //노트의 (x,y) 좌표값 및 속도
    int noteVelocity ;
    int isused = 0;
    int type;

    public Note_info(int startTime,int noteX,int noteY,int noteVelocity,int type) {
        this.startTime = startTime;
        this.noteX = noteX;
        this.noteY = noteY;
        this.noteVelocity = noteVelocity;
        this.type = type;
    }

}
//    타이머 를 활용해서 포문을 이용해 악보의 시간값을 잡아내고 타이머가 그시간 이상이 되면 자동으로
//        셋노트를 하고 캔버스에 그린다.
//        그러기 위해서 악보에 (시간,x,y,속도?)가 있어야 한다.
//
//        1. 타이머를 세팅을 한다.
//        2. 타이머의 시간을 받아온다.
//        3.포문을 이용해 악보를 10개정보만써보고, 타이머가 그시간이 지났을떄를 테스트 해본다.
//        악보를 객체 뽑아서 사용할지 아니면 그냥 그걸로할지는 미정 고민 좀 더 해보자
