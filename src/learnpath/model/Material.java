package learnpath.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Material {
    private String materialId;
    private String fileName;
    private String fileLink; 
    private String materialDetail; 
    private Timestamp uploadTimestamp;
    private String uploadedByUserId;
    private String roomId;
    private String subjectId;

    public Material() {
    }
    public Material(String fileName, String fileLink, String materialDetail, String uploadedByUserId, String roomId) {
        this.materialId = UUID.randomUUID().toString();
        this.fileName = fileName;
        this.fileLink = fileLink;
        this.materialDetail = materialDetail;
        this.uploadedByUserId = uploadedByUserId;
        this.roomId = roomId;
        this.uploadTimestamp = uploadTimestamp;
    }
    public String getMaterialId() {
        return materialId;
    }
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileLink() {
        return fileLink;
    }
    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
    public String getMaterialDetail() {
        return materialDetail;
    }
    public void setMaterialDetail(String materialDetail) {
        this.materialDetail = materialDetail;
    }
    public Timestamp getUploadTimestamp() {
        return uploadTimestamp;
    }
    public void setUploadTimestamp(Timestamp uploadTimestamp) {
        this.uploadTimestamp = uploadTimestamp;
    }  
    public String getUploadedByUserId() {
        return uploadedByUserId;
    }
    public void setUploadedByUserId(String uploadedByUserId) {
        this.uploadedByUserId = uploadedByUserId;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}