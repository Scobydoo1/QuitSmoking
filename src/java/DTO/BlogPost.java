/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Nghia
 */
public class BlogPost {

    private String idPost;
    private String idMember;
    private String title;
    private String content;
    private String image;
    private Date publishDate;

    // Constructor
     public BlogPost() {
       
    }
     
    public BlogPost(String idPost, String idMember, String title, String content, String image, Date publishDate) {
        this.idPost = idPost;
        this.idMember = idMember;
        this.title = title;
        this.content = content;
        this.image = image;
        this.publishDate = publishDate;
    }

   

    // Getter and Setter methods
    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
