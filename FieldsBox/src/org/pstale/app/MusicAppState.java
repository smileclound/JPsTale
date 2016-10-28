package org.pstale.app;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioSource;
import com.jme3.audio.AudioData.DataType;

/**
 * 背景音乐
 * 
 * @author yanmaoyuan
 */
public class MusicAppState extends BaseAppState {
	private String song;
	private AudioNode music;
	private String playing;
	private float delay;
	private float volume = 1.0f;

	public MusicAppState() {
	}

	public void setSong(String song) {
		setSong(song, 0);
	}

	public void setSong(String song, float delay) {
		if (this.song == null && song == null)
			return;
		
		/**
		 * 换歌了
		 */
		if (this.song != null && !this.song.equals(song)) {
			if (music != null) {
				music.stop();
				music = null;
			}
		}
		
		/**
		 * 停止播放
		 */
		if (song == null) {
			this.song = null;
			this.delay = delay;
			resetSong();
			return;
		}
		
		if (song != null && song.equals(this.song))
			return;
		
		this.delay = delay;
		this.song = song;
		resetSong();
	}

	public void setVolume(float volume) {
		if (this.volume == volume) {
			return;
		}
		this.volume = volume;
		if (music != null && music.getStatus() != AudioSource.Status.Stopped) {
			music.setVolume(volume);
		}
	}

	protected void resetSong() {
		if (delay > 0) {
			return;
		}

		if (song == null && music != null) {
			music.stop();
			music = null;
			return;
		} else if (song == null) {
			return;
		}

		// Need to see if we are playing the right song
		if (music == null || music.getStatus() == AudioSource.Status.Stopped || !song.equals(playing)) {

			try {
				AssetManager assets = getApplication().getAssetManager();
				music = new AudioNode(assets, song, DataType.Buffer);
				music.setReverbEnabled(false);
				music.setPositional(false);
				music.setVolume(volume);
				music.play();
				playing = song;
			} catch (Exception e) {
				System.out.println("加载音乐失败:" + song);
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void initialize(Application app) {
	}

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
	}

	@Override
	protected void onDisable() {
	}

	@Override
	public void update(float tpf) {
		delay -= tpf;
		resetSong();
	}
}