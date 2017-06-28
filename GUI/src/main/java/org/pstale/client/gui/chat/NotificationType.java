/* $Id$ */
package org.pstale.client.gui.chat;

import org.pstale.common.Version;

import com.jme3.math.ColorRGBA;

//
//

/**
 * A logical notification type, which can be mapped to UI specific contexts.
 * This would be similar to logical styles vs. physical styles in HTML.
 */
public enum NotificationType {
	CLIENT("client") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_CLIENT;
		}
	},
	ERROR("error") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_ERROR;
		}
	},
	HEAL("heal") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_POSITIVE;
		}
		@Override
		public String getStyleDescription() {
			return REGULAR;
		}
	},
	INFORMATION("information") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_INFORMATION;
		}
	},
	NEGATIVE("negative") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_NEGATIVE;
		}
		@Override
		public String getStyleDescription() {
			return REGULAR;
		}
	},
	NORMAL("normal") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_NORMAL;
		}
	},
	POISON("poison") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_NEGATIVE;
		}
		@Override
		public String getStyleDescription() {
			return REGULAR;
		}
	},
	POSITIVE("positive") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_POSITIVE;
		}
		@Override
		public String getStyleDescription() {
			return REGULAR;
		}
	},
	EMOTE("emote") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_EMOTE;
		}
	},
	GROUP("group") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_GROUP;
		}
	},
	PRIVMSG("privmsg") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_PRIVMSG;
		}
	},
	RESPONSE("response") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_RESPONSE;
		}
	},
	SCENE_SETTING("scene_setting") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_SCENE_SETTING;
		}
	},
	SERVER("server") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_PRIVMSG;
		}
	},
	SIGNIFICANT_NEGATIVE("significant_negative") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_SIGNIFICANT_NEGATIVE;
		}
	},
	SIGNIFICANT_POSITIVE("significant_positive") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_SIGNIFICANT_POSITIVE;
		}
	},
	TUTORIAL("tutorial") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_TUTORIAL;
		}
	},
	SUPPORT("support") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_SUPPORT;
		}
	},
	DETAILED("detailed") {
		@Override
		public String getStyleDescription() {
			return REGULAR;
		}
	},
	WARNING("warning") {
		@Override
		public ColorRGBA getColor() {
			return COLOR_WARNING;
		}
	};
	public static final ColorRGBA COLOR_CLIENT = ColorRGBA.Gray;

	public static final ColorRGBA COLOR_ERROR = ColorRGBA.Red;

	public static final ColorRGBA COLOR_INFORMATION = ColorRGBA.Orange;

	public static final ColorRGBA COLOR_NEGATIVE = ColorRGBA.Red;

	public static final ColorRGBA COLOR_NORMAL = ColorRGBA.Black;

	public static final ColorRGBA COLOR_POSITIVE = ColorRGBA.Green;

	/** dark blue */
	public static final ColorRGBA COLOR_GROUP = new ColorRGBA(0, 0, 0.625f, 1);

	/** muted purple */
	public static final ColorRGBA COLOR_EMOTE = new ColorRGBA(0.38671875f, 0.23828125f, 0.54296875f, 1);

	public static final ColorRGBA COLOR_PRIVMSG = ColorRGBA.DarkGray;

	/** dark green */
	public static final ColorRGBA COLOR_RESPONSE = new ColorRGBA(0, 0.390625f, 0, 1);
	
	/** dark brown */
	public static final ColorRGBA COLOR_SCENE_SETTING = new ColorRGBA(0.33984375f, 0.125f, 0.0078125f, 1);

	public static final ColorRGBA COLOR_SIGNIFICANT_NEGATIVE = ColorRGBA.Pink;

	/** bright turquoise blue */
	public static final ColorRGBA COLOR_SIGNIFICANT_POSITIVE = new ColorRGBA(0.25390625f, 0.41015625f, 0.87890625f, 1);

	/** purple */
	public static final ColorRGBA COLOR_TUTORIAL = new ColorRGBA(0.671875f, 0, 0.671875f, 1);
	
	/** strong bright orange */
	public static final ColorRGBA COLOR_SUPPORT = new ColorRGBA(1, 0.4453125f, 0, 1);
	
	/** dark red */
	public static final ColorRGBA COLOR_WARNING = new ColorRGBA(0.625f, 0, 0, 1);
	
	// TODO: review thinking here of using constants.
	// these are tied to the ones in client.KTextEdit.gui.initStylesForTextPane
	// so should we tie them together somehow?
	// also the definitions are crazy.
	
	/** normal is bold */
	public static final String NORMALSTYLE = "normal";
	/** regular is not bold */
	public static final String REGULAR = "regular";
	// fwiw, "bold" is blue, italic, bigger than normal, bold and blue.
	
	/**
	 * The mapping mnemonic.
	 */
	protected String mnemonic;

	/**
	 * Create a notification type.
	 *
	 * @param mnemonic
	 *            The mapping mnemonic.
	 */
	private NotificationType(final String mnemonic) {
		this.mnemonic = mnemonic;
	}

	//
	// NotificationType
	//

	/**
	 * Get the mapping mnemonic (programmatic name).
	 *
	 * @return The mapping mnemonic.
	 */
	public String getMnemonic() {
		return mnemonic;
	}

	/**
	 * Get the ColorRGBA that is tied to a notification type.
	 *
	 * @return The appropriate ColorRGBA.
	 */
	public ColorRGBA getColor() {
		return COLOR_NORMAL;
	}

	/**
	 * Get the style that is tied to a notification type.
	 *
	 * @return The appropriate style.
	 */
	public String getStyleDescription() {
		return NORMALSTYLE;
	}
	
	/**
	 * Get notification type for server messages that the client can show
	 * without problems. Call this instead of using SERVER directly.
	 * 
	 * @param clientVersion version of the client
	 * @return appropriate type
	 */
	public static NotificationType getServerNotificationType(String clientVersion) {
		if ((clientVersion != null) && (Version.compare(clientVersion, "1.00") > 0)) {
			return NotificationType.SERVER;
		} else {
			return NotificationType.PRIVMSG;
		}
	}
}
