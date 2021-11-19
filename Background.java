package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {

    class BGPicture {
        private Texture txt;
        private Vector2 pos;

        public BGPicture(Vector2 pos) {
            txt = new Texture("backWinter.png");
            this.pos = pos;
        }
    }
    private BGPicture[] backs;
    private int speed;

    public Background() {
        speed = 1;
        backs = new BGPicture[2];
        backs[0] = new BGPicture(new Vector2(0,0));
        backs[1] = new BGPicture(new Vector2(1920, 0));
    }
    public void render(SpriteBatch batch) {
        for(int i=0; i< backs.length; i++) {
            batch.draw(backs[i].txt, backs[i].pos.x, backs[i].pos.y);
        }
    }
    public void update() {
        for (int i=0; i< backs.length; i++) {
            backs[i].pos.x -= speed;
        }
        if(backs[0].pos.x < -1000) {
            backs[0].pos.x = 0;
            backs[1].pos.x = 1920;
        }
    }
}
