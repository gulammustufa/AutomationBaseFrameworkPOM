package utility;

public interface ApiEndPoints {
    public interface ReqresApiEndPoint{
        String CREATE_USER_END_POINT = "/api/register";
        String GET_USER_END_POINT = "/users/";
        String GET_USERS_END_POINT = "/users?page=1";
    }
}
