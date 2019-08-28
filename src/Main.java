import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame(); //окно и рутина с ним
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640, 640);

        int width = frame.getWidth(); //чтобы кнопки тянулись
        int height = frame.getHeight();

        //GButton button = new GButton(10, 10, (int)(width * 0.1), (int)(height*0.1));

        ArrayList<GButton> buttons = new ArrayList<GButton>(); //тут единственных раз создаём массив кнопок и заполняем
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                buttons.add(new GButton(10 + (int)(width * 0.05 * i), 10 + (int)(height*0.05 * j), (int)(width * 0.05), (int)(height*0.05)));
            }
            
        }

        //панель с кнопками внутри крепим к окну
        Panel panel = new Panel(buttons);
        frame.add(panel);

        //вешаем на каждую кнопку слушателя, причём хитрого, своего созданного
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { }
            });
        }



        frame.setVisible(true);

        while (true){
            frame.repaint();
            panel.update();
            width = frame.getWidth();
            height = frame.getHeight();
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).x = 10 + (int)(width * 0.05 * (i % 18));
                buttons.get(i).y = 10 + (int)(height*0.05 * (i /18));
                int nx = 10 + (int)(width * 0.05 * (i % 18 + 1));
                int ny = 10 + (int)(height*0.05 * (i / 18 + 1));
                buttons.get(i).width = nx - buttons.get(i).x;
                buttons.get(i).height = ny - buttons.get(i).y;
            }

            //System.out.print(width );
            //System.out.println(height);
        }
    }
}
