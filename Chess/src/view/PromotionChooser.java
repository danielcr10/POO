package view;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.ChessController;

public class PromotionChooser {

	private JPopupMenu popupMenu = new JPopupMenu();

	private BoardView parent;

	private ChessController controller;

	private Point pawnPosition;

	public PromotionChooser(BoardView parent, ChessController controller) {
		this.controller = controller;
		this.parent = parent;
		for(String label : controller.getPawnPromotionPossibilities()) {
			final JMenuItem item = new JMenuItem(label);
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final JMenuItem selectedItem = (JMenuItem) e.getSource();
					controller.requestPawnPromotionAt(pawnPosition, selectedItem.getText());
					pawnPosition = null;
					parent.promotionPosition = null;
					parent.clickedSquare = null;
				}
			});
			popupMenu.add(item);
		}
		// Por algum motivo, se eu nao setar o JPopupMenu para visível e depois
		// para invisível o popup demora muito pra abrir na primeira abertura.
		popupMenu.setVisible(true);
		popupMenu.setVisible(false);
	}

	public void setPawnPosition(Point position) {
		pawnPosition = position;
	}

	public void openMenu(Point where) {
		popupMenu.show(parent, where.x, where.y);
	}

}
