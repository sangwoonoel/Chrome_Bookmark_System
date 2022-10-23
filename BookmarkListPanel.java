import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;


public class BookmarkListPanel extends JPanel {
	private BookmarkList list;
	private DefaultTableModel model; 
	private JTable table;
	private JScrollPane scrollPane;
	
	public BookmarkListPanel() {
		
	}
	public BookmarkListPanel(BookmarkList file){
		list = file;
		String[] colName = {"", "Group","Name", "URL", "Created Time", "Memo"};
		list.mergeByGroup();
		model = new DefaultTableModel(colName, 0);
		String token = "!@#$%";
		
		
		for(int i = 0; i < list.numBookmarks(); i++ ) {
			if (token.equals(list.getBookmark(i).getGroupName())) {
				continue;
			}
			if (!list.getBookmark(i).getGroupName().isEmpty()) {
				token = list.getBookmark(i).getGroupName();
				String temp[] = {">",list.getBookmark(i).getGroupName(),"","","",""};
				model.addRow(temp);
			}
			else {
				String temp[] = {"",list.getBookmark(i).getGroupName(),list.getBookmark(i).getName(),list.getBookmark(i).getUrl(),list.getBookmark(i).getTime(),list.getBookmark(i).getMemo()};
				model.addRow(temp);
			}
		}
		
		model.setColumnCount(colName.length);
		model.setColumnIdentifiers(colName);
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);	
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(650, 300));
		this.add(scrollPane);  
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) { 
					JTable target = (JTable)e.getSource();
					if (target != table) return;
					if (table.getSelectedColumn() == 0) {
						String openMark = (String) table.getValueAt(table.getSelectedRow(), 0);
						String sGroup = (String) table.getValueAt(table.getSelectedRow(), 1);
						int index = table.getSelectedRow();
			
						if (openMark.equals(">")){
							model.setValueAt("v", index, 0);
							int count = 1;
							for(int i = 0; i < list.numBookmarks(); i++ ) {
								if(list.getBookmark(i).getGroupName().equals(sGroup)) {									
									String name = list.getBookmark(i).getName();
									String time = list.getBookmark(i).getTime();
									String url = list.getBookmark(i).getUrl();
									String groupName = list.getBookmark(i).getGroupName();
									String memo = list.getBookmark(i).getMemo();
									model.insertRow(index + count, new String[] {"", groupName, name, url, time, memo});
									count++;
								}
							}					
						}
						else if (openMark.equals("v")){
							model.setValueAt(">", index, 0);
							for(int i = 0; i < table.getRowCount(); i++ ) {
								if (model.getValueAt(i, 1).equals(sGroup) && model.getValueAt(i, 0)=="") {
									model.removeRow(i);
									i--;
								}									
							}
						}
						else {
							;
						}												
					}
					else {
						;
					}
				}
			}
		});	
	}

	public void reload() {
		this.remove(scrollPane);
		validate();
		repaint();
		String[] colName = {"", "Group","Name", "URL", "Created Time", "Memo"};
		list.mergeByGroup();
		model = new DefaultTableModel(colName, 0);
		String token = "!@#$%";
		
		for(int i = 0; i < list.numBookmarks(); i++ ) {
			if (token.equals(list.getBookmark(i).getGroupName())) {
				continue;
			}
			if (!list.getBookmark(i).getGroupName().isEmpty()) {
				token = list.getBookmark(i).getGroupName();
				String temp[] = {">",list.getBookmark(i).getGroupName(),"","","",""};
				model.addRow(temp);
			}
			else {
				String temp[] = {"",list.getBookmark(i).getGroupName(),list.getBookmark(i).getName(),list.getBookmark(i).getUrl(),list.getBookmark(i).getTime(),list.getBookmark(i).getMemo()};
				model.addRow(temp);
			}
		}		
		model.setColumnCount(colName.length);
		model.setColumnIdentifiers(colName);
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);	
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(650, 300));
		this.add(scrollPane); 
		this.revalidate();
		this.repaint();
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) { 
					JTable target = (JTable)e.getSource();
					if (target != table) return;
					if (table.getSelectedColumn() == 0) {
						String openMark = (String) table.getValueAt(table.getSelectedRow(), 0);
						String sGroup = (String) table.getValueAt(table.getSelectedRow(), 1);
						int index = table.getSelectedRow();
		
						if (openMark.equals(">")){
							model.setValueAt("v", index, 0);
							int count = 1;
							for(int i = 0; i < list.numBookmarks(); i++ ) {
								if(list.getBookmark(i).getGroupName().equals(sGroup)) {
									String name = list.getBookmark(i).getName();
									String time = list.getBookmark(i).getTime();
									String url = list.getBookmark(i).getUrl();
									String groupName = list.getBookmark(i).getGroupName();
									String memo = list.getBookmark(i).getMemo();									
									model.insertRow(index + count, new String[] {"", groupName, name, url, time, memo});
									count++;
								}
							}
						}
						else if (openMark.equals("v")){
							model.setValueAt(">", index, 0);
							for(int i = 0; i < table.getRowCount(); i++ ) {
								if (model.getValueAt(i, 1).equals(sGroup) && model.getValueAt(i, 0)=="") {
									model.removeRow(i);
									i--;
								}									
							}							
						}
						else {
							;
						}
					}
					else {
						;
					}
				}
			}
		});
	}
	
	public JTable getTable() {
		return table;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
}
