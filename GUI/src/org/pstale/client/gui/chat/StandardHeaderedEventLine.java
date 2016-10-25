/* $Id$ */
package org.pstale.client.gui.chat;

public class StandardHeaderedEventLine extends EventLine {

	public StandardHeaderedEventLine(final String header, final String text) {
		super(header, text, NotificationType.NORMAL);
	}

}
