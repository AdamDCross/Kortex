import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.function.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.graphics.*;

private class Message extends Actor {
	public Message(int x, int y, int r, String message, Color c) {
		//
		// Load the font
		//
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get(FontPath+FontFile));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}

		Text text = new Text (message, sansRegular, fontSize);
		text.setColor(c);
		text.setStyle(Text.BOLD | Text.UNDERLINED);

		FloatRect textBounds = text.getLocalBounds( );
		// Find middle and set as origin/ reference point
		text.setOrigin(textBounds.width / 2,
				textBounds.height / 2);

		this.x = x;
		this.y = y;
		this.r = r;

		obj = text;
	}
}