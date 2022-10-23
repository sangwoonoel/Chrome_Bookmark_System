import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActBtnPanel extends JPanel {
	private BookmarkList list;
	private BookmarkListPanel bLPanel;
	private JButton jbtAdd;
	private JButton jbtDelete;
	private JButton jbtUp;
	private JButton jbtDown;
	private JButton jbtSave;
	private BookmarkManagerFrame bMF;
	public ActBtnPanel() {
		
	}
	public ActBtnPanel(BookmarkList file, BookmarkListPanel p, BookmarkManagerFrame f) {
		list = file;
		bLPanel = p;
		bMF = f;
		this.setLayout(new GridLayout(5, 1));
		jbtAdd = new JButton("ADD");
		jbtDelete = new JButton("DELETE");
		jbtUp =  new JButton("UP");
		jbtDown = new JButton("DOWN");
		jbtSave = new JButton("SAVE");
		this.add(jbtAdd);
		this.add(jbtDelete);
		this.add(jbtUp);
		this.add(jbtDown);
		this.add(jbtSave);
		
		jbtAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookmarkInfoFrame(list, bLPanel, bMF);
			}
	    });
		
		jbtDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int select = -1;
				select = p.getTable().getSelectedRow();
				if (select >= 0) {
					String token = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 0);
					String groupName = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 1);
					String name = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 2);
					String url = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 3);
					String time = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 4);
					String memo = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 5);
					
					if (!token.isEmpty()) {
						list.remove(groupName);
						int index = p.getTable().getSelectedRow();
						p.getModel().removeRow(index);
						revalidate();
						repaint();
						for(int i = 0; i < p.getTable().getRowCount(); i++ ) {
							if (p.getModel().getValueAt(i, 1).equals(groupName) && p.getModel().getValueAt(i, 0)=="") {
								p.getModel().removeRow(i);
								i--;
							}
						}
					}
					else {
						int bmIndex = list.equal(name, time, url, groupName, memo);
						if (bmIndex >= 0) {
							list.remove(bmIndex);
						}
						int index = p.getTable().getSelectedRow();
						p.getModel().removeRow(index);
						revalidate();
						repaint();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select a bookmark!");
				}
			}
	    });
		
		jbtUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = -1;
				select = p.getTable().getSelectedRow();
				if (select > 0) {
					String token = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 0);
					String groupName = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 1);
					String name = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 2);
					String url = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 3);
					String time = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 4);
					String memo = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 5);
					String tempToken = (String) p.getTable().getValueAt(select - 1, 0);
					String tempGroup = (String) p.getTable().getValueAt(select - 1, 1);
					p.getModel().moveRow(select, select, select - 1);
					revalidate();
					repaint();
					
					if (token == "v" || token == ">") {						
						int groupNameIndex = list.equal(groupName);
						Bookmark temp1 = list.getBookmark(groupNameIndex - 1);
						
						list.set(groupNameIndex-1, list.getBookmark(groupNameIndex));
						list.set(groupNameIndex, temp1);
						list.mergeByGroup();
					}
					else {
						int bmIndex = list.equal(name, time, url, groupName, memo);
						
						if (tempToken == "v" || tempToken == ">") {
							if (!groupName.isEmpty()) {
								;
							}
							else {
								int i = bmIndex;
								while(i >= 1 && !list.getBookmark(i - 1).getGroupName().isEmpty()) {									
									Bookmark temp2 = list.getBookmark(i - 1);
									list.set(i-1, list.getBookmark(i));
									list.set(i, temp2);
									i--;
								}
							}
						}
						else {
							if (!groupName.isEmpty() && tempGroup.isEmpty()) {
								;
							}
							else if (!tempGroup.isEmpty() && groupName.isEmpty()) {
								;
							}
							else {
								if (bmIndex >= 1) {
									Bookmark temp2 = list.getBookmark(bmIndex - 1);
									list.set(bmIndex-1, list.getBookmark(bmIndex));
									list.set(bmIndex, temp2);
								}
							}
						}
					}
				}
			}
	    });
		
		jbtDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = -1;
				select = p.getTable().getSelectedRow();
				
				if (select >= 0 && select < p.getTable().getRowCount() - 1) {
					String token = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 0);
					String groupName = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 1);
					String name = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 2);
					String url = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 3);
					String time = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 4);
					String memo = (String) p.getTable().getValueAt(p.getTable().getSelectedRow(), 5);
					String nGroup = (String) p.getTable().getValueAt(select + 1, 1);
					String nName = (String) p.getTable().getValueAt(select + 1, 2);
					String nUrl = (String) p.getTable().getValueAt(select + 1, 3);
					String nTime = (String) p.getTable().getValueAt(select + 1, 4);
					String nMemo = (String) p.getTable().getValueAt(select + 1, 5);
					String tempToken = (String) p.getTable().getValueAt(select + 1, 0);
					p.getModel().moveRow(select, select, select + 1);
					revalidate();
					repaint();
									
					if (token == "v" || token == ">") {
						if (!nGroup.isEmpty()) {
							;
						}
						else {
							int nBmIndex = list.equal(nName, nTime, nUrl, nGroup, nMemo);
							int i = nBmIndex;
							while(i >= 1 && !list.getBookmark(i - 1).getGroupName().isEmpty()) {
								Bookmark temp2 = list.getBookmark(i - 1);
								list.set(i - 1, list.getBookmark(i));
								list.set(i, temp2);
								i--;
							}
						}
					}
					else {
						int bmIndex = list.equal(name, time, url, groupName, memo);
						if (nGroup.isEmpty() && !groupName.isEmpty()) {
							;
						}
						else if (bmIndex <= list.numBookmarks()) {
							Bookmark temp2 = list.getBookmark(bmIndex + 1);
							list.set(bmIndex+1, list.getBookmark(bmIndex));
							list.set(bmIndex, temp2);
						}
						else {
							;
						}
						if (!tempToken.isEmpty()) {
							list.mergeByGroup();
						}
					}
				}
			}
	    });
		
		jbtSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.writeFile();
			}
	    });
	}
}

