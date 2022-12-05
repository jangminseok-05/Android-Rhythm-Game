package com.example.rabbit;

import java.util.ArrayList;
public class SheetOfMusic {
    ArrayList<Note_info> sheetOfMusic;
    int timer = 0;

    public SheetOfMusic() {
        sheetOfMusic = new ArrayList<>();
    }
    //악보같은 경우에는 노래를 정하고 노래에 맞춰서 다시 제작해야함

    public void setSheetOfMusic_1() {

        //line_list = 레일, speed_list = 스피드, interval = 시간간격
        //여기서 필요에 의해서 노트에 대한 다른 속성까지 컨트롤 가능 (ex노트 종류)
        int[] line_list = {1, 2, 3, 4, 2, 2, 2, 4, 4, 4, 1, 1, 1, 1, 1, 3, 1, 4, 2, 3, 1, 4, 2, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 1, 1, 1, 4, 4, 4, 4, 2, 2, 2, 2, 3, 3, 3, 3, 1, 1, 1, 1, 4, 4, 4, 4, 2, 2, 2, 2, 3, 3, 3, 3, 1, 1, 1, 1, 4, 4, 4, 4, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 4, 4, 4, 1, 1, 1, 1, 1, 3, 1, 4, 2, 3, 1, 4, 2, 4, 1, 2, 3, 4, 1, 2, 3, 4, 2, 2, 2, 4, 4, 4, 1, 1, 1, 1, 1, 3, 1, 4, 2, 3, 1, 4, 2, 4, 1, 2, 3, 4, 1, 2, 3, 4};
        int[] interval  = {5, 5, 5, 5, 5, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,10, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1,10, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1,10, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 5, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 1, 1, 5, 1, 1, 5, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        int[] types =     {0, 0, 0, 0, 1, 2, 3, 1, 2, 3, 1, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 1, 2, 3, 1, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
       //int[] speed_list= {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};




        for (int i = 0; i < line_list.length; i++) {
            int l = line_list[i];
            int v = 7*5;//리스트가 깔끔하게 보일려고 10을 여기서 곱함
            int it = 110*interval[i];//1000 = 1초 150 = 1초/4 /실행시간고려해서 0.75초가 거의 1초
            int t = types[i];
            timer += it;

            switch (l) {
                case 1:
                    Note_info item = new Note_info(timer, 100, -400, v, t);
                    sheetOfMusic.add(item);
                    break;
                case 2:
                    Note_info item2 = new Note_info(timer, 300, -400, v, t);
                    sheetOfMusic.add(item2);
                    break;
                case 3:
                    Note_info item3 = new Note_info(timer, 500, -400, v, t);
                    sheetOfMusic.add(item3);
                    break;
                case 4:
                    Note_info item4 = new Note_info(timer, 700, -400, v, t);
                    sheetOfMusic.add(item4);
                    break;

            }

        }
    }

    public ArrayList<Note_info> getSheetOfMusic_1() {
        return sheetOfMusic;
    }


//
//    for(int i = 0; i< 10; i++) {
//        int temp = 1000;
//        int k = i % 3;
//        switch (k) {
//            case 0:
//                Note_info item = new Note_info(temp + i * 750, 200, -400);
//                sheetOfMusic.add(item);
//                break;
//            case 1:
//                //test할때 x,y변경
//                Note_info item2 = new Note_info(temp + i * 750, 200, -400);
//                sheetOfMusic.add(item2);
//                break;
//            case 2:
//                //test할때 x,y변경
//                Note_info item3 = new Note_info(temp + i * 750, 200, -400);
//                sheetOfMusic.add(item3);
//                break;
//        }
//    }
}
