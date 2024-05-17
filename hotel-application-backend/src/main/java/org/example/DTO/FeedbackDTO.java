package org.example.DTO;

public class FeedbackDTO {
    private int userID;
    private int hotelID;
    private String feedback;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int userID, int hotelID, String feedback) {
        this.userID = userID;
        this.hotelID = hotelID;
        this.feedback = feedback;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "userID=" + userID +
                ", hotelID=" + hotelID +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
