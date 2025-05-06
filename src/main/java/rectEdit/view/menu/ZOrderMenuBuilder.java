package rectEdit.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import rectEdit.controller.RectEditorController;
import rectEdit.handler.ActionKey;
import rectEdit.model.RectEditorModel;
import rectEdit.service.ZOrderManager;

public class ZOrderMenuBuilder {
	public static JMenu build(RectEditorModel model, RectEditorController controller) {
		JMenu zOrderMenu = new JMenu("Z順操作");

		JMenuItem zOrderFrontMenuItem = new JMenuItem(ActionKey.Z_ORDER_FRONT);
		JMenuItem zOrderBackMenuItem = new JMenuItem(ActionKey.Z_ORDER_BACK);
		JMenuItem zOrderForwardMenuItem = new JMenuItem(ActionKey.Z_ORDER_FORWARD);
		JMenuItem zOrderBackwardMenuItem = new JMenuItem(ActionKey.Z_ORDER_BACKWARD);

		zOrderFrontMenuItem.addActionListener(e -> {
			model.pushSnapshot();
			ZOrderManager.bringToFront(model.getRectanglesForMutation(), model.getSelectedIds());
			model.notifyRectsChanged("Z順を最前面に移動しました");
		});

		zOrderBackMenuItem.addActionListener(e -> {
			model.pushSnapshot();
			ZOrderManager.sendToBack(model.getRectanglesForMutation(), model.getSelectedIds());
			model.notifyRectsChanged("Z順を最背面に移動しました");
		});

		zOrderForwardMenuItem.addActionListener(e -> {
			model.pushSnapshot();
			ZOrderManager.moveForward(model.getRectanglesForMutation(), model.getSelectedIds());
			model.notifyRectsChanged("Z順を一つ前面に移動しました");
		});

		zOrderBackwardMenuItem.addActionListener(e -> {
			model.pushSnapshot();
			ZOrderManager.moveBackward(model.getRectanglesForMutation(), model.getSelectedIds());
			model.notifyRectsChanged("Z順を一つ背面に移動しました");
		});

		zOrderMenu.add(zOrderFrontMenuItem);
		zOrderMenu.add(zOrderBackMenuItem);
		zOrderMenu.add(zOrderForwardMenuItem);
		zOrderMenu.add(zOrderBackwardMenuItem);

		return zOrderMenu;
	}
}
