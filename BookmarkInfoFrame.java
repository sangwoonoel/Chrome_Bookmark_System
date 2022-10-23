import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookmarkInfoFrame extends JFrame {
	private BookmarkList list;
	private JButton jbtInput;
	private BookmarkListPanel bLPanel;
	private BookmarkManagerFrame bMF;
	
	public BookmarkInfoFrame() {
		
	}
	
	public BookmarkInfoFrame(BookmarkList file, BookmarkListPanel p, BookmarkManagerFrame f) {
		list = file;
		bLPanel = p;
		bMF = f;
		
		this.setTitle("Input New Bookmark");
		this.setSize(400, 250);
		this.setLocationRelativeTo(null); // Center the frame
		this.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		String[] colName = {"Group","Name", "URL", "Memo"};
		DefaultTableModel model; 
		JTable table;
		JScrollPane scrollPane;
		model = new DefaultTableModel(colName, 1);
		model.setColumnCount(colName.length);
		model.setColumnIdentifiers(colName);
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(650, 300));
		this.add(scrollPane, BorderLayout.CENTER);
		jbtInput = new JButton("Input");
		this.add(jbtInput, BorderLayout.EAST);
		this.setVisible(true);
		this.pack();

		jbtInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String groupName = (String) table.getValueAt(0,0);
				String name = (String) table.getValueAt(0,1);
				String url = (String) table.getValueAt(0,2);
				String memo = (String) table.getValueAt(0,3);
				
				if (groupName == null) groupName = "";
				if (name == null) name = "";
				if (memo == null) memo = "";
				if (url == null || url.isEmpty()) {
					JOptionPane.showMessageDialog(null, "MalformedURLException: wrong URL - No URL");
				}
				else list.add(new Bookmark(name, url, groupName, memo));
				bLPanel.reload();
			}
	    });			
	}

}
