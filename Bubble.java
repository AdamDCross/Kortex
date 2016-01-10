import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.function.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.graphics.*;

private class Bubble extends Actor {
	private int radius;

	public Bubble(int x, int y, int radius, Color c,
			int transparency) {
		CircleShape circle = new CircleShape(radius);
		circle.setFillColor(new Color(c, transparency));
		circle.setOrigin(radius, radius);

		this.x = x;
		this.y = y;
		this.radius = radius;

		obj = circle;
	}

	//
	// Default method typically assumes a rectangle,
	// so do something a little different
	//
	@Override
	public boolean within (int px, int py) {
		//
		// In this example, For simplicty...
		// - We just treat circle as a box
		// - x, y is the top corner rather than the centre
		// - We use points x,y and px, py not bounding boxes
		//
		if (px > x && px < x + 2 * radius &&
				py > y && py < y + 2 * radius) {
			return true;
		}
		else {
			return false;
		}
	}
}