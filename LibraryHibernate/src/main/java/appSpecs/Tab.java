package appSpecs;

public class Tab {
	private String tabName;
	private String url;
	private Boolean isForAdmin;
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getIsForAdmin() {
		return isForAdmin;
	}
	public void setIsForAdmin(Boolean isForAdmin) {
		this.isForAdmin = isForAdmin;
	}
	
	public Tab(){
		
	}
	
	public Tab(String tabName, String url, Boolean isForAdmin){
		this.tabName = tabName;
		this.url = url;
		this.isForAdmin = isForAdmin;
	}
}
