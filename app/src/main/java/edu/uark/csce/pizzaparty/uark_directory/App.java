package edu.uark.csce.pizzaparty.uark_directory;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class App implements Serializable {
    private Integer id;
    private String name;
    private String developer;
    private String version;
    private String description;
    private Date createDate;
    private Integer numImages;
    private String thumbURL;
    private String apkURL;
    private String apkName;
    private ArrayList<String> screenShotUrls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String publisher) {
        this.developer = publisher;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getNumImages() {
        return numImages;
    }

    public void setNumImages(Integer numImages) {
        this.numImages = numImages;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public String getApkURL() {
        return apkURL;
    }

    public void setApkURL(String apkURL) {
        this.apkURL = apkURL;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public ArrayList<String> getScreenShotUrls() {
        return screenShotUrls;
    }

    public void setScreenShotUrls(ArrayList<String> screenShotUrls) {
        this.screenShotUrls = screenShotUrls;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developer='" + developer + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", numImages=" + numImages +
                ", thumbURL='" + thumbURL + '\'' +
                ", apkURL='" + apkURL + '\'' +
                ", apkName='" + apkName + '\'' +
                ", screenShotUrls=" + screenShotUrls +
                '}';
    }
}
