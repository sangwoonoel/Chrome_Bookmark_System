import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bookmark {
	private String name = "";
	private String time;
	private String url;
	private String groupName = "";
	private String memo = "";
	
	Bookmark(String url) {
		LocalDateTime now = LocalDateTime.now();
		this.time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
		this.url = url;
	}
	//새로 추가?
	Bookmark(String name, String url, String groupName, String memo) {
		this.name = name;
		LocalDateTime now = LocalDateTime.now();
		this.time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
		this.url = url;
		this.groupName = groupName;
		this.memo = memo;
	}
	
	Bookmark(String name, String time, String url, String groupName, String memo) {
		this.name = name;
		this.time = time;
		this.url = url;
		this.groupName = groupName;
		this.memo = memo;
	}
	
	//getter
	public String getName() {
		return this.name;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getGroupName() {
		return this.groupName;
	}
	
	public String getMemo() {
		return this.memo;
	}
	
	//setter
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setTime(String newTime) {
		this.time = newTime;
	}
	
	public void setUrl(String newUrl) {
		this.url = newUrl;
	}
	
	public void setGroupName(String newGroupName) {
		this.groupName = newGroupName;
	}
	
	public void setMemo(String newMemo) {
		this.memo = newMemo;
	}
	
	public void print() {
		System.out.println(name + "," + time + "," + url + "," + groupName + "," + memo);
	}
	
}
