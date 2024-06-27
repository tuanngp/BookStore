
package entity.Product;

import java.sql.Timestamp;



/*
 * @author tuanngp
 */
public class Log {
    private int userId;
    private Timestamp loginTime;
    private Timestamp logoutTime;

    public Log(int userId) {
        this.userId = userId;
    }

    public Log(int userId, Timestamp loginTime, Timestamp logoutTime) {
        this.userId = userId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }



    @Override
    public String toString() {
        return "Log{" + "userId=" + userId + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + '}';
    }
}
