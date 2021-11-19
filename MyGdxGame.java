package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Santa santa;
	Obstacles obstacles;
	boolean gameOver;
	Texture restartTxt;
	BitmapFont font;
	int score=0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		santa = new Santa();
		obstacles = new Obstacles();
		gameOver = false;
		restartTxt = new Texture("RestartBtn.png");
		font = new BitmapFont();
	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		bg.render(batch);
		obstacles.render(batch);
		if(!gameOver) {
			santa.render(batch);
		} else {
			batch.draw(restartTxt, 200, 200);
		}
		String scoreStr = Integer.toString(score);
		font.draw(batch, scoreStr, 30, 30);
		font.setColor(Color.BLUE);
		batch.end();
	}


	public void update() {
		bg.update();
		santa.update();
		obstacles.update();
		for(int i=0; i<Obstacles.obs.length; i++) {
			if (santa.position.x > Obstacles.obs[i].position.x && santa.position.x < Obstacles.obs[i].position.x + 50) {
				if (santa.position.y < Obstacles.arr[i][0] || santa.position.y > Obstacles.arr[i][1]) {
					gameOver = true;
					score = 0;
				}
			}
			if(santa.position.x == Obstacles.obs[i].position.x) {
				score+=1;
			}
			if (santa.position.y < 0 || santa.position.y > 600) {
				gameOver = true;
				score = 0;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver) {
				recreate();
			}
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void recreate() {
		santa.recreate();
		obstacles.recreate();
		gameOver = false;
	}
}
