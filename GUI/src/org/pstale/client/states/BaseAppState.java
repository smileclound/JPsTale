package org.pstale.client.states;

import java.util.Random;

import org.pstale.client.Game;

import tonegod.gui.core.Screen;

import com.jme3.app.state.AbstractAppState;

public class BaseAppState extends AbstractAppState {
	protected Game game;
	protected Screen screen;
	protected Random rand;
	
	public BaseAppState(Game app, Screen screen) {
		this.game = app;
		this.screen = screen;
		rand = new Random();
	}
}
