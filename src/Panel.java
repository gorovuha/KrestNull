import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Panel extends JPanel {
    ArrayList<GButton> buttons; //тот единственный лист кнопок висит на панели
    int clicks; //для счёта кликов и решения крестик или нолик писать
    int dxs[] = {1, 1, 0, 1};
    int dys[] = {1, -1, 1, 0};

    //конструктор
    public Panel(ArrayList<GButton> button) {
        this.buttons = button;
        this.clicks = 0;
        this.addMouseListener(new MouseListener() { //слушает мышь на панели
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) { //послушал и крикнул каждой кнопке, что где-то нажалось
                for (int i = 0; i < buttons.size(); i++) {
                    if (buttons.get(i).mousePressed(e, clicks)){ //тут в скобках метод кнопки, которые возвращает правду или ложь
                        clicks++; //клики увеличиваем
                    }

                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


    //то что от родительского JPanel рисует в каждый frame.repaint()
    @Override
    protected void paintComponent(Graphics g) {
        for (int i = 0; i < buttons.size(); i++) { //на панели кнопки, значит каждую надо нарисовать
            buttons.get(i).paintComponent(g);
        }
    }

    //проверка на выигрыш
    public void update(){
        int pole[][] = new int[18][18];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                pole[i][j] = buttons.get(i * 18 + j).index;
                //System.out.println(buttons.get(i * 25 + j).index);
                //System.out.print(pole[i][j]);
            }
            //System.out.println();
        }
        for (int x0 = 0; x0 < 18; x0++) {
            for (int y0 = 0; y0 < 18; y0++) {
                for (int t = 0; t < 4; t++) {
                    int dx = dxs[t];
                    int dy = dys[t];
                    //int x0 = buttons.get(i).x;
                    //int y0 = buttons.get(i).y;
                    int start = pole[y0][x0];
                    boolean isOk = true;
                    if (start == 2){
                        isOk = false;
                    } else {
                        for (int j = 0; j < 5; j++) {
                            if (y0 + j *dy > 0 && y0 + j * dy < 18 &&
                                    x0 + j *dx > 0 && x0 + j * dx < 18){
                                if (pole[y0 + j * dy][x0 + j * dx] != start){
                                    isOk = false;
                                }
                            }
                        }
                    }
                    if (isOk){
                        System.out.println("win");
                        this.end();
                    }
                }
            }

        }

    }

    void end(){
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).text = "end";
        }
        this.setBackground(Color.black);
    }
}

