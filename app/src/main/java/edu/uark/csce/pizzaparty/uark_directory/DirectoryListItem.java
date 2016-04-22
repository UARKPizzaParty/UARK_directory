package edu.uark.csce.pizzaparty.uark_directory;

/**
 * Created by silas on 4/10/2016.
 */
public class DirectoryListItem {
    private int appId;
    private String thumbnailUrl;
    private String appName;
    private String appDeveloper;

    DirectoryListItem (int appId, String thumbnailUrl, String appName, String appDeveloper) {
        this.appId = appId;
        this.thumbnailUrl = thumbnailUrl;
        this.appName = appName;
        this.appDeveloper = appDeveloper;
    }

    public int getAppId () {
        return appId;
    }
    public String getThumbnailUrl () {
        return thumbnailUrl;
    }
    public void setThumbnailUrl(String s) {
        thumbnailUrl = s;
    }
    public String getAppName () {
        return appName;
    }
    public void setAppName(String s) {
        appName = s;
    }
    public String getAppDeveloper() {
        return appDeveloper;
    }
    public void setAppDeveloper(String s) {
        appDeveloper = s;
    }

    @Override
    public String toString() {
        return "appName: " + appName + ", appDeveloper: " + appDeveloper;
    }
}
