import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GButton {
    int x; //координаты
    int y;
    double width; //размер
    double height;
    String text; //x или o
    int index; //индекс строки в кнопке
    ActionListener listener; //слушатель

    void addActionListener(ActionListener listener){
        this.listener = listener;
    } //в main прикрепляли именно благодаря вот этому

    //конструктор
    public GButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = ""; //ничего не пишем
        this.index = 2;
    }

    //то самое "рисуй" из панели рисует каждую кнопку
    void paintComponent(Graphics g){
        g.drawRect(x, y, (int)width, (int)height);
        g.drawString(text, x+(int)(width / 2), y+(int)(height / 2));
    }

    //проверяем в каждой кнопке, а не тут ли кликнулось, и если до, то возвращаем правду и пишем текст, а панель увеличивает клики
    //так же тут проверка на наличие текста в кнопке и её игнор, если текст уже есть
    boolean mousePressed(MouseEvent e, int click){
        if(e.getX() > x && e.getX() < x + width && e.getY() > y && e.getY() < y + height){
            if (click % 2 == 0 && this.text == ""){
                this.text = "x";
                this.index = 0;
                return true;
            }  else if (click % 2 == 1 && this.text == ""){
                this.text = "o";
                this.index = 1;
                return true;
            } else{
                return false;
            }
        }
        return false;
    }
}