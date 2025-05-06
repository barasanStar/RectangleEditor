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

		JMenuItem zOrderFrontItem = new JMenuItem(ActionKey.Z_ORDER_FRONT);
		JMenuItem zOrderBackItem = new JMenuItem(ActionKey.Z_ORDER_BACK);
		JMenuItem zOrderForwardItem = new JMenuItem(ActionKey.Z_ORDER_FORWARD);
		JMenuItem zOrderBackwardItem = new JMenuItem(ActionKey.Z_ORDER_BACKWARD);

		zOrderFrontItem.addActionListener(e -> {
			model.pushSnapshot();
			ZOrderManager.bringToFront(model.getRectanglesForMutation(), model.getSelectedIds());
			model.notifyRectsChanged("Z順を最前面に移動しました");
		});

		zOrderBackItem.addActionListener(e -> {
			model.pushSnapshot();
			ZOrderManager.sendToBack(model.getRectanglesForMutation(), model.getSelectedIds());
			model.notifyRectsChanged("Z順を最背面に移動しました");
		});

		zOrderForwardItem.addActionListener(e -> {
			model.pushSnapshot();
			ZOrderManager.moveForward(model.getRectanglesForMutation(), model.getSelectedIds());
			model.notifyRectsChanged("Z順を一つ前面に移動しました");
		});

		zOrderBackwardItem.addActionListener(e -> {
			model.pushSnapshot();
			ZOrderManager.moveBackward(model.getRectanglesForMutation(), model.getSelectedIds());
			model.notifyRectsChanged("Z順を一つ背面に移動しました");
		});

		zOrderMenu.add(zOrderFrontItem);
		zOrderMenu.add(zOrderBackItem);
		zOrderMenu.add(zOrderForwardItem);
		zOrderMenu.add(zOrderBackwardItem);

		return zOrderMenu;
	}
}
