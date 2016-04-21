package edu.uark.csce.pizzaparty.uark_directory;

/**
 * Created by silas on 4/10/2016.
 */
public class DirectoryListItem {
    private String thumbnailUrl;
    private String appName;
    private String appDeveloper;

    DirectoryListItem (String thumbnailUrl, String appName, String appDeveloper) {
        this.thumbnailUrl = thumbnailUrl;
        this.appName = appName;
        this.appDeveloper = appDeveloper;
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
