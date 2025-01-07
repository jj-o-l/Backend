package com.example.javaproject.DTO;

import java.util.Objects;

public class UserInfoDto {
    private String userId;
    private String username;
    private Integer score;
    private Integer success;
    private Integer failed;

    // 기본 생성자
    public UserInfoDto() {
    }

    // 모든 필드를 초기화하는 생성자
    public UserInfoDto(String userId, String username, Integer score, Integer success, Integer failed) {
        this.userId = userId;
        this.username = username;
        this.score = score;
        this.success = success;
        this.failed = failed;
    }

    // Getter와 Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", success=" + success +
                ", failed=" + failed +
                '}';
    }

    // equals 메서드
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserInfoDto userInfoDto = (UserInfoDto) obj;
        return Objects.equals(userId, userInfoDto.userId) &&
                Objects.equals(username, userInfoDto.username) &&
                Objects.equals(score, userInfoDto.score) &&
                Objects.equals(success, userInfoDto.success) &&
                Objects.equals(failed, userInfoDto.failed);
    }

    // hashCode 메서드
    @Override
    public int hashCode() {
        return Objects.hash(userId, username, score, success, failed);
    }
}
