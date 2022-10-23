import javax.swing.*;
import java.awt.*;

public class BookmarkManagerFrame extends JFrame {
	private BookmarkListPanel p1;
	private ActBtnPanel p2;
	
	public BookmarkManagerFrame() {
		BookmarkList list = new BookmarkList("C:\\Users\\sangwoo\\eclipse-workspace\\Project6\\src\\bookmark.txt");
		this.setTitle("BookmarkManager");
		this.setSize(400, 250);
		this.setLocationRelativeTo(null); // Center the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
	    p1 = new BookmarkListPanel(list);
		this.add(p1, BorderLayout.CENTER);
		
		p2 = new ActBtnPanel(list, p1, this);
		this.add(p2, BorderLayout.EAST);
		
		this.setVisible(true);
		this.pack();
	}
	
	public void reload() {
		this.remove(p1);
		this.add(p1);
		this.revalidate();
		this.repaint();
	}
}
