package user;

import common.DbConnection;

import java.sql.Connection;

public class UserService {

    private Connection conn;

    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao(DbConnection.getConnection());
    }

    public void signup(UserReqDto userReqDto) {
        userDao.signup(userReqDto);
    }

    public int login(UserReqDto userReqDto) {

        int idx = userDao.selectUser(userReqDto);

        return idx;
    }
}
