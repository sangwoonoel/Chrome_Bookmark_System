import java.io.File;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.io.FileWriter;

public class BookmarkList {
	private ArrayList<Bookmark> bookmarkList = new ArrayList<Bookmark>();
	private Scanner input;
	
	BookmarkList() {
		
	}
	
	BookmarkList(String bookmarkFileName) {
		File file = new File(bookmarkFileName);
		
		try {
			input = new Scanner(file);
		}
		catch(Exception e) {
			System.out.println("Unknown BookmarkList data File");
			return;
		}
		
		while(input.hasNext()) {
			String line = input.nextLine();
			String[] bMark = line.split(",|;", -1); 
			
			for (int i = 0; i < bMark.length; i++) { 
				bMark[i] = bMark[i].trim();
			}

			if (bMark.length < 5)
				continue;
	
			if (bMark[0].startsWith("//"));
			
			else if (bMark[2].isEmpty()) {
				System.out.println("MalformedURLException: wrong URL - No URL ; invalid Bookmark info line: " + line);
			}
			else if(bMark[1].isEmpty() && !bMark[2].isEmpty()) {
				this.bookmarkList.add(new Bookmark(bMark[2]));
			}
			else if(!checkDate(bMark[1])) {
				System.out.println("Date Format Error -> No Created Time invalid Bookmark info line: " + line);
			}
			else {
				this.bookmarkList.add(new Bookmark(bMark[0], bMark[1], bMark[2], bMark[3], bMark[4]));
			}
		}
		this.input.close();
	}

	public int numBookmarks() {
		//return numOfBookMarks;
		return bookmarkList.size();
	}

	public Bookmark getBookmark(int i) {
		return bookmarkList.get(i);
	}
	
	public void writeFile() {
		File file = new File("C:\\Users\\sangwoo\\eclipse-workspace\\Project6\\src\\bookmark.txt");
		try {
			FileWriter fw = new FileWriter(file);
			for(int i = 0; i < numBookmarks(); i++) {
				fw.write(this.getBookmark(i).getName() + ";" + this.getBookmark(i).getTime() + ";" + 
						this.getBookmark(i).getUrl() + ";" + this.getBookmark(i).getGroupName() + ";" + 
						this.getBookmark(i).getMemo() + "\n");
			}
			fw.close();
		}
		catch(Exception e) {
			System.out.println("Failed to save file");
			return;
		}
		System.out.println("Save file successfully");
	}
	
	public void mergeByGroup() {
		for(int i = 0; i < this.numBookmarks(); i++) {
			String mergeGroupName = this.getBookmark(i).getGroupName();
			if(mergeGroupName.isEmpty())
				continue;
			else {
				for(int j = i + 1; j < this.numBookmarks(); j++) {
					String sameGroupName = this.getBookmark(j).getGroupName();
					if(sameGroupName.isEmpty())
						continue;
					else {
						if (mergeGroupName.equals(sameGroupName)) {
							int k = i + 1;							
							while(k < j) {
								Bookmark temp = this.getBookmark(k);
								this.bookmarkList.set(k ,this.getBookmark(j));
								this.bookmarkList.set(j ,temp);
								k++;
							}
							break;
						}
					}					
				}
			}												
		}
	}
			
	public void add(Bookmark book) {
		bookmarkList.add(book);
	}
	public void remove(int i) {
		bookmarkList.remove(i);
	}
	
	public void remove(String groupName) {
		for(int i = 0; i < numBookmarks(); i++) {
			if (getBookmark(i).getGroupName().equals(groupName)) {
				remove(i);
				i--;
			}
		}
	}
	
	public void set(int i, Bookmark book) {
		bookmarkList.set(i, book);
	}
	
	public int equal(String name, String time, String url, String groupName, String memo) {
		for(int i = 0; i < numBookmarks(); i++) {
			if (getBookmark(i).getName().equals(name) && getBookmark(i).getTime().equals(time) && 
					getBookmark(i).getUrl().equals(url) && getBookmark(i).getGroupName().equals(groupName) && getBookmark(i).getMemo().equals(memo)) {
				return i;
			}
			else {
				continue;
			}	
		}
		return -1;
	}
	
	public int equal(String groupName) {
		for(int i = 0; i < numBookmarks(); i++) {
			if (getBookmark(i).getGroupName().equals(groupName)) {
				return i;
			}
			else {
				continue;
			}	
		}
		return -1;
	}
	
    public boolean checkDate(String checkDate) {
        try {
            SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd_HH:mm"); 
            dateFormatParser.setLenient(false); 
            dateFormatParser.parse(checkDate); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
	

