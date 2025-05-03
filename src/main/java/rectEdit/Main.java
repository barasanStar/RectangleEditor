package rectEdit;

import java.awt.Color;

import rectEdit.model.Rect;
import rectEdit.model.RectFactory;

public class Main {
	public static void main(String[] args) {
		Rect rect1 = RectFactory.create(1, 1, 1, 1, new Color(255, 0, 255));
		System.out.println(rect1);
		Rect rect2 = RectFactory.createRandom();
		System.out.println(rect2);
		Rect rect3 = RectFactory.createRandom();
		System.out.println(rect3);

	}
}
