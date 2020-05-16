package me.magnum.melonds;

import me.magnum.melonds.model.Input;
import me.magnum.melonds.model.Point;

public interface IInputListener {
	void onKeyPress(Input key);
	void onKeyReleased(Input key);
	void onTouch(Point point);
}
